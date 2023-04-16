public class MyLinkedList<T> implements MyList<T> {
    private Node<T> head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public void add(T item) {
        add(item, size);
    }

    public void add(T item, int index) {
        if (index < 0  || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> newNode = new Node<T>(item);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> prevNode = getNode(index - 1);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0  || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = getNode(index);
        return node.data;
    }

    public T remove(int index) {
        if (index < 0  || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> removedNode;
        if (index == 0) {
            removedNode = head;
            head = head.next;
        } else {
            Node<T> prevNode = getNode(index - 1);
            removedNode = prevNode.next;
            prevNode.next = removedNode.next;
        }
        size--;
        return removedNode.data;
    }

    public boolean remove(T item) {
        Node<T> prevNode = null;
        Node<T> currNode = head;
        while (currNode != null) {
            if (currNode.data.equals(item)) {
                if (prevNode == null) {
                    head = currNode.next;
                } else {
                    prevNode.next = currNode.next;
                }
                size--;
                return true;
            }
            prevNode = currNode;
            currNode = currNode.next;
        }
        return false;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        Node<T> node = head;
        while (node != null) {
            if (node.data.equals(o)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (node.data.equals(o)) {
                lastIndex = i;
            }
            node = node.next;
        }
        return lastIndex;
    }

    public int indexOf(Object o) {
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (node.data.equals(o)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    public void sort() {
        head = mergeSort(head);
    }

    private Node<T> mergeSort(Node<T> node) {
        if (node == null  || node.next == null) {
            return node;
        }
        Node<T> middle = getMiddle(node);
        Node<T> nextOfMiddle = middle.next;
        middle.next = null;
        Node<T> left = mergeSort(node);
        Node<T> right = mergeSort(nextOfMiddle);
        Node<T> sortedList = merge(left, right);
        return sortedList;
    }

    private Node<T> merge(Node<T> left, Node<T> right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        Node<T> result = null;
        if (((Comparable<T>)left.data).compareTo(right.data) <= 0) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }

    private Node<T> getMiddle(Node<T> node) {
        if (node == null) {
            return null;
        }
        Node<T> slow = node;
        Node<T> fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private Node<T> getNode(int index) {
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}