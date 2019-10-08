/*******************************************************************************
 * Copyright (c) 2014 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *   Thales Global Services S.A.S - initial API and implementation
 ******************************************************************************/

package org.polarsys.kitalpha.ad.af.dsl.services.preference.helper;

import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.kitalpha.ad.af.dsl.services.preference.Activator;

/**
 * @author Boubekeur Zendagui
 */

public class AFRootProjectNamePreferencesHelper {
	
	private final static IPreferenceStore store = Activator.getDefault().getPreferenceStore();

	/**
	 * This get the value customized by user
	 * 
	 * @return a root project name provided by user
	 */
	public static String getCustomizedValue() {
		return store.getString(AFDslPreferenceConstant.AF_ROOT_PROJECT_NAME__VALUE);
	}

	/**
	 * Set the property to it default value
	 */
	public static void restorDefautValues() {
		store.setDefault(AFDslPreferenceConstant.AF_ROOT_PROJECT_NAME__VALUE,
				         AFDslPreferenceConstant.AF_ROOT_PROJECT_NAME__DEFAULT_VALUE);
	}
}
