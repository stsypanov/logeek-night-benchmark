package com.luxoft.logeek.collections;

import com.sun.istack.internal.NotNull;
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

  @NotNull
  public static Unsafe getUnsafe() {
    return unsafe;
  }

  private final long offset;

  @NotNull
  public static <T, V> AtomicFieldUpdater<T, V> forFieldOfType(@NotNull Class<T> ownerClass, @NotNull Class<V> fieldType) {
    return new AtomicFieldUpdater<T, V>(ownerClass, fieldType);
  }

  @NotNull
  public static <T> AtomicFieldUpdater<T, Long> forLongFieldIn(@NotNull Class<T> ownerClass) {
    return new AtomicFieldUpdater<T, Long>(ownerClass, long.class);
  }

  @NotNull
  public static <T> AtomicFieldUpdater<T, Integer> forIntFieldIn(@NotNull Class<T> ownerClass) {
    return new AtomicFieldUpdater<T, Integer>(ownerClass, int.class);
  }

  private AtomicFieldUpdater(@NotNull Class<ContainingClass> ownerClass, @NotNull Class<FieldType> fieldType) {
    Field found = getTheOnlyVolatileFieldOfClass(ownerClass, fieldType);
    offset = unsafe.objectFieldOffset(found);
  }

  @NotNull
  private static <T,V> Field getTheOnlyVolatileFieldOfClass(@NotNull Class<T> ownerClass, @NotNull Class<V> fieldType) {
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

  public boolean compareAndSet(@NotNull ContainingClass owner, FieldType expected, FieldType newValue) {
    return unsafe.compareAndSwapObject(owner, offset, expected, newValue);
  }

  public boolean compareAndSetLong(@NotNull ContainingClass owner, long expected, long newValue) {
    return unsafe.compareAndSwapLong(owner, offset, expected, newValue);
  }

  public boolean compareAndSetInt(@NotNull ContainingClass owner, int expected, int newValue) {
    return unsafe.compareAndSwapInt(owner, offset, expected, newValue);
  }

  public void set(@NotNull ContainingClass owner, FieldType newValue) {
    unsafe.putObjectVolatile(owner, offset, newValue);
  }

  public FieldType get(@NotNull ContainingClass owner) {
    //noinspection unchecked
    return (FieldType)unsafe.getObjectVolatile(owner, offset);
  }
}
