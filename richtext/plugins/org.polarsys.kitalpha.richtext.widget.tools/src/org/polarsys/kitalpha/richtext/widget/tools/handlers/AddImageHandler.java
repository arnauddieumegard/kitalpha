/*******************************************************************************
 * Copyright (c) 2017 Thales Global Services S.A.S.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Thales Global Services S.A.S - initial API and implementation
 ******************************************************************************/
package org.polarsys.kitalpha.richtext.widget.tools.handlers;

import org.eclipse.swt.widgets.Display;
import org.polarsys.kitalpha.richtext.common.intf.MDERichTextWidget;
import org.polarsys.kitalpha.richtext.nebula.widget.toolbar.MDERichTextToolbarItemHandler;
import org.polarsys.kitalpha.richtext.widget.tools.dialogs.MDEAddImageDialog;
import org.polarsys.kitalpha.richtext.widget.tools.manager.ImageManagerImpl;

/**
 * @author Faycal Abka
 */
public class AddImageHandler implements MDERichTextToolbarItemHandler {

	@Override
	public void execute(MDERichTextWidget richText) {
		MDEAddImageDialog dialog = new MDEAddImageDialog(Display.getCurrent().getActiveShell(), richText, new ImageManagerImpl(richText));
		dialog.open();
	}

}
