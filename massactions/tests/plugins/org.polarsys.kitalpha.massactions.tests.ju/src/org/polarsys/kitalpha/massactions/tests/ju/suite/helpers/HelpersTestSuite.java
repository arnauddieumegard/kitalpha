/*******************************************************************************
 * Copyright (c) 2018 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S - initial API and implementation
 *******************************************************************************/

package org.polarsys.kitalpha.massactions.tests.ju.suite.helpers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.polarsys.kitalpha.massactions.tests.ju.test.helpers.CommonFeaturesTests;
import org.polarsys.kitalpha.massactions.tests.ju.test.helpers.UnfoldRowObjectTests;

/**
 * 
 * @author Sandu Postaru
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ CommonFeaturesTests.class, UnfoldRowObjectTests.class })
public class HelpersTestSuite {

}
