/*******************************************************************************
 * Copyright (c) 2009 xored software, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.ui.text.templates;

import org.eclipse.jface.text.templates.TemplateContextType;

public interface ICodeTemplateCategory {

	String getName();

	boolean isGroup();

	/**
	 * Returns {@link TemplateContextType}s for this group.
	 * 
	 * @return
	 */
	TemplateContextType[] getTemplateContextTypes();

	int getPriority();

}
