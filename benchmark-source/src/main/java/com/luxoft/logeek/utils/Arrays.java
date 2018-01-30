package com.luxoft.logeek.utils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * Copy of {@link java.util.Arrays} with some functionality
 * modified for better performance
 */
public class Arrays {

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> List<T> asList(T... a) {
        return new ArrayList<>(a);
    }

    /**
     * @serial include
     */
    private static class ArrayList<E> extends AbstractList<E>
            implements RandomAccess, java.io.Serializable
    {
        private static final long serialVersionUID = -2764017481108945198L;
        private final E[] a;

        ArrayList(E[] array) {
            a = Objects.requireNonNull(array);
        }

        @Override
        public int size() {
            return a.length;
        }

        @Override
        public Object[] toArray() {
            int length = a.length;
            Object[] copy = new Object[length];
            System.arraycopy(a, 0, copy, 0, length);
            return copy;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a) {
            int size = size();
            if (a.length < size)
                return java.util.Arrays.copyOf(this.a, size,
                        (Class<? extends T[]>) a.getClass());
            System.arraycopy(this.a, 0, a, 0, size);
            if (a.length > size)
                a[size] = null;
            return a;
        }

        @Override
        public E get(int index) {
            return a[index];
        }

        @Override
        public E set(int index, E element) {
            E oldValue = a[index];
            a[index] = element;
            return oldValue;
        }

        @Override
        public int indexOf(Object o) {
            E[] a = this.a;
            if (o == null) {
                for (int i = 0; i < a.length; i++)
                    if (a[i] == null)
                        return i;
            } else {
                for (int i = 0; i < a.length; i++)
                    if (o.equals(a[i]))
                        return i;
            }
            return -1;
        }

        @Override
        public boolean contains(Object o) {
            return indexOf(o) >= 0;
        }

        @Override
        public Spliterator<E> spliterator() {
            return Spliterators.spliterator(a, Spliterator.ORDERED);
        }

        @Override
        public void forEach(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            for (E e : a) {
                action.accept(e);
            }
        }

        @Override
        public void replaceAll(UnaryOperator<E> operator) {
            Objects.requireNonNull(operator);
            E[] a = this.a;
            for (int i = 0; i < a.length; i++) {
                a[i] = operator.apply(a[i]);
            }
        }

        @Override
        public void sort(Comparator<? super E> c) {
            java.util.Arrays.sort(a, c);
        }

        @Override
        public Iterator<E> iterator() {
            return new ArrayItr<>(a);
        }

        /**
         * Methods overridden for better performance
         */
        @Override
        public int lastIndexOf(Object o) {
            if (o == null) {
                for (int i = size() - 1; i >= 0; i--)
                    if (a[i] == null)
                        return i;
            } else {
                for (int i = size() - 1; i >= 0; i--)
                    if (o.equals(a[i]))
                        return i;
            }
            return -1;
        }

        @Override
        protected void removeRange(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int hashCode() {
            return java.util.Arrays.hashCode(a);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof List))
                return false;

            List that = (List) o;

            if (that instanceof RandomAccess) {
                if (a.length != that.size()) {
                    return false;
                }

                for (int i = 0; i < a.length; i++) {
                    E o1 = a[i];
                    Object o2 = that.get(i);
                    if (o1 == null ? o2 != null : !o1.equals(o2)) {
                        return false;
                    }
                }
                return true;
            }

            return super.equals(o);
        }

        //        @Override
//        public List<E> subList(int fromIndex, int toIndex) {
//            return new SubList(this, fromIndex, toIndex);
//        }
//
//        private class SubList extends AbstractList<E> {
//            private final AbstractList<E> l;
//            private final int offset;
//            private int size;
//
//            SubList(AbstractList<E> list, int fromIndex, int toIndex) {
//                if (fromIndex < 0)
//                    throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
//                if (toIndex > list.size())
//                    throw new IndexOutOfBoundsException("toIndex = " + toIndex);
//                if (fromIndex > toIndex)
//                    throw new IllegalArgumentException("fromIndex(" + fromIndex +
//                            ") > toIndex(" + toIndex + ")");
//                l = list;
//                offset = fromIndex;
//                size = toIndex - fromIndex;
//                this.modCount = l.modCount;
//            }
//
//            public E set(int index, E element) {
//                rangeCheck(index);
//                checkForComodification();
//                E oldValue = Arrays.ArrayList.this.a[offset + index];
//                Arrays.ArrayList.this.a[offset + index] = element;
//                return oldValue;
//            }
//
//            public E get(int index) {
//                rangeCheck(index);
//                checkForComodification();
//                return Arrays.ArrayList.this.a[offset + index];
//            }
//
//            public int size() {
//                checkForComodification();
//                return size;
//            }
//
//            public void add(int index, E element) {
//                throw new UnsupportedOperationException();
//            }
//
//            public E remove(int index) {
//                throw new UnsupportedOperationException();
//            }
//
//            protected void removeRange(int fromIndex, int toIndex) {
//                throw new UnsupportedOperationException();
//            }
//
//            public boolean addAll(Collection<? extends E> c) {
//                throw new UnsupportedOperationException();
//            }
//
//            public boolean addAll(int index, Collection<? extends E> c) {
//                throw new UnsupportedOperationException();
//            }
//
//            public Iterator<E> iterator() {
//                return listIterator();
//            }
//
//            public ListIterator<E> listIterator(final int index) {
//                checkForComodification();
//                rangeCheckForAdd(index);
//
//                return new ListIterator<E>() {
//                    private final ListIterator<E> i = l.listIterator(index + offset);
//
//                    public boolean hasNext() {
//                        return nextIndex() < size;
//                    }
//
//                    public E next() {
//                        if (hasNext())
//                            return i.next();
//                        else
//                            throw new NoSuchElementException();
//                    }
//
//                    public boolean hasPrevious() {
//                        return previousIndex() >= 0;
//                    }
//
//                    public E previous() {
//                        if (hasPrevious())
//                            return i.previous();
//                        else
//                            throw new NoSuchElementException();
//                    }
//
//                    public int nextIndex() {
//                        return i.nextIndex() - offset;
//                    }
//
//                    public int previousIndex() {
//                        return i.previousIndex() - offset;
//                    }
//
//                    public void remove() {
//                        throw new UnsupportedOperationException();
//                    }
//
//                    public void set(E e) {
//                        i.set(e);
//                    }
//
//                    public void add(E e) {
//                        i.add(e);
//                        SubList.this.modCount = l.modCount;
//                        size++;
//                    }
//                };
//            }
//
//            public List<E> subList(int fromIndex, int toIndex) {
//                return new SubList(this, fromIndex, toIndex);
//            }
//
//            private void rangeCheck(int index) {
//                if (index < 0 || index >= size)
//                    throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//            }
//
//            private void rangeCheckForAdd(int index) {
//                if (index < 0 || index > size)
//                    throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//            }
//
//            private String outOfBoundsMsg(int index) {
//                return "Index: " + index + ", Size: " + size;
//            }
//
//            private void checkForComodification() {
//                if (this.modCount != l.modCount)
//                    throw new ConcurrentModificationException();
//            }
//        }

    }

    private static class ArrayItr<E> implements Iterator<E> {
        private int cursor;
        private final E[] a;

        ArrayItr(E[] a) {
            this.a = a;
        }

        @Override
        public boolean hasNext() {
            return cursor < a.length;
        }

        @Override
        public E next() {
            int i = cursor;
            if (i >= a.length) {
                throw new NoSuchElementException();
            }
            cursor = i + 1;
            return a[i];
        }
    }
}
