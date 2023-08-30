import edu.princeton.cs.algs4.CC;
import edu.princeton.cs.algs4.Graph;

import java.util.LinkedList;

public class SPOF {
    public static LinkedList<Integer> Vertex(Graph graph) {
        LinkedList<Integer> Vertices = new LinkedList<Integer>();
        for (int i = 0; i < graph.V(); i++) {
            Graph graph_copy = new Graph(graph.V());
            for (int j = 0; j < graph.V(); j++) {
                if (i != j) {
                    for (int a : graph.adj(j)) {
                        if (i != a) {
                            graph_copy.addEdge(a, j);

                        }
                    }
                }
            }
            if (new CC(graph_copy).count() > 2) {
                Vertices.add(i);
            }
        }
        return Vertices;
    }

    private static class Edge implements Comparable<Edge> {
        int z, d;

        public Edge(int z, int d) {
            this.z = z;
            this.d = d;
        }

        public String toString() {
            return (this.z + " " + this.d);
        }

        public boolean equals(Object a) {
            if (a == this) return true;
            if (a == null) return false;
            if (this.getClass() != a.getClass()) return false;
            Edge that = (Edge) a;
            if (this.z != that.z) return false;
            if (this.d != that.d) return false;
            if ((this.z == that.z && this.d == that.d) || (this.z == that.d && this.d == that.z)) {
                return true;
            }
            else {
                return false;
            }
        }

        public int compareTo(Edge that) {
            if (!this.equals(that)) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }

    public static LinkedList<Edge> CheckEdge(Graph graph) {
        LinkedList<Edge> Edge = new LinkedList<Edge>();
        SET<Edge> edge_set = new SET<Edge>();
        for (int i = 0; i < graph.V(); i++) {
            for (int a : graph.adj(i)) {
                edge_set.add(new Edge(i, a));
            }
        }
        for (Edge a : edge_set) {
            Graph new_graph = new Graph(graph.V());
            for (int i = 0; i < graph.V(); i++) {
                for (int j : graph.adj(i)) {
                    if ((!a.equals(new Edge(i, j))) && (j > i)) {
                        new_graph.addEdge(i, j);

                    }
                }
            }

            if (new CC(new_graph).count() > 1) {
                Edge.add(a);
            }
        }
        return Edge;
    }


    public static Graph read(In in, int z, int d) {
        Graph g = new Graph(z);
        for (int i = 0; i < d; i++) {
            int vertex = in.readInt();
            int edge = in.readInt();
            g.addEdge(vertex, edge);
        }
        return g;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph g = read(in, in.readInt(), in.readInt());
        CC copy = new CC(g);
        if (copy.count() > 1) {
            System.out.println("ERROR: Graph is not connected");
        }
        else {
            LinkedList<Edge> first_edge = CheckEdge(g);
            LinkedList<Integer> first_vertex = Vertex(g);

            System.out.println("SPOF vertices:");
            for (Integer a : first_vertex) {
                StdOut.println(a);
            }
            System.out.println("SPOF edges:");
            for (Edge a : first_edge) {
                StdOut.println(a);
            }
        }
    }
}

