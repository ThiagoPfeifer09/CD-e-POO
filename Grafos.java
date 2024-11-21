import java.util.Vector;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

enum Color {
    WHITE, GRAY, BLACK
}

class VertexAttributes {
    Color color;
    int pred;
    int d;
    int f;
}

class In {
    Scanner in;

    In(File in) {
        try {
            this.in = new Scanner(in);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        }
    }

    int readInt() {
        return in.nextInt();
    }

    String readLine() {
        return in.nextLine();
    }
}

public class Graph {
    static final Integer INF = 1000000000;
    static final Integer NIL = -1;
    private final int V; // number of vertices
    private int E; // number of edges
    private Vector<Vector<Integer>> adj; // adjacency lists
    private Vector<VertexAttributes> atribs;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Vector<Vector<Integer>>(V); // Create array of lists.
        for (int v = 0; v < V; v++)
            adj.add(new Vector<Integer>()); // Initialize all lists to empty.
        atribs = new Vector<VertexAttributes>(V);
        for (int v = 0; v < V; v++)
            atribs.add(new VertexAttributes());
    }

    public Graph(In in) {
        // Lê as informações iniciais
        String line = in.readLine(); // Ignora linha 'c'
        line = in.readLine(); // Ignora linha 'No. of vertices'
        this.V = Integer.parseInt(line.split(":")[1].trim()); // Lê o número de vértices
        line = in.readLine(); // Ignora linha 'No. of directed edges'
        this.E = Integer.parseInt(line.split(":")[1].trim()); // Lê o número de arestas
        line = in.readLine(); // Ignora linha 'Max. weight'
        line = in.readLine(); // Ignora linha 'Min. weight'
        
        adj = new Vector<Vector<Integer>>(V); // Cria lista de adjacências
        for (int v = 0; v < V; v++) {
            adj.add(new Vector<Integer>());
        }
        
        atribs = new Vector<VertexAttributes>(V); // Inicializa os atributos dos vértices
        for (int v = 0; v < V; v++) {
            atribs.add(new VertexAttributes());
        }

        // Lê as arestas
        while (in.in.hasNextLine()) {
            String edge = in.readLine().trim();
            if (edge.startsWith("a")) {
                String[] parts = edge.split("\\s+");
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int w = Integer.parseInt(parts[3]);
                addEdge(u - 1, v - 1); // A aresta usa índices de 1 a N, então subtrai 1 para ajustar ao índice 0 a N-1
            }
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w); // Add w to v’s list.
        adj.get(w).add(v); // Add v to w’s list.
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }

    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }

    public String StatusAtribs() {
        String s = "Vertex Attributes Status (color, pred, d, f) \n";
        int i = 0;
        for (VertexAttributes a : atribs) {
            s += (i) + ": " + a.color + ", " + a.pred + ", " + a.d + ", " + a.f;
            s += "\n";
            i++;
        }
        return s;
    }

    public void BFS(int s) {
        Queue<Integer> Q;
        for (VertexAttributes a : atribs) {
            a.color = Color.WHITE;
            a.d = INF;
            a.pred = NIL;
        }
        atribs.get(s).color = Color.GRAY;
        atribs.get(s).d = 0;
        atribs.get(s).pred = NIL;
        Q = new LinkedList<Integer>();
        Q.add(s);
        while (Q.peek() != null) { // if Q is empty, peek() returns null
            int u = Q.poll();
            for (Integer v : adj(u)) {
                if (atribs.get(v).color == Color.WHITE) {
                    atribs.get(v).color = Color.GRAY;
                    atribs.get(v).d = atribs.get(u).d + 1;
                    atribs.get(v).pred = u;
                    Q.add(v);
                }
            }
            atribs.get(u).color = Color.BLACK;
        }
    }

    public void DFS() {
        // Implementação do DFS
    }

    public static void main(String[] args) {
        /*Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(4, 1);
        g.addEdge(4, 3);
        g.addEdge(3, 1);
        g.addEdge(2, 3);
        g.addEdge(2, 1);
        System.out.println(g.toString());
        g.BFS(0);
        System.out.println(g.StatusAtribs());
*/
        Graph p = new Graph(new In(new File("tinyG.txt")));
        System.out.println(p.toString());
        p.BFS(0);
        System.out.println(p.StatusAtribs());
    }
}
