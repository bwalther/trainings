package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetectCycleDirectedGraphDFS {


    private static class Graph {

        private int nodes;
        private List<List<Integer>> adjlist;
        private boolean visited[];

        // inpath stores the visited nodes in the traversal path before backtracking
        // for finding cycle in a directed graph.
        private boolean inpath[];

        boolean cycle_present;

        Graph(int arg_nodes) {

            nodes = arg_nodes;

            adjlist = new ArrayList<>(nodes);
            for (int i = 0; i < nodes; i++) {
                adjlist.add(new ArrayList<>());
            }

            visited = new boolean[nodes];
            inpath = new boolean[nodes];

            cycle_present = false;
        }

        public void AddEdge(int src, int dst, boolean bidirectional) {

            adjlist.get(src).add(dst);

            if (bidirectional)
                adjlist.get(dst).add(src);
        }

        // Function detects cycle in a directed graph
        // using DFS technique at its core.
        public void DetectCycle(int src) {

            visited[src] = true;
            // Set the flag for the vertex visited in traversal path
            //
            inpath[src] = true;

            for (int adj_node : adjlist.get(src)) {

                if (inpath[adj_node]) {
                    cycle_present = true;
                    return;
                } else if (!visited[adj_node]) {
                    DetectCycle(adj_node);
                }
            }
            // Before we backtrack unset the flag for the src vertex as it will
            // not be in the next traversal path
            inpath[src] = false;
        }

        // Mark nodes unvisited for the next traversal
        public void MarkUnvisited() {
            Arrays.fill(visited, false);
        }

        public boolean CyclePresent() {
            return cycle_present;
        }


    }


    public static void main(String[] args) {

        Graph g1_directed = new Graph(7);
        /*
        Graph 1
        6-----------
        ^          |
        |          |
        4<------5<--
        ^       ^
        |       |
        1<------3
        ^       ^
        |       |
        0 ----->2 */

        // Add edges to the directed graph
        g1_directed.AddEdge(0, 1, false);
        g1_directed.AddEdge(0, 2, false);
        g1_directed.AddEdge(1, 4, false);
        g1_directed.AddEdge(2, 3, false);
        g1_directed.AddEdge(3, 1, false);
        g1_directed.AddEdge(3, 5, false);
        g1_directed.AddEdge(4, 6, false);
        g1_directed.AddEdge(5, 4, false);
        g1_directed.AddEdge(6, 5, false);

        g1_directed.DetectCycle(0);

        if (g1_directed.CyclePresent()) {
            System.out.println("Cycle found in the directed graph g1");
        } else {
            System.out.println("Cycle not found in the directed graph g1");
        }

        Graph g2_directed = new Graph(5);

        /*
        Graph 2
        4-
        ^ \
        |  \
        3   \
        ^    \
        |     \
        2      \
        ^       \
        |        \
        0--->1<---
        */
        g2_directed.AddEdge(0, 1, false);
        g2_directed.AddEdge(0, 2, false);
        g2_directed.AddEdge(2, 3, false);
        g2_directed.AddEdge(3, 4, false);
        g2_directed.AddEdge(4, 1, false);

        g2_directed.DetectCycle(0);

        if (g2_directed.CyclePresent()) {
            System.out.println("Cycle found in the directed graph g2");
        } else {
            System.out.println("Cycle not found in the directed graph g2");
        }
    }
}
