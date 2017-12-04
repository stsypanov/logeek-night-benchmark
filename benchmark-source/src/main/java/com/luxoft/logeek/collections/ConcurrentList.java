package com.luxoft.logeek.collections;

import java.util.Collection;
import java.util.List;

public interface ConcurrentList<E> extends List<E> {
  /**
   * Append the element if not present.
   *
   * @return true if the element was added
   */
  boolean addIfAbsent(E e);

  /**
   * Appends all of the elements in the specified collection that
   * are not already contained in this list, to the end of
   * this list, in the order that they are returned by the
   * specified collection's iterator.
   *
   * @return the number of elements added
   */
  int addAllAbsent(Collection<? extends E> c);
}
