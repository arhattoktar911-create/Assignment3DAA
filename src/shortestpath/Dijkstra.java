package shortestpath;

import graph.Edge;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public static class Node implements Comparable<Node> {
        public int vertex;
        public double distance;

        public Node(int vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.distance, o.distance);
        }
    }

    public static class Result {
        public double[] dist;
        public int[] parent;
        public long operations;

        public Result(int vertices) {
            dist = new double[vertices];
            parent = new int[vertices];
            Arrays.fill(dist, Double.POSITIVE_INFINITY);
            Arrays.fill(parent, -1);
        }
    }

    public static Result run(List<List<Edge>> adj, int start) {
        int n = adj.size();
        Result res = new Result(n);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        res.dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            res.operations++;
            Node current = pq.poll();
            int u = current.vertex;

            if (current.distance > res.dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                res.operations++;
                int v = edge.dest;
                double weight = edge.weight;

                if (res.dist[u] + weight < res.dist[v]) {
                    res.dist[v] = res.dist[u] + weight;
                    res.parent[v] = u;
                    pq.add(new Node(v, res.dist[v]));
                }
            }
        }
        return res;
    }
}