package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size = 0;

    private static class Node<Item> {
        Item data;
        Node<Item> previous;
        Node<Item> next;

        Node(Item item) {
            this.data = item;
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item to add cannot be null");
        Node<Item> node = new Node<>(item);
        if (size == 0) {
            first = node;
            last = node;
        } else {
            first.previous = node;
            node.next = first;
            first = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item to add cannot be null");
        Node<Item> node = new Node<>(item);
        if (size == 0) {
            first = node;
        } else {
            last.next = node;
            node.previous = last;
        }
        last = node;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException("deque is empty");
        Item ret = first.data;
        if (size == 1) {
            last = null;
            first = null;
        } else if (size == 2) {
            disconnectFirstLast();
            first = last;
        } else {
            if (first.next != null) {
                first.next.previous = null;
            }
            first = first.next;
        }
        size--;
        return ret;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException("deque is empty");
        Item ret = last.data;
        if (size == 1) {
            last = null;
            first = null;
        } else if (size == 2) {
            disconnectFirstLast();
            last = first;
        } else {
            if (last.previous != null) {
                last.previous.next = null;
            }
            last = last.previous;
        }
        size--;
        return ret;
    }

    private class DequeIterator implements Iterator<Item> {

        Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.data;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        System.out.println("Initial state: size:" + deque.size());
        deque.addFirst("First");
        System.out.println("Size after addingOne" + deque.size());
        if (!deque.removeFirst().equals("First")) throw new IllegalStateException();
        System.out.println("Size after removing: " + deque.size());
        deque.addLast("Last");
        deque.addFirst("First");
        if (deque.size() != 2) throw new IllegalStateException("size should be 2");
        if (!deque.removeLast().equals("Last")) throw new IllegalStateException();
        if (!deque.removeLast().equals("First")) throw new IllegalStateException();
        if (!deque.isEmpty()) throw new IllegalStateException();

        deque.addFirst("One");
        deque.addFirst("Two");
        deque.addFirst("Three");
        deque.addLast("Four");
        deque.addLast("Five");
        deque.addLast("Six");
        for (String s : deque) {
            System.out.println(s);
        }

    }


    private void disconnectFirstLast() {
        last.previous = null;
        first.next = null;
    }

}
