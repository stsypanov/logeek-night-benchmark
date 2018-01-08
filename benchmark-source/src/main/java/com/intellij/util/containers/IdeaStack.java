package com.intellij.util.containers;


import java.util.*;

/**
 * A drop-in replacement for {@link java.util.Stack} based on {@link ArrayList} (instead of {@link Vector})
 * and therefore is (1) not synchronized and (2) faster.
 *
 * @author max
 */
public class IdeaStack<T> extends ArrayList<T> {
  public IdeaStack() { }

  public IdeaStack(int initialCapacity) {
    super(initialCapacity);
  }

  public IdeaStack( Collection<T> init) {
    super(init);
  }

  public IdeaStack( T... items) {
    for (T item : items) {
      push(item);
    }
  }

  public T push(T t) {
    add(t);
    return t;
  }

  public T peek() {
    final int size = size();
    if (size == 0) throw new EmptyStackException();
    return get(size - 1);
  }

  public T pop() {
    final int size = size();
    if (size == 0) throw new EmptyStackException();
    return remove(size - 1);
  }

  public T tryPop() {
    return isEmpty() ? null : pop();
  }

  public boolean empty() {
    return isEmpty();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof RandomAccess && o instanceof List) {
      List other = (List)o;
      if (size() != other.size()) {
        return false;
      }

      for (int i = 0; i < other.size(); i++) {
        Object o1 = other.get(i);
        Object o2 = get(i);
        if (!(o1 == null ? o2 == null : o1.equals(o2))) {
          return false;
        }
      }

      return true;
    }

    return super.equals(o);
  }
}