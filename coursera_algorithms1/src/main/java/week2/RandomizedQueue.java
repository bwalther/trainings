package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INITIAL_CAPACITY = 8;
    private Object[] a;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = new Object[INITIAL_CAPACITY];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size + 1 > a.length) {
            resize(a.length * 2);
        }
        a[size] = item;
        size++;
    }

    private void resize(int newSize) {
        Object[] b = new Object[newSize];
        System.arraycopy(a, 0, b, 0, a.length);
        a = b;
    }

    private void swap(int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size - 1 < a.length / 4 && a.length / 2 > INITIAL_CAPACITY) {
            resize(a.length / 2);
        }
        int idx = StdRandom.uniform(size);
        swap(idx, size - 1);
        //noinspection unchecked
        Item ret = (Item) a[size - 1];
        a[size - 1] = null;
        size--;
        return ret;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        //noinspection unchecked
        return (Item) a[StdRandom.uniform(size)];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        int[] idx;
        int current = 0;

        RandomizedQueueIterator() {
            idx = new int[size];
            for (int i = 0; i < size; i++) {
                idx[i] = i;
            }
            shuffle(idx);
        }

        private void shuffle(int[] a) {
            for (int i = 0; i < size; i++) {
                int rIdx = i + StdRandom.uniform(size - i);
                int swap = a[i];
                a[i] = a[rIdx];
                a[rIdx] = swap;
            }
        }

        @Override
        public boolean hasNext() {
            return current < idx.length;
        }

        @Override
        public Item next() {
            //noinspection unchecked
            if (!hasNext()) throw new NoSuchElementException();
            return (Item) a[idx[current++]];
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        if (!queue.isEmpty()) throw new IllegalStateException();
        queue.enqueue("One");
        queue.enqueue("Two");
        if (queue.isEmpty()) throw new IllegalStateException();
        if (queue.size() != 2) throw new IllegalStateException();
        System.out.println("Sample of 2:" + queue.sample());
        System.out.println("Dequeued 1 of 2:" + queue.dequeue());
        if (queue.size != 1) throw new IllegalStateException();
        queue.dequeue();
        if (!queue.isEmpty()) throw new IllegalStateException();
        queue.enqueue("One");
        queue.enqueue("Two");
        queue.enqueue("Three");
        queue.enqueue("Four");
        for (String s : queue) {
            System.out.println(s);
        }
    }
}
