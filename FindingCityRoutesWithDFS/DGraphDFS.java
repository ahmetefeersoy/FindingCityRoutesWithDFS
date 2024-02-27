import java.util.*;

public class DGraphDFS {
    private final DGraph graph;
    private final Set<Integer> marked;
    private int limit;
    private String startCity;
    public ArrayList<ArrayList<String>> routeList;

    public DGraphDFS(DGraph graph, String startCity, int limit) {
        this.graph = graph;
        this.marked = new HashSet<>();
        this.limit = limit;
        this.startCity = startCity;
        int start = graph.getVertexIndex(startCity);
        routeList = new ArrayList<>();
        dfs(start, 0, new ArrayList<>(Collections.singletonList(startCity)));
    }

    public ArrayList<ArrayList<String>> getPaths() {
        return routeList;
    }

    private void dfs(int vertexIndex, int hops, List<String> route) {
        if (hops == limit) {
            routeList.add(new ArrayList<>(route));
            return;
        }

        String a = graph.getCityName(vertexIndex);
            
        for (int neighbor : graph.adj(a)) {
            if (!marked.contains(neighbor)) {
                route.add(graph.getCityName(neighbor));
                dfs(neighbor, hops + 1, route);
                route.remove(route.size() - 1);
            }
        }

        marked.remove(vertexIndex);
    }
}
