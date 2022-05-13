package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidTree {

    Map<Integer, List<Integer>> graph = new HashMap<>();

    // valid tree when 1) no cycles 2) fully connnected

    public boolean validTree(int n, int[][] edges) {
        // write your code here
        boolean[] paths = new boolean[n];
        boolean[] visited = new boolean[n];

        buildGraph(edges);
        // no cycles
        if (isCycle(paths, visited, 0)) {
            return false;
        }
        // fully connected
        for (int node = 0; node < n; node++) {
            if (!visited[node]) {
                return false;
            }
        }
        return true;
    }

    private boolean isCycle(boolean[] paths, boolean[] visited, int node) {
        visited[node] = true;

        paths[node] = true;

        for (int neighbor : graph.get(node)) {
            if (paths[neighbor]) {
                return true;
            }
            if (!visited[neighbor] && isCycle(paths, visited, neighbor)) {
                return true;
            }
        }

        paths[node] = false;
        return false;
    }

    private void buildGraph(int[][] edges) {
        for (int[] edge : edges) {
            int v = edge[0];
            int w = edge[1];

            if (!graph.containsKey(v)) {
                graph.put(v, new ArrayList<>());
            }
            graph.get(v).add(w);

            if (!graph.containsKey(w)) {
                graph.put(w, new ArrayList<>());
            }
            graph.get(w).add(v);
        }
    }
}
