package graphs;

import java.util.*;

public class UndirectedGraph {
    private int size;   // No. of vertices
    private List<Integer> adjList[]; //Adjacency Lists

    // graph Constructor:number of vertices in graph are passed
    UndirectedGraph(int v) {
        size = v;
        adjList = new ArrayList[v];
        for (int i = 0; i < v; ++i) {
            adjList[i] = new ArrayList<>();
        }
    }

    void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v);
    }

    // BFS traversal from the root_node
    void BFS(int root) {
        // initially all vertices are not visited
        boolean visited[] = new boolean[size];

        // BFS queue
        Queue<Integer> queue = new ArrayDeque<>();

        // current node = visited, insert into queue
        visited[root] = true;
        queue.add(root);

        Queue<Integer> q = new LinkedList<>();

        while (queue.size() != 0) {
            // deque an entry from queue and process it
            root = queue.poll();
            System.out.print(root + " ");

            // get all adjacent nodes of current node and process
            for (Integer n : adjList[root]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    void dfsIterative(int root) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Integer vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                for (Integer v : adjList[vertex]) {
                    stack.push(v);
                }
            }
        }
    }

    boolean isCyclicUtil(int v, boolean visited[], int parent) {
        visited[v] = true;
        Iterator<Integer> it = adjList[v].iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            if (!visited[i] && isCyclicUtil(i, visited, v)) {
                return true;
            } else if (i != parent) {
                return true;
            }
        }
        return false;
    }

    boolean isCyclic() {
        boolean visited[] = new boolean[this.size];
        for (int u = 0; u < this.size; u++) {
            // Don't recur for u if already visited
            if (!visited[u] && isCyclicUtil(u, visited, -1)) {
                return true;
            }
        }
        return false;
    }

    // A function used by DFS
    void dfsRecUtil(int v, boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> i = this.adjList[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                dfsRecUtil(n, visited);
        }
    }

    void dfsRecursive(int v) {
        boolean visited[] = new boolean[this.size];
        List<String> a = Arrays.asList("A", "B");
        List<String> b = List.of("A", "B");

        // Call the recursive helper
        // function to print DFS
        // traversal
        dfsRecUtil(v, visited);
    }

    void printShortestPath(int source, int dest) {
        int prev[] = new int[size];
        int dist[] = new int[size];
        for (int i = 0; i < size; i++) {
            prev[i] = -1;
            dist[i] = Integer.MAX_VALUE;
        }
        BFS(source, dest, prev, dist);
        int i = dest;
        LinkedList<Integer> crawl = new LinkedList<>();
        while (prev[i] != -1) {
            crawl.add(prev[i]);
            i = prev[i];
        }
        System.out.print("Path: ");
        Iterator<Integer> it = crawl.descendingIterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " -> ");
        }
        System.out.print(dest);
        System.out.println("\nDist: " + dist[dest]);
    }

    void BFS(int source, int dest, int[] prev, int[] dist) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        dist[source] = 0;
        boolean[] visited = new boolean[size];

        visited[source] = true;

        while (!queue.isEmpty()) {
            Integer node = queue.remove();
            for (Integer c : adjList[node]) {
                if (!visited[c]) {
                    visited[c] = true;
                    queue.add(c);
                    prev[c] = node;
                    dist[c] = dist[node] + 1;
                    if (c == dest) {
                        queue.clear();
                        break;
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        //create a graph with 5 vertices
        UndirectedGraph g = new UndirectedGraph(10);
        //add edges to the graph
//        g.addEdge(0, 1);
//        g.addEdge(0, 2);
//        g.addEdge(0, 3);
//        g.addEdge(1, 2);
//        g.addEdge(2, 4);

//        g.addEdge(0, 1);
//        g.addEdge(0, 2);
//        g.addEdge(1, 2);
//        g.addEdge(2, 3);
//        g.addEdge(3, 4);
//        g.addEdge(4, 5);

        // shortest path graph 1
//        g.addEdge(0, 1);
//        g.addEdge(0, 2);
//        g.addEdge(1, 3);
//        g.addEdge(1, 4);
//        g.addEdge(2, 5);
//        g.addEdge(2, 6);
//        g.addEdge(3, 7);
//        g.addEdge(2, 7);

        // shortest path graph 2
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(2, 7);
        g.addEdge(3, 7);
        g.addEdge(7, 9);
        g.addEdge(0, 9);


        //print BFS sequence
        int startVertex = 0;
        System.out.println("Breadth-first traversal of graph with " + startVertex + " as starting vertex:");
        g.BFS(startVertex);
        int source = 1;
        int dest = 9;
        System.out.println("\nShortest Path between " + source + " and " + dest);
        g.printShortestPath(source, dest);


        System.out.println("\nDepth-first traversal of graph with " + startVertex + " as starting vertex:");
        g.dfsIterative(startVertex);
    }
}
