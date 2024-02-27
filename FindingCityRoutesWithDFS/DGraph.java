import java.util.*;

public class DGraph {
    public int V;
    public final String[] vertexNames;
    private final List<Set<Integer>> adj;
    public DGraph(int V) {
        this.V = V;
        this.vertexNames = new String[V];
        this.adj = new ArrayList<>();

        for (int v = 0; v < V; v++) {
            adj.add(new HashSet<>());
        }
    }

    public void addEdge(String v, String w) {
        int vIndex = getIndex(v);
        int wIndex = getIndex(w);
        adj.get(vIndex).add(wIndex);
    }

    public Iterable<Integer> adj(String vertex) {
        int vertexIndex = getVertexIndex(vertex);
        if (vertexIndex != -1) {
            return adj.get(vertexIndex);
        }
        return Collections.emptyList();
    }

    private int getIndex(String vertexName) {
        int existingIndex = getVertexIndex(vertexName);
        if (existingIndex != -1) {
            return existingIndex;
        }
    
        for (int i = 0; i < V; i++) {
            if (vertexNames[i] == null) {
                vertexNames[i] = vertexName;
                return i;
            }
        }
    
        return -1; // 
    }
    
    public int getVertexIndex(String vertexName) {
        for (int i = 0; i < V; i++) {
            if (vertexNames[i] != null && Objects.equals(vertexNames[i], vertexName)) {
                return i;
            }
        }
        return -1;
    }
    
    public String getCityName(int vertexIndex) {
        if (vertexIndex >= 0 && vertexIndex < V) {
            return vertexNames[vertexIndex];
        }else 
        return "Invalid vertex index";
    }
    
}
