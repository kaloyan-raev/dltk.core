/**
 * 
 */
package org.eclipse.dltk.compiler.task;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.Preferences;

public abstract class TaskTagUtils {

	private static final String TAG_SEPARATOR = ","; //$NON-NLS-1$
	private static final String PRIORITY_SEPARATOR = ";"; //$NON-NLS-1$

	public static List decodeTaskTags(String tags) {
		final String[] tagPairs = getTokens(tags, TAG_SEPARATOR);
		final List elements = new ArrayList();
		for (int i = 0; i < tagPairs.length; ++i) {
			final String[] values = getTokens(tagPairs[i], PRIORITY_SEPARATOR);
			final TodoTask task = new TodoTask();
			task.name = values[0];
			if (values.length == 2) {
				task.priority = values[1];
			} else {
				task.priority = TodoTask.PRIORITY_NORMAL;
			}
			elements.add(task);
		}
		return elements;

	}

	public static String encodeTaskTags(List elements) {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < elements.size(); ++i) {
			final TodoTask task = (TodoTask) elements.get(i);
			if (i > 0) {
				sb.append(TAG_SEPARATOR);
			}
			sb.append(task.name);
			sb.append(PRIORITY_SEPARATOR);
			sb.append(task.priority);
		}
		final String string = sb.toString();
		return string;
	}

	public static List getDefaultTags() {
		final List defaultTags = new ArrayList();
		defaultTags.add(new TodoTask("TODO", TodoTask.PRIORITY_NORMAL)); //$NON-NLS-1$
		defaultTags.add(new TodoTask("FIXME", TodoTask.PRIORITY_HIGH)); //$NON-NLS-1$ 
		defaultTags.add(new TodoTask("XXX", TodoTask.PRIORITY_NORMAL)); //$NON-NLS-1$
		return defaultTags;
	}

	public static void initializeDefaultValues(Preferences store) {
		store.setDefault(ITodoTaskPreferences.ENABLED, true);
		store.setDefault(ITodoTaskPreferences.CASE_SENSITIVE, true);
		store.setDefault(ITodoTaskPreferences.TAGS,
				encodeTaskTags(getDefaultTags()));
	}

	public static boolean isValidName(String newText) {
		return newText.indexOf(TAG_SEPARATOR.charAt(0)) < 0
				&& newText.indexOf(PRIORITY_SEPARATOR.charAt(0)) < 0;
	}

	private static String[] getTokens(String text, String separator) {
		final StringTokenizer tok = new StringTokenizer(text, separator);
		final int nTokens = tok.countTokens();
		final String[] res = new String[nTokens];
		for (int i = 0; i < res.length; i++) {
			res[i] = tok.nextToken().trim();
		}
		return res;
	}
}