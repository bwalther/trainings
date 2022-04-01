package graphs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class Node<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Node.class);
    private T value;
    private Set<Node<T>> neighbours;

    public Node(T value) {
        this.value = value;
        this.neighbours = new HashSet<>();
    }

    public void connect(Node<T> node) {
        if (this == node) throw new IllegalArgumentException("Cannot connect with itself");
        this.neighbours.add(node);
        node.neighbours.add(this);
    }

    public static <T> Optional<Node<T>> search(Node<T> start, T value) {
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(start);
        Set<Node<T>> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Node<T> current = queue.remove();
            LOGGER.debug("Visited node with value: {}", current.value);
            if (current.value.equals(value)) {
                return Optional.of(current);
            }
            visited.add(current);
            queue.addAll(current.neighbours);
            queue.removeAll(visited);
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        Node<Integer> start = new Node<>(10);
        Node<Integer> firstNeighbor = new Node<>(2);
        start.connect(firstNeighbor);

        Node<Integer> firstNeighborNeighbor = new Node<>(3);
        firstNeighbor.connect(firstNeighborNeighbor);
        firstNeighborNeighbor.connect(start);

        Node<Integer> secondNeighbor = new Node<>(4);
        start.connect(secondNeighbor);
        LOGGER.debug("Searching 3");
        Node.search(start, 3);
        LOGGER.debug("Searching 99");
        Node.search(start, 99);
    }

}


