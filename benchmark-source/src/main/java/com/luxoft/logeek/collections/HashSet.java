package com.luxoft.logeek.collections;

import java.util.Collection;

public class HashSet<E> extends java.util.HashSet<E> {
  public HashSet() { }

  public HashSet(Collection<? extends E> collection) {
    super(collection);
  }

  public HashSet(int i, float v) {
    super(i, v);
  }

  public HashSet(int i) {
    super(i);
  }

  @Override
  public boolean contains(Object o) {
    return size() != 0 && super.contains(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    if (size() == 0) return c.isEmpty();
    return super.containsAll(c);
  }

  public void clear() {
    if (size() == 0) return; // optimization
    super.clear();
  }
}