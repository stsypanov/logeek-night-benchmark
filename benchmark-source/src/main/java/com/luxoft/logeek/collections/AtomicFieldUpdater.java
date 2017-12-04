package com.luxoft.logeek.collections;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class AtomicFieldUpdater<ContainingClass, FieldType> {
  private static final Unsafe unsafe;

  static {
    try {
      Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      unsafe = (Unsafe) theUnsafe.get(null);
    } catch (Exception e) {
      throw new AssertionError(e);
    }
  }

  public static Unsafe getUnsafe() {
    return unsafe;
  }

  private final long offset;

  public static <T, V> AtomicFieldUpdater<T, V> forFieldOfType(Class<T> ownerClass,  Class<V> fieldType) {
    return new AtomicFieldUpdater<>(ownerClass, fieldType);
  }

  public static <T> AtomicFieldUpdater<T, Long> forLongFieldIn(Class<T> ownerClass) {
    return new AtomicFieldUpdater<>(ownerClass, long.class);
  }

  public static <T> AtomicFieldUpdater<T, Integer> forIntFieldIn(Class<T> ownerClass) {
    return new AtomicFieldUpdater<>(ownerClass, int.class);
  }

  private AtomicFieldUpdater(Class<ContainingClass> ownerClass,  Class<FieldType> fieldType) {
    Field found = getTheOnlyVolatileFieldOfClass(ownerClass, fieldType);
    offset = unsafe.objectFieldOffset(found);
  }

  private static <T,V> Field getTheOnlyVolatileFieldOfClass(Class<T> ownerClass,  Class<V> fieldType) {
    Field[] declaredFields = ownerClass.getDeclaredFields();
    Field found = null;
    for (Field field : declaredFields) {
      if ((field.getModifiers() & (Modifier.STATIC | Modifier.FINAL)) != 0) {
        continue;
      }
      if (fieldType.isAssignableFrom(field.getType())) {
        if (found == null) {
          found = field;
        }
        else {
          throw new IllegalArgumentException("Two fields of "+fieldType+" found in the "+ownerClass+": "+found.getName() + " and "+field.getName());
        }
      }
    }
    if (found == null) {
      throw new IllegalArgumentException("No (non-static, non-final) field of "+fieldType+" found in the "+ownerClass);
    }
    found.setAccessible(true);
//    if (!BitUtil.isSet(found.getModifiers(), Modifier.VOLATILE)) {
//      throw new IllegalArgumentException("Field " + found + " in the " + ownerClass + " must be volatile");
//    }
    return found;
  }

  public boolean compareAndSet(ContainingClass owner, FieldType expected, FieldType newValue) {
    return unsafe.compareAndSwapObject(owner, offset, expected, newValue);
  }

  public boolean compareAndSetLong(ContainingClass owner, long expected, long newValue) {
    return unsafe.compareAndSwapLong(owner, offset, expected, newValue);
  }

  public boolean compareAndSetInt(ContainingClass owner, int expected, int newValue) {
    return unsafe.compareAndSwapInt(owner, offset, expected, newValue);
  }

  public void set(ContainingClass owner, FieldType newValue) {
    unsafe.putObjectVolatile(owner, offset, newValue);
  }

  public FieldType get(ContainingClass owner) {
    //noinspection unchecked
    return (FieldType)unsafe.getObjectVolatile(owner, offset);
  }
}
