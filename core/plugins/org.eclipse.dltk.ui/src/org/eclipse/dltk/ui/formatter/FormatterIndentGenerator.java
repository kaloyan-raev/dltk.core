/*******************************************************************************
 * Copyright (c) 2008 xored software, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.ui.formatter;

import java.util.Arrays;


public class FormatterIndentGenerator implements IFormatterIndentGenerator {

	private final char[] chars;
	private final int indentationSize;

	public FormatterIndentGenerator(char ch, int indentationSize) {
		this.chars = new char[256];
		Arrays.fill(chars, ch);
		this.indentationSize = indentationSize;
	}

	public void generateIndent(final int indentLevel, StringBuffer target) {
		if (indentLevel > 0) {
			int size = indentLevel * indentationSize;
			while (size > 0) {
				final int step = Math.min(size, chars.length);
				target.append(chars, 0, step);
				size -= step;
			}
		}
	}

}