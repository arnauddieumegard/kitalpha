/*******************************************************************************
 * Copyright (c) 2018  Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *  Thales Global Services S.A.S - initial API and implementation
 *******************************************************************************/
package org.polarsys.kitalpha.massactions.core.config;

import org.polarsys.kitalpha.massactions.core.table.layer.IMAComponent;

/**
 * A configuration interface for a {@link IMAComponent}.
 * 
 * @author Sandu Postaru
 * 
 */
public interface IMAConfiguration extends IMAComponent {

  /**
   * Apply the current configuration.
   */
  void apply();
}