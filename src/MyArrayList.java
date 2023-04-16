import java.util.Arrays;

    public class MyArrayList<T> implements MyList<T> {
        private static final int CONST = 10;
        private T[] array;
        private int size;

        public MyArrayList() {
            this(CONST);
        }

        public MyArrayList(int capacity) {
            array = (T[]) new Object[capacity];
            size = 0;
        }

        public void add(T item) {
            if (size == array.length) {
                resize();
            }
            array[size] = item;
            size++;
        }

        public void add(T item, int index) {
            if (index < 0  || index > size) {
                throw new IndexOutOfBoundsException();
            }
            if (size == array.length) {
                resize();
            }
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = item;
            size++;
        }

        public T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return array[index];
        }

        public T remove(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            T removedItem = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return removedItem;
        }

        public boolean remove(T item) {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(item)) {
                    remove(i);
                    return true;
                }
            }
            return false;
        }

        public void clear() {
            for (int i = 0; i < size; i++) {
                array[i] = null;
            }
            size = 0;
        }

        public int size() {
            return size;
        }

        public boolean contains(Object o) {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(o)) {
                    return true;
                }
            }
            return false;
        }

        public int lastIndexOf(Object o) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i].equals(o)) {
                    return i;
                }
            }
            return -1;
        }

        public int indexOf(Object o) {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(o)) {
                    return i;
                }
            }
            return -1;
        }

        public void sort() {
            Arrays.sort(array, 0, size);
        }

        private void resize() {
            T[] newArray = (T[]) new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

