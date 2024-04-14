import java.util.*;

// Class representing a vertex in the graph
class Vertex {
    String name; // Name of the vertex
    int d; // Shortest distance from source
    Vertex pi; // Predecessor in shortest path

    // Constructor to initialize a vertex with a name
    public Vertex(String name) {
        this.name = name;
        this.d = Integer.MAX_VALUE; // Initially set to infinity
        this.pi = null; // Predecessor set to null
    }
}

// Class representing an edge in the graph
class Edge {
    Vertex source; // Source vertex of the edge
    Vertex destination; // Destination vertex of the edge
    int weight; // Weight of the edge

    // Constructor to initialize an edge with source, destination, and weight
    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

// Main class representing the Bellman-Ford algorithm
public class BellmanFord {
    private List<Vertex> vertices; // List to store vertices of the graph
    private List<Edge> edges; // List to store edges of the graph

    // Constructor to initialize lists of vertices and edges
    public BellmanFord() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    // Method to add a vertex to the graph
    public void addVertex(String name) {
        vertices.add(new Vertex(name)); // Add a new vertex with the given name
    }

    // Method to add an edge to the graph
    public void addEdge(String sourceName, String destinationName, int weight) {
        Vertex source = getVertex(sourceName); // Get the source vertex object
        Vertex destination = getVertex(destinationName); // Get the destination vertex object
        edges.add(new Edge(source, destination, weight)); // Add a new edge with source, destination, and weight
    }

    // Method to get a vertex object by name
    private Vertex getVertex(String name) {
        for (Vertex v : vertices) {
            if (v.name.equals(name))
                return v; // Return the vertex object if found
        }
        return null; // Return null if vertex not found
    }

    // Method to initialize single source vertex
    private void initializeSingleSource(Vertex source) {
        for (Vertex v : vertices) {
            v.d = Integer.MAX_VALUE; // Set distance to infinity
            v.pi = null; // Set predecessor to null
        }
        source.d = 0; // Set distance of source vertex to 0
    }

    // Method to relax an edge
    private boolean relax(Edge edge) {
        if (edge.source.d != Integer.MAX_VALUE && edge.destination.d > edge.source.d + edge.weight) {
            edge.destination.d = edge.source.d + edge.weight; // Update distance
            edge.destination.pi = edge.source; // Update predecessor
            return true; // Return true if relaxation occurs
        }
        return false; // Return false otherwise
    }

    // Method to perform Bellman-Ford algorithm
    public boolean bellmanFord(String sourceName) {
        Vertex source = getVertex(sourceName); // Get source vertex object
        if (source == null) {
            System.out.println("Source vertex not found."); // Print error message if source not found
            return false; // Return false
        }

        initializeSingleSource(source); // Initialize single source

        // Relax all edges |V| - 1 times
        for (int i = 1; i < vertices.size(); i++) {
            for (Edge edge : edges) {
                relax(edge); // Relax each edge
            }
        }

        // Check for negative weight cycles
        boolean hasNegativeCycle = false;
        for (Edge edge : edges) {
            if (relax(edge)) {
                System.out.println("Graph contains a negative weight cycle."); // Print message if negative cycle found
                hasNegativeCycle = true; // Set flag to true
                break;
            }
        }
        return hasNegativeCycle; // Return true if negative cycle found, false otherwise
    }

    // Method to print shortest paths
    public void printShortestPaths() {
        System.out.println("Number of vertices: " + vertices.size());
        System.out.println("Number of edges: " + edges.size());

        System.out.println(String.format("%-8s%-10s%-10s", "Vertex", "d-value", "Pi-value"));
        for (Vertex v : vertices) {
            String d = (v.d == Integer.MAX_VALUE) ? "INF" : String.valueOf(v.d);
            String pi = (v.pi == null) ? "null" : v.pi.name;
            System.out.println(String.format("%-8s%-10s%-10s", v.name, d, pi));
        }
    }
    

    public static void main(String[] args) {
        // Create Graph G3
        BellmanFord graph1 = new BellmanFord();
        graph1.addVertex("S");
        graph1.addVertex("A");
        graph1.addVertex("B");
        graph1.addVertex("C");
        graph1.addVertex("D");
        graph1.addVertex("E");
        graph1.addVertex("F");
        graph1.addVertex("G");

        graph1.addEdge("S", "A", 6);
        graph1.addEdge("S", "B", 5);
        graph1.addEdge("S", "C", -4);
        graph1.addEdge("A", "D", 1);
        graph1.addEdge("A", "E", 5);
        graph1.addEdge("B", "A", 2);
        graph1.addEdge("B", "C", -1);
        graph1.addEdge("B", "F", 3);
        graph1.addEdge("C", "F", 2);
        graph1.addEdge("D", "E", -2);
        graph1.addEdge("E", "G", -3);
        graph1.addEdge("F", "E", 1);
        graph1.addEdge("G", "D", 4);

        graph1.bellmanFord("S");
        System.out.println("Graph G3:");
        graph1.printShortestPaths();

       
        // Create Graph G4
        BellmanFord graph2 = new BellmanFord();
        graph2.addVertex("S");
        graph2.addVertex("A");
        graph2.addVertex("B");
        graph2.addVertex("C");
        graph2.addVertex("D");
        graph2.addVertex("E");
        graph2.addVertex("F");
        graph2.addVertex("G");
        graph2.addVertex("H");
        graph2.addVertex("I");

        graph2.addEdge("S", "A", 6);
        graph2.addEdge("S", "B", -1);
        graph2.addEdge("S", "C", 5);
        graph2.addEdge("A", "B", 1);
        graph2.addEdge("A", "D", -4);
        graph2.addEdge("B", "C", 2);
        graph2.addEdge("B", "E", 3);
        graph2.addEdge("C", "E", 7);
        graph2.addEdge("C", "F", -2);
        graph2.addEdge("D", "E", 2);
        graph2.addEdge("E", "A", 6);
        graph2.addEdge("E", "F", 5);
        graph2.addEdge("E", "H", 5);
        graph2.addEdge("F", "I", 3);
        graph2.addEdge("G", "D", 4);
        graph2.addEdge("G", "H", -3);
        graph2.addEdge("H", "I", 1);

        graph2.bellmanFord("S");
        System.out.println("\nGraph G4:");
        graph2.printShortestPaths();

    }
}