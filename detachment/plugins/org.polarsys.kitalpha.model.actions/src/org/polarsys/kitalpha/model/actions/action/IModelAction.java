/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    THALES GLOBAL SERVICES - Initial API and implementation
 *******************************************************************************/
package org.polarsys.kitalpha.model.actions.action;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.kitalpha.model.actions.exception.ModelActionException;

/**
 * @author Faycal Abka
 */
public interface IModelAction {
	
	public void setModelAnalysisID(String id);
	public String getModelAnalysisID();
	
	public void exec(Resource resource, IProgressMonitor monitor)
			throws ModelActionException;
}
