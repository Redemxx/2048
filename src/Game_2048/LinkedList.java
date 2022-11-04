package Game_2048;

public class LinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList() {
        this(0);
    }

    public LinkedList(int initialCapacity) {
        this.size = initialCapacity;
        head = new Node<>();
        tail = head;
        if (initialCapacity <= 0) {
            return;
        }

        Node<E> temp_node = head;
        for (int a = 1; a < initialCapacity; a++){
            temp_node.next = new Node<>();
            temp_node = temp_node.next;
        }
        tail = temp_node;
    }

    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
    }

    // ADD (E e) rtn; bool
    public void add(E object) {
        if (size  == 0) {
            head = new Node<>(object);
            tail = head;
            ++size;
            return;
        }
        tail.next = new Node<>(object);
        tail = tail.next;
        ++size;
    }

    public void add(int index, E object) {
        if (index == size) {
            add(object);
            return;
        }
        checkIndex(index);

        Node<E> new_node = new Node<>(object);
        Node<E> node = head;
        Node<E> first_node = null;
        Node<E> index_node = null;
        for (int i = 0; i < index; i++) {
            if (i == index-1) {
                first_node = node;
            }
            node = node.next;
        }

        if (first_node == null) {
            first_node = head;
        }

        if (index == 0)
            head = new_node;
        else
            first_node.next = new_node;

        if (index == size) {
            tail = first_node.next;
            ++size;
        }else{
            new_node.next = node;
            ++size;
        }
    }

    // clear ()
    // Clears list; starts as new list
    public void clear() {
        head = null;
        size = 0;
    }

    // get (int index) rtn; E at index
    public E get(int index) {
        checkIndex(index);

        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    public void set(int index, E data) {
        checkIndex(index);

        Node<E> current_node = head;
        for (int a = 0; a < index; a++) {
            current_node = current_node.next;
        }
        current_node.data = data;
    }

    // remove (int index) rtn; E removed
    public E remove(int index) {
        checkIndex(index);
        Node<E> removed_node;

        if (index == 0) {
            removed_node = head;
            head = head.next;
            --size;
            return removed_node.data;
        }

        Node<E> node = head;
        Node<E> first_node = null;
        Node<E> index_node = null;
        for (int i = 0; i < index; i++) {
            if (i == index-1) {
                first_node = node;
            }
            node = node.next;
        }
        removed_node = node;

        if (index == size-1) {
            first_node.next = null;
        }else{
            first_node.next = node.next;
        }
        --size;
        return removed_node.data;
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        Node<E> current = head;
        for (int a = 0; a < size; a++) {
            out.append(current.data).append("\n");
            current = current.next;
        }
        return out.toString();
    }

    // size() rtn; int size of list
    private class Node<EE> {
        public EE data;
        public Node<EE> next;

        public Node(EE object) {
            data = object;
        }

        public Node() {}
    }

//    public static void main(String[] args) {
//        LinkedList<String> list = new LinkedList<>();
//        list.add("First");
//        list.add("Third");
//        list.add(1, "Second");
//        list.add(0, "Zero");
//        list.remove(2);
//        list.add(3, "Test");
//        list.set(1, "I replaced first");
//        list.set(3, "May the 4th be with you");
//
//        System.out.println(list);
//
//        LinkedList<String> list2 = new LinkedList<>();
//        list2.add(0, "Empty index add");
//
//        System.out.println(list2);
//
//    }
}
