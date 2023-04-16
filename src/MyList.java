public interface MyList<T> {
    void add(T item);
    void add(T item, int index);
    T get(int index);
    T remove(int index);
    boolean remove(T index);
    void clear();
    int size();
    boolean contains(Object o);
    int lastIndexOf(Object o);
    int indexOf(Object o);
    void sort();
}
