package mst;

import graph.Edge;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {
    public static class EdgeNode implements Comparable<EdgeNode> {
        public int vertex;
        public double weight;
        public Edge edge;

        public EdgeNode(int vertex, double weight, Edge edge) {
            this.vertex = vertex;
            this.weight = weight;
            this.edge = edge;
        }

        @Override
        public int compareTo(EdgeNode o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    public static class Result {
        public double totalWeight = 0;
        public List<Edge> mstEdges = new ArrayList<>();
        public long operations = 0;
        public boolean isConnected = false;
    }

    public static Result run(int vertices, List<List<Edge>> adj) {
        Result res = new Result();
        boolean[] inMST = new boolean[vertices];
        PriorityQueue<EdgeNode> pq = new PriorityQueue<>();

        inMST[0] = true;
        for (Edge e : adj.get(0)) {
            pq.add(new EdgeNode(e.dest, e.weight, e));
        }

        while (!pq.isEmpty() && res.mstEdges.size() < vertices - 1) {
            res.operations++;
            EdgeNode node = pq.poll();
            int u = node.vertex;

            if (inMST[u]) continue;

            inMST[u] = true;
            res.mstEdges.add(node.edge);
            res.totalWeight += node.weight;

            for (Edge e : adj.get(u)) {
                res.operations++;
                if (!inMST[e.dest]) {
                    pq.add(new EdgeNode(e.dest, e.weight, e));
                }
            }
        }

        res.isConnected = (res.mstEdges.size() == vertices - 1);
        return res;
    }
}