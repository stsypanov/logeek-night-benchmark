package com.intellij.util.containers;


import java.util.Collection;

public class ContainerUtils {
	
	public static <T, A extends T, C extends Collection<T>> C addAll(C collection, A... elements) {
		//noinspection ManualArrayToCollectionCopy
		for (T element : elements) {
			collection.add(element);
		}
		return collection;
	}
}
