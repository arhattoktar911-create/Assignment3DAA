# Assignment 3: Smart City Infrastructure and Route Planning

## Overview
This Java application models a smart city's infrastructure network using graph-based representations. 
Districts are represented as vertices ($V$), and infrastructure connections or roads are represented as weighted edges ($E$). 
The system supports:
1. Minimum Spanning Tree (MST) computation for optimal road network planning using Kruskal's and Prim's algorithms.
2. Direct/Acyclic analysis using Topological Sorting and Strongly Connected Components (Tarjan's algorithm).
3. Shortest path computation using Dijkstra's algorithm.

---

## Implementation Details

The project is structured into modular packages:
- `graph`: Core classes `Edge`, `Graph`, and `GraphGenerator`.
- `dsu`: Custom `DisjointSet` implementation utilizing **Path Compression** and **Union by Rank**.
- `mst`: `Kruskal` and `Prim` algorithms for MST calculation.
- `connectivity`: `TopologicalSort` (Kahn's algorithm) and `TarjanSCC` for directed graph evaluation.
- `shortestpath`: `Dijkstra` algorithm using a standard Priority Queue (Min-Heap).

---

## Complexity Analysis

| Algorithm | Time Complexity | Space Complexity | Notes |
| :--- | :--- | :--- | :--- |
| **DSU (Path Comp + Rank)** | $\mathcal{O}(\alpha(V))$ per op | $\mathcal{O}(V)$ | $\alpha$ is the Inverse Ackermann function ($\approx \mathcal{O}(1)$). |
| **Kruskal's MST** | $\mathcal{O}(E \log E)$ | $\mathcal{O}(V + E)$ | Sorting edges dominates the runtime. |
| **Prim's MST** | $\mathcal{O}((V + E) \log V)$ | $\mathcal{O}(V + E)$ | Efficient for dense graphs using Binary Heap. |
| **Topological Sort** | $\mathcal{O}(V + E)$ | $\mathcal{O}(V)$ | Uses Kahn's in-degree queue method. |
| **Tarjan's SCC** | $\mathcal{O}(V + E)$ | $\mathcal{O}(V)$ | Single DFS traversal with low-link values. |
| **Dijkstra's** | $\mathcal{O}((V + E) \log V)$ | $\mathcal{O}(V)$ | Requires non-negative edge weights. |

---

## Experimental Setup & Results

Tests were performed using fixed seed generation (`seed = 42 + run`) across 5 repeated runs per configuration.

### Benchmark Results Table

| Configuration | Algorithm | Vertices ($V$) | Edges ($E$) | Avg Time ($\mu s$) | Avg Operations |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Small** | Kruskal | 10 | 30 | 9663.48 | 333 |
| **Small** | Prim | 10 | 30 | 9420.70 | 71 |
| **Small** | Dijkstra | 10 | 30 | 7688.98 | 76 |
| **Small** | Topo Sort | 10 | 30 | 6169.72 | 70 |
| **Small** | Tarjan SCC | 10 | 30 | 6657.52 | 40 |
| **Medium** | Kruskal | 100 | 500 | 1419.96 | 5888 |
| **Medium** | Prim | 100 | 500 | 569.46 | 1283 |
| **Medium** | Dijkstra | 100 | 500 | 385.66 | 1196 |
| **Medium** | Topo Sort | 100 | 500 | 292.04 | 1100 |
| **Medium** | Tarjan SCC | 100 | 500 | 267.46 | 600 |
| **Large** | Kruskal | 1000 | 5000 | 7656.26 | 59140 |
| **Large** | Prim | 1000 | 5000 | 6949.02 | 13304 |
| **Large** | Dijkstra | 1000 | 5000 | 4548.96 | 11944 |
| **Large** | Topo Sort | 1000 | 5000 | 2118.26 | 11000 |
| **Large** | Tarjan SCC | 1000 | 5000 | 634.10 | 6000 |

### Execution Time Performance Plots

#### 1. Execution Time vs. Number of Vertices
![Execution Time vs Vertices](results/plots/1graf.png)
![Execution Time vs Vertices Backup](results/plots/1graf)
![Execution Time vs Vertices Nested](results/plots/placeholder.txt/1graf.png)

#### 2. Execution Time vs. Number of Edges
![Execution Time vs Edges](results/plots/graf.png)
![Execution Time vs Edges Backup](results/plots/graf)
![Execution Time vs Edges Nested](results/plots/placeholder.txt/graf.png)

---

## Comparison and Discussion

1. **Kruskal vs. Prim**:
   - **Kruskal's** operation count grows significantly faster on larger graphs due to edge sorting ($\mathcal{O}(E \log E)$) and additional DSU checks.
   - **Prim's** implementation performs fewer total operations when utilizing an adjacency list and priority queue on sparse-to-medium graphs.

2. **DSU Impact**:
   - Combining Path Compression and Union by Rank keeps the amortized operations for DSU nearly constant ($\mathcal{O}(\alpha(V))$).

3. **Dijkstra & Negative Weights**:
   - Dijkstra's algorithm relies on a greedy strategy that assumes once a node is visited with the shortest known distance, its path cannot be improved. Negative edge weights break this invariant, requiring algorithms like Bellman-Ford.

---

## Testing & Correctness Verification

The implementation was tested against edge cases:
- **Single Vertex Graph ($V=1$)**: Verified correct handling with zero operations.
- **Disconnected Graphs**: Checked that Kruskal/Prim report disconnected status (`isConnected = false`).
- **Cyclic Directed Graphs**: Topological sorting correctly identifies cycles and returns `hasCycle = true`.
- **MST Equivalency**: Verified that Kruskal and Prim return identical total MST weight for connected graphs.

---

## Conclusion

- For **Minimum Spanning Trees**, Prim's algorithm is preferable for dense graphs or graph representations using adjacency lists, whereas Kruskal's is very effective when edges are already sorted.
- For **Linear Infrastructure Flow/Dependencies**, Topological Sort effectively detects cyclic dependencies.
- For **Single-Source Shortest Paths**, Dijkstra's algorithm offers optimal performance with non-negative road weights.
