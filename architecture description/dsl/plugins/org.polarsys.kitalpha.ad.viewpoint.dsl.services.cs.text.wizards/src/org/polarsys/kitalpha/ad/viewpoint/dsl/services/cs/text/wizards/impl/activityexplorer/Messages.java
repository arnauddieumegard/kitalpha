package org.polarsys.kitalpha.ad.viewpoint.dsl.services.cs.text.wizards.impl.activityexplorer;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	
	private static final String BUNDLE_NAME = "org.polarsys.kitalpha.ad.viewpoint.dsl.services.cs.text.wizards.impl.activityexplorer.messages"; //$NON-NLS-1$
	
	public static String ActivityExplorerFileCreatePage_Title;
	public static String ActivityExplorerFileCreationPage_Description;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}

}
