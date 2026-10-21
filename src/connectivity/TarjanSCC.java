package connectivity;

import graph.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TarjanSCC {
    public static class Result {
        public List<List<Integer>> sccList = new ArrayList<>();
        public long operations = 0;
    }

    private static int index = 0;

    public static Result run(int vertices, List<List<Edge>> adj) {
        Result res = new Result();
        index = 0;
        int[] ids = new int[vertices];
        int[] low = new int[vertices];
        boolean[] onStack = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        Arrays.fill(ids, -1);

        for (int i = 0; i < vertices; i++) {
            if (ids[i] == -1) {
                dfs(i, adj, ids, low, onStack, stack, res);
            }
        }

        return res;
    }

    private static void dfs(int u, List<List<Edge>> adj, int[] ids, int[] low, boolean[] onStack, Stack<Integer> stack, Result res) {
        res.operations++;
        ids[u] = low[u] = index++;
        stack.push(u);
        onStack[u] = true;

        for (Edge e : adj.get(u)) {
            res.operations++;
            int v = e.dest;
            if (ids[v] == -1) {
                dfs(v, adj, ids, low, onStack, stack, res);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], ids[v]);
            }
        }

        if (ids[u] == low[u]) {
            List<Integer> component = new ArrayList<>();
            while (true) {
                int node = stack.pop();
                onStack[node] = false;
                component.add(node);
                if (node == u) break;
            }
            res.sccList.add(component);
        }
    }
}