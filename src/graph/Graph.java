package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public int vertices;
    public List<Edge> edges;
    public List<List<Edge>> adj;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
        this.adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            this.adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, double weight) {
        Edge edge = new Edge(src, dest, weight);
        edges.add(edge);
        adj.get(src).add(edge);
    }
}