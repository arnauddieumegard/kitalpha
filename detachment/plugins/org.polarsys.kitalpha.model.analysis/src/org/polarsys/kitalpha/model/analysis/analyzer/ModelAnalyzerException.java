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
package org.polarsys.kitalpha.model.analysis.analyzer;

/**
 * @author Faycal Abka
 */
public class ModelAnalyzerException extends Throwable {

	private static final long serialVersionUID = 7014705563026180357L;
	
	public ModelAnalyzerException(String message){
		super(message);
	}
}
