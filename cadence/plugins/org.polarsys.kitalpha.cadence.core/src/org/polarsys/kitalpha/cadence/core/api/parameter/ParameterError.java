/*******************************************************************************
 * Copyright (c) 2014 Thales Global Services S.A.S.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Thales Global Services S.A.S - initial API and implementation
 ******************************************************************************/
package org.polarsys.kitalpha.cadence.core.api.parameter;


/**
 * This class allows to give a reason, when a org.polarsys.kitalpha.cadence.core.api.parameter value is not correct.
 * @author Guillaume Gebhart
 */
public final class ParameterError<T> {

	private String reason = "?"; //$NON-NLS-1$
	private GenericParameter<T> parameter = null;

	/**
	 * @param reason
	 * @param org.polarsys.kitalpha.cadence.core.api.parameter
	 */
	public ParameterError(GenericParameter<T> parameter, String reason) {
		this.reason = reason;
		this.parameter = parameter;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(final String reason) {
		this.reason = reason;
	}

	/**
	 * @return the org.polarsys.kitalpha.cadence.core.api.parameter
	 */
	public GenericParameter<T> getParameter() {
		return parameter;
	}

	/**
	 * @param org.polarsys.kitalpha.cadence.core.api.parameter the org.polarsys.kitalpha.cadence.core.api.parameter to set
	 */
	public void setParameter(final GenericParameter<T> parameter) {
		this.parameter = parameter;
	}

}
