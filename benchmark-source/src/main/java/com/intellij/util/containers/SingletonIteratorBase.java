package com.intellij.util.containers;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class SingletonIteratorBase<T> implements Iterator<T> {
  private boolean myVisited;

  @Override
  public final boolean hasNext() {
    return !myVisited;
  }

  @Override
  public final T next() {
    if (myVisited) {
      throw new NoSuchElementException();
    }
    myVisited = true;
    checkCoModification();
    return getElement();
  }

  protected abstract void checkCoModification();

  protected abstract T getElement();
}