package connectivity;

import graph.Edge;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {
    public static class Result {
        public List<Integer> order = new ArrayList<>();
        public boolean hasCycle = false;
        public long operations = 0;
    }

    public static Result run(int vertices, List<List<Edge>> adj) {
        Result res = new Result();
        int[] inDegree = new int[vertices];

        for (int u = 0; u < vertices; u++) {
            for (Edge e : adj.get(u)) {
                inDegree[e.dest]++;
                res.operations++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            res.operations++;
            int u = queue.poll();
            res.order.add(u);

            for (Edge e : adj.get(u)) {
                res.operations++;
                inDegree[e.dest]--;
                if (inDegree[e.dest] == 0) {
                    queue.add(e.dest);
                }
            }
        }

        if (res.order.size() != vertices) {
            res.hasCycle = true;
            res.order.clear();
        }

        return res;
    }
}