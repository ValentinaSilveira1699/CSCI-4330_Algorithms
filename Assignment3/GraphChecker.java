import java.util.*;

public class GraphChecker {
    private static final int UNCOLORED = -1;
    private List<List<Integer>> graph;
    private int[] colors;
    private Map<Integer, Integer> parent; // To store the parent vertices

    public GraphChecker(int vertices) {
        graph = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        colors = new int[vertices];
        Arrays.fill(colors, UNCOLORED);
        parent = new HashMap<>();
    }

    public void addEdge(int v, int w) {
        graph.get(v).add(w);
        graph.get(w).add(v);
    }

    public boolean isBipartite() {
        for (int i = 0; i < graph.size(); i++) {
            if (colors[i] == UNCOLORED && !isBipartiteDFS(i, 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBipartiteDFS(int vertex, int color) {
        colors[vertex] = color;
        for (int neighbor : graph.get(vertex)) {
            if (colors[neighbor] == UNCOLORED) {
                parent.put(neighbor, vertex); // Store parent vertex
                if (!isBipartiteDFS(neighbor, 1 - color)) {
                    return false;
                }
            } else if (colors[neighbor] == color) {
                System.out.println("Graph is not 2-colorable.");
                System.out.println("Visited vertices and their colors:");
                printVisitedVertices();
                System.out.println("Detected edge with conflicting colors: (" + (char) ('a' + vertex) +
                        ", " + (char) ('a' + neighbor) + ")");
                return false;
            }
        }
        return true;
    }

    private void printVisitedVertices() {
        for (int i = 0; i < colors.length; i++) {
            char vertexLabel = (char) ('a' + i);
            System.out.println(vertexLabel + "(" + (colors[i] == 0 ? "blue" : "red") +
                    ") parent: " + (parent.containsKey(i) ? (char) ('a' + parent.get(i)) : "-"));
        }
    }

    public static void main(String[] args) {
        // Test with Graph G1
        GraphChecker graphCheckerG1 = new GraphChecker(10);
        graphCheckerG1.addEdge(0, 2);
        graphCheckerG1.addEdge(0, 5);
        graphCheckerG1.addEdge(0, 6);
        graphCheckerG1.addEdge(0, 9);
        graphCheckerG1.addEdge(1, 3);
        graphCheckerG1.addEdge(1, 4);
        graphCheckerG1.addEdge(1, 7);
        graphCheckerG1.addEdge(1, 8);
        graphCheckerG1.addEdge(2, 3);
        graphCheckerG1.addEdge(2, 4);
        graphCheckerG1.addEdge(2, 7);
        graphCheckerG1.addEdge(2, 8);
        graphCheckerG1.addEdge(3, 5);
        graphCheckerG1.addEdge(3, 6);
        graphCheckerG1.addEdge(4, 5);
        graphCheckerG1.addEdge(4, 6);
        graphCheckerG1.addEdge(5, 7);
        graphCheckerG1.addEdge(5, 8);
        graphCheckerG1.addEdge(6, 7);
        graphCheckerG1.addEdge(6, 8);

        System.out.println("Testing Graph G1:");
        if (graphCheckerG1.isBipartite()) {
            System.out.println("Graph G1 is bipartite.");
        }

        // Test with Graph G2
        GraphChecker graphCheckerG2 = new GraphChecker(12);
        graphCheckerG2.addEdge(0, 1);
        graphCheckerG2.addEdge(0, 2);
        graphCheckerG2.addEdge(1, 4);
        graphCheckerG2.addEdge(1, 5);
        graphCheckerG2.addEdge(2, 3);
        graphCheckerG2.addEdge(2, 6);
        graphCheckerG2.addEdge(3, 7);
        graphCheckerG2.addEdge(3, 8);
        graphCheckerG2.addEdge(4, 9);
        graphCheckerG2.addEdge(5, 10);
        graphCheckerG2.addEdge(6, 11);
        graphCheckerG2.addEdge(7, 8);
        graphCheckerG2.addEdge(9, 11);
        graphCheckerG2.addEdge(10, 11);

        System.out.println("\nTesting Graph G2:");
        if (graphCheckerG2.isBipartite()) {
            System.out.println("Graph G2 is bipartite.");
        }
    }
}






