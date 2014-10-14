/*******************************************************************************
 * Copyright (c) 2014 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *   Thales Global Services S.A.S - initial API and implementation
 *******************************************************************************/

package org.polarsys.kitalpha.model.attachment.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.diffmerge.api.IMergeSelector;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EElementPresence;
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.setup.ComparisonSetup;
import org.eclipse.emf.diffmerge.ui.setup.ComparisonSetupManager;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Thomas Guiu
 * 
 */
public class AttachmentWizard extends Wizard {

	private final class MergeCommand extends RecordingCommand {
		private final EditingDomain domain;
		private final IComparisonMethod method;
		private IProgressMonitor monitor;

		private MergeCommand(TransactionalEditingDomain domain, IComparisonMethod method, IProgressMonitor monitor) {
			super(domain);
			this.domain = domain;
			this.method = method;
			this.monitor = monitor;
		}

		@Override
		protected void doExecute() {
			try {
				Role leftRole = Role.REFERENCE;
				IEditableModelScope sourceScope = method.getModelScopeDefinition(leftRole).createScope(domain);
				if (sourceScope instanceof IPersistentModelScope)
					((IPersistentModelScope) sourceScope).load();

				Role rightRole = Role.TARGET;
				IEditableModelScope targetScope = method.getModelScopeDefinition(rightRole).createScope(domain);
				if (targetScope instanceof IPersistentModelScope)
					((IPersistentModelScope) targetScope).load();

				IEditableModelScope ancestorScope = null;// method.getModelScopeDefinition(Role.ANCESTOR).createScope(domain);
				if (ancestorScope instanceof IPersistentModelScope)
					((IPersistentModelScope) ancestorScope).load();

				EComparison comparison = new EComparisonImpl(targetScope, sourceScope, ancestorScope);
				comparison.compute(method.getMatchPolicy(), method.getDiffPolicy(), method.getMergePolicy(), monitor);

				boolean consistent = comparison.isConsistent();
				boolean hasRemainingDifferences = comparison.hasRemainingDifferences();
				Collection<IDifference> remainingDifferences = comparison.getRemainingDifferences();
				comparison.merge(new IMergeSelector() {

					@Override
					public Role getMergeDirection(IDifference difference_p) {
						System.out.println(difference_p);
						if (difference_p.isConflicting())
							return null;
						if (difference_p instanceof EReferenceValuePresence) {
							EReferenceValuePresence diff = (EReferenceValuePresence) difference_p;
							return toMerge(diff.getValue().getReference()) ? Role.TARGET : null;
						}
						if (difference_p instanceof EElementPresence) {
							// les nouveaux elts
							EElementPresence diff = (EElementPresence) difference_p;
							return toMerge(diff.getElement(), true) ? Role.TARGET : null;
						}

						return Role.TARGET;
					}
				}, true, monitor);
				// comparison.merge(rightRole, true, null);
				Collection<IDifference> remainingDifferences2 = comparison.getRemainingDifferences();
				if (targetScope instanceof IPersistentModelScope.Editable)
					((IPersistentModelScope.Editable) targetScope).save();

			} catch (Exception e) {
				throw new WrappedException(e);
			}
		}
	}

	private List<IFile> files;

	private ModelPage modelPage;
	private ViewpointPage viewpointPage;

	public AttachmentWizard(List<IFile> files) {
		this.files = files;
		setNeedsProgressMonitor(true);
		setWindowTitle(Messages.wizard_title);

	}

	@Override
	public void addPages() {
		addPage(modelPage = new ModelPage(files));
		addPage(viewpointPage = new ViewpointPage());
	}

	@Override
	public boolean performFinish() {
		ComparisonSetupManager manager = EMFDiffMergeUIPlugin.getDefault().getSetupManager();
		ComparisonSetup setup = manager.createComparisonSetup(modelPage.getTargetFile(), modelPage.getSourceFile(), modelPage.getAncestorFile());
		IComparisonMethodFactory comparisonMethodFactory = setup.getApplicableComparisonMethodFactories().get(0);
		final IComparisonMethod method = comparisonMethodFactory.createComparisonMethod(setup.getScopeDefinition(Role.TARGET), setup.getScopeDefinition(Role.REFERENCE), setup.getScopeDefinition(Role.ANCESTOR));
		final EditingDomain domain = method.getEditingDomain();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {

					domain.getCommandStack().execute(new MergeCommand((TransactionalEditingDomain) domain, method, monitor));
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(false, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), Messages.error_dialog_title, realException.getMessage());
			return false;
		}

		return true;
	}

	private boolean toMerge(EObject obj) {
		return viewpointPage.analysisResult.getUriToRemove().contains(obj.eClass().getEPackage().getNsURI());
	}

	private boolean toMerge(EObject obj, boolean checkContainers) {
		boolean merge = toMerge(obj);
		if (merge || !checkContainers)
			return merge;
		EObject eContainer = obj.eContainer();
		if (eContainer == null)
			return false;
		return toMerge(eContainer, true);
	}
}
