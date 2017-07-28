package de.thb.fbi.project.koma.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//TODO: possible static final class
public class ListUtils {

	/*
	 * returns a list of fields in String (methods and/or classNames)
	 */
	public List<String> resolveEntityMethods(Object object) {

		Set<String> hs = new HashSet<>();
		String method = null;
		for (Method m : object.getClass().getSuperclass().getDeclaredMethods()) {
			method = m.getName();

			if (method.startsWith("is")) {

				hs.add(getBooleanName(method));
			}
			if (method.startsWith("to")) {
				// do nothing
			} else {
				hs.add(getMethodName(method));
			}
		}

		for (Method m : object.getClass().getDeclaredMethods()) {
			method = m.getName();

			if (method.startsWith("is")) {
				hs.add(getBooleanName(method));
			}
			if (method.startsWith("to")) {
				// do nothing
			} else {
				hs.add(getMethodName(method));
			}
		}
		return new ArrayList<>(hs);
	}

	public String getMethodName(String aMethod) {
		aMethod = Character.toLowerCase(aMethod.charAt(3)) + aMethod.substring(4);
		return aMethod;
	}

	public String getBooleanName(String aMethod) {
		aMethod = Character.toLowerCase(aMethod.charAt(2)) + aMethod.substring(3);
		return aMethod;
	}
}
