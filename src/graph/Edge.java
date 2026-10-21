package graph;

public class Edge {
    public int src;
    public int dest;
    public double weight;

    public Edge(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}