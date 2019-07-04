/*******************************************************************************
 * Copyright (c) 2014, 2019 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *   Thales Global Services S.A.S - initial API and implementation
 ******************************************************************************/

package org.polarsys.kitalpha.doc.gen.business.core.extension.page;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;

/**
 * An object representation of a contribution to the extension point 
 * org.polarsys.kitalpha.doc.gen.business.core.contentExtension.pageExtension.
 * @author Boubekeur Zendagui
 */
public class PageExtensionElement {
	
	/**
	 * <p>
	 * Status of a given extension. Each extensions had a default status set in the extension point. 
	 * This status can be modified by end-user. 
	 * </p>
	 * <p>
	 * The possibles values are:
	 * <ul>
	 * <li><code>Active</code>: The extension is active </li>
	 * <li><code>Inactive</code>: The extension is inactive. </li>
	 * <li><code>Unknown</code>: the extensions status is unknown  </li>
	 * </ul>
	 * </p>
	 * @author Boubekeur Zendagui
	 */
	public enum PageExtensionActivationStatus {
		Active,
		Inactive,
		Unknown;
		
		public static PageExtensionActivationStatus getStatus(String status){
			if (status.equalsIgnoreCase("Active")) {
				return Active;
			} 
			if (status.equalsIgnoreCase("Inactive")) {
				return Inactive;
			}
			return Unknown;
		}
	}
	
	private String name;
	private String description;
	private String domain;
	private String category;
	private List<URI> pageURI = new ArrayList<URI>();
	private List<URI> sidebarURI = new ArrayList<URI>();
	private PageExtensionActivationStatus defaultActivationStatus ;
	private PageExtensionActivationStatus currentActivationstatus ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<URI> getPageURI() {
		return pageURI;
	}
	public List<URI> getSidebarURI() {
		return sidebarURI;
	}
	public PageExtensionActivationStatus getDefaultActivationStatus() {
		return defaultActivationStatus;
	}
	public void setDefaultActivationStatus(PageExtensionActivationStatus defaultStatus) {
		this.defaultActivationStatus = defaultStatus;
	}
	public PageExtensionActivationStatus getCurrentActivactionStatus() {
		return currentActivationstatus != null ? currentActivationstatus : defaultActivationStatus;
	}
	public void setCurrentActivactionStatus(PageExtensionActivationStatus status) {
		this.currentActivationstatus = status;
	}

}
