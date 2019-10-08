/*******************************************************************************
 * Copyright (c) 2016 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *   Thales Global Services S.A.S - initial API and implementation
 ******************************************************************************/
package org.polarsys.kitalpha.doc.gen.business.ecore.egf.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.egf.model.pattern.PatternContext;
import org.eclipse.egf.pattern.query.IQuery;
import org.polarsys.kitalpha.doc.gen.business.ecore.helpers.EcoreFileNameService;

/**
 * 
 * @author Faycal Abka
 *
 */
public class InjectFileNameService implements IQuery {

	@Override
	public List<Object> execute(ParameterDescription parameter, Map<String, String> queryCtx, PatternContext context) {
		List<Object> result = new ArrayList<Object>();
		result.add(EcoreFileNameService.INSTANCE);
		return result;
	}

}
