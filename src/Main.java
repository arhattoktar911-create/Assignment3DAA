import graph.Graph;
import mst.Kruskal;
import mst.Prim;
import shortestpath.Dijkstra;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Testing Smart City Infrastructure Project ---");

        // Тестовый граф из 4 вершин
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 10.0);
        graph.addEdge(1, 2, 15.0);
        graph.addEdge(0, 2, 5.0);
        graph.addEdge(2, 3, 10.0);

        // Проверка Kruskal
        Kruskal.Result kruskalRes = Kruskal.run(graph.vertices, graph.edges);
        System.out.println("Kruskal MST Weight: " + kruskalRes.totalWeight);

        // Проверка Prim
        Prim.Result primRes = Prim.run(graph.vertices, graph.adj);
        System.out.println("Prim MST Weight: " + primRes.totalWeight);

        // Проверка Dijkstra
        Dijkstra.Result dijkstraRes = Dijkstra.run(graph.adj, 0);
        System.out.println("Shortest distance from 0 to 3: " + dijkstraRes.dist[3]);
    }
}