package com.luxoft.logeek.benchmark.contains;

import java.util.Map;

public class HashMap<K, V> extends java.util.HashMap<K, V> {
  public HashMap() { }

  public HashMap(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor);
  }

  public HashMap(int initialCapacity) {
    super(initialCapacity);
  }

  public <K1 extends K, V1 extends V> HashMap(Map<? extends K1, ? extends V1> map) {
    super(map);
  }

  @Override
  public boolean containsKey(Object key) {
    if (size() == 0) return false;
    return super.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    if (size() == 0) return false;
    return super.containsValue(value);
  }

  @Override
  public V get(Object key) {
    if (size() == 0) return null;
    return super.get(key);
  }

  @Override
  public V remove(Object key) {
    if (size() == 0) return null;
    return super.remove(key);
  }

  @Override
  public boolean remove(Object key, Object value) {
    if (size() == 0) return true;
    return super.remove(key, value);
  }

  @Override
  public void clear() {
    if (size() == 0) return; // optimization
    super.clear();
  }
}
