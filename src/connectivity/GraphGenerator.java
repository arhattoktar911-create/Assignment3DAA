package graph;

import java.util.Random;

public class GraphGenerator {

    public static Graph generateConnectedUndirectedGraph(int vertices, int edgesCount, long seed) {
        Random random = new Random(seed);
        Graph graph = new Graph(vertices);

        // 1. Создаем остовное дерево, чтобы гарантировать связность графа
        for (int i = 1; i < vertices; i++) {
            int parent = random.nextInt(i);
            double weight = 1 + random.nextDouble() * 99; // Веса от 1 до 100
            graph.addEdge(parent, i, weight);
            graph.addEdge(i, parent, weight);
        }

        // 2. Добавляем оставшиеся случайные рёбра
        int currentEdges = vertices - 1;
        while (currentEdges < edgesCount) {
            int u = random.nextInt(vertices);
            int v = random.nextInt(vertices);
            if (u != v) {
                double weight = 1 + random.nextDouble() * 99;
                graph.addEdge(u, v, weight);
                graph.addEdge(v, u, weight);
                currentEdges++;
            }
        }

        return graph;
    }

    public static Graph generateDAG(int vertices, int edgesCount, long seed) {
        Random random = new Random(seed);
        Graph graph = new Graph(vertices);

        int added = 0;
        while (added < edgesCount) {
            int u = random.nextInt(vertices);
            int v = random.nextInt(vertices);
            // В DAG ребро всегда идет от меньшего индекса к большему
            if (u < v) {
                double weight = 1 + random.nextDouble() * 99;
                graph.addEdge(u, v, weight);
                added++;
            }
        }
        return graph;
    }
}