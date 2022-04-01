package graphs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DirectedWeightedGraph {
    private static final Logger LOG = LoggerFactory.getLogger(DirectedWeightedGraph.class);
    List<List<Node>> adjList = new ArrayList<>();

    //Graph Constructor
    public DirectedWeightedGraph(List<Edge> edges) {
        // adjacency list memory allocation
        for (int i = 0; i < edges.size(); i++)
            adjList.add(i, new ArrayList<>());

        // add edges to the graph
        for (Edge e : edges) {
            // allocate new node in adjacency List from src to dest
            adjList.get(e.src).add(new Node(e.dest, e.weight));
        }
    }

    // print adjacency list for the graph
    public static void printGraph(DirectedWeightedGraph graph) {
        int src_vertex = 0;
        int list_size = graph.adjList.size();

        System.out.println("The contents of the graph:");
        while (src_vertex < list_size) {
            //traverse through the adjacency list and print the edges
            for (Node edge : graph.adjList.get(src_vertex)) {
                System.out.print("Vertex:" + src_vertex + " ==> " + edge.value +
                        " (" + edge.weight + ")\t");
            }

            System.out.println();
            src_vertex++;
        }
    }

    public static List<Node> breathFirstTraverse(DirectedWeightedGraph graph, int start) {
        List<Node> traversal = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer current = queue.remove();
            if (!visited.contains(current)) {
                LOG.debug("Visiting node:" + current);
                visited.add(current);
                for (Node neighbor : graph.adjList.get(current)) {
                    if (!visited.contains(neighbor.value)) {
                        queue.add(neighbor.value);
                    }
                }
            }
        }
        return traversal;
    }

    public static void main(String[] args) {
        // define edges of the graph
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 2),
                new Edge(0, 2, 4),
                new Edge(1, 2, 4),
                new Edge(2, 0, 5),
                new Edge(2, 1, 4),
                new Edge(3, 2, 3),
                new Edge(4, 3, 3),
                new Edge(4, 5, 1),
                new Edge(5, 4, 3));

        // call graph class Constructor to construct a graph
        DirectedWeightedGraph graph = new DirectedWeightedGraph(edges);

        // print the graph as an adjacency list
        DirectedWeightedGraph.printGraph(graph);
        DirectedWeightedGraph.breathFirstTraverse(graph, 5);
    }

    // node of adjacency list
    static class Node {
        int value, weight;

        Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    //class to store edges of the weighted graph
    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}
