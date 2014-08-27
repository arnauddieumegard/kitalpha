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

package org.polarsys.kitalpha.model.analysis.contrib.unknownreferences.finders;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.polarsys.kitalpha.model.analysis.contrib.unknownreferences.Messages;
import org.polarsys.kitalpha.model.analysis.contrib.unknownreferences.feedback.Feedback;
import org.polarsys.kitalpha.model.analysis.interfaces.IFeedback.FeedbackLevel;
import org.polarsys.kitalpha.model.analysis.interfaces.IFeedback.IFeedbackMessage;
import org.polarsys.kitalpha.model.analysis.interfaces.IFinder;

/**
 * @author Matthieu Helleboid
 */
public class InvalidFilterDescription implements IFinder<Set<FilterDescription>, Collection<IFeedbackMessage>> {
	
	private Set<FilterDescription> filterDescriptions;

	public InvalidFilterDescription() {
		this.filterDescriptions = new HashSet<FilterDescription>();
	}

	@Override
	public void findIn(EObject eObject) {
		if (eObject instanceof FilterDescription) {
			FilterDescription filterDescription = (FilterDescription) eObject;
			if (filterDescription.eIsProxy()){
				filterDescriptions.add(filterDescription);
			}
		}
	}

	@Override
	public void findIn(Resource resource) {
		//Do nothing
	}

	@Override
	public Set<FilterDescription> getAnalysisResult() {
		return this.filterDescriptions;
	}

	@Override
	public Collection<IFeedbackMessage> getFeedbackAnalysisMessages() {
		Feedback feedback = new Feedback();

		for (FilterDescription filterDescription : getAnalysisResult()) {
			String message = Messages.INVALID_SIRIUS_FILTER_DESCRIPTION;
			message = message + Feedback.getRepresentationName(filterDescription);
			message = message + Feedback.getProxyURI(filterDescription);
			IFeedbackMessage feedbackMessage = new Feedback.FeedbackMessage(FeedbackLevel.ERROR, message);
			feedback.addFeedbackMessage(feedbackMessage);
		}
		return feedback.getFeedbackMessages();
	}
}
