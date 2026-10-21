import connectivity.TarjanSCC;
import connectivity.TopologicalSort;
import graph.Graph;
import graph.GraphGenerator;
import mst.Kruskal;
import mst.Prim;
import shortestpath.Dijkstra;

public class Benchmark {
    public static void main(String[] args) {
        int[][] configs = {
                {10, 30},     // Small
                {100, 500},   // Medium
                {1000, 5000}  // Large
        };

        String[] names = {"Small", "Medium", "Large"};

        System.out.println("================ EXPERIMENTAL ANALYSIS RESULTS ================\n");

        for (int i = 0; i < configs.length; i++) {
            int V = configs[i][0];
            int E = configs[i][1];
            System.out.println("--- Configuration: " + names[i] + " (V=" + V + ", E=" + E + ") ---");

            // Замеры для Undirected / MST & Dijkstra
            long totalKruskalTime = 0, totalPrimTime = 0, totalDijkstraTime = 0;
            long kruskalOps = 0, primOps = 0, dijkstraOps = 0;

            for (int run = 0; run < 5; run++) {
                Graph undirGraph = GraphGenerator.generateConnectedUndirectedGraph(V, E, 42 + run);

                // Kruskal
                long start = System.nanoTime();
                Kruskal.Result kRes = Kruskal.run(V, undirGraph.edges);
                totalKruskalTime += (System.nanoTime() - start);
                kruskalOps += kRes.operations;

                // Prim
                start = System.nanoTime();
                Prim.Result pRes = Prim.run(V, undirGraph.adj);
                totalPrimTime += (System.nanoTime() - start);
                primOps += pRes.operations;

                // Dijkstra
                start = System.nanoTime();
                Dijkstra.Result dRes = Dijkstra.run(undirGraph.adj, 0);
                totalDijkstraTime += (System.nanoTime() - start);
                dijkstraOps += dRes.operations;
            }

            // Замеры для DAG / Topological Sort & SCC
            long totalTopoTime = 0, totalSccTime = 0;
            long topoOps = 0, sccOps = 0;

            for (int run = 0; run < 5; run++) {
                Graph dag = GraphGenerator.generateDAG(V, E, 42 + run);

                long start = System.nanoTime();
                TopologicalSort.Result tRes = TopologicalSort.run(V, dag.adj);
                totalTopoTime += (System.nanoTime() - start);
                topoOps += tRes.operations;

                start = System.nanoTime();
                TarjanSCC.Result sRes = TarjanSCC.run(V, dag.adj);
                totalSccTime += (System.nanoTime() - start);
                sccOps += sRes.operations;
            }

            System.out.printf("Kruskal    | Avg Time: %8.2f us | Avg Ops: %d%n", (totalKruskalTime / 5.0) / 1000.0, kruskalOps / 5);
            System.out.printf("Prim       | Avg Time: %8.2f us | Avg Ops: %d%n", (totalPrimTime / 5.0) / 1000.0, primOps / 5);
            System.out.printf("Dijkstra   | Avg Time: %8.2f us | Avg Ops: %d%n", (totalDijkstraTime / 5.0) / 1000.0, dijkstraOps / 5);
            System.out.printf("Topo Sort  | Avg Time: %8.2f us | Avg Ops: %d%n", (totalTopoTime / 5.0) / 1000.0, topoOps / 5);
            System.out.printf("Tarjan SCC | Avg Time: %8.2f us | Avg Ops: %d%n", (totalSccTime / 5.0) / 1000.0, sccOps / 5);
            System.out.println();
        }
    }
}