package mst;

import dsu.DisjointSet;
import graph.Edge;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {
    public static class Result {
        public double totalWeight = 0;
        public List<Edge> mstEdges = new ArrayList<>();
        public long operations = 0;
        public boolean isConnected = false;
    }

    public static Result run(int vertices, List<Edge> edges) {
        Result res = new Result();
        List<Edge> sortedEdges = new ArrayList<>(edges);
        sortedEdges.sort(Comparator.comparingDouble(e -> e.weight));

        DisjointSet dsu = new DisjointSet(vertices);
        for (Edge edge : sortedEdges) {
            res.operations++;
            if (dsu.union(edge.src, edge.dest)) {
                res.mstEdges.add(edge);
                res.totalWeight += edge.weight;
            }
        }
        res.operations += dsu.operationsCount;
        res.isConnected = (res.mstEdges.size() == vertices - 1);
        return res;
    }
}