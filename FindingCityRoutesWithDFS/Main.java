import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DGraph graph = new DGraph(0);
        Scanner scn = new Scanner(System.in);
        String filename;
        filename = scn.nextLine();
        filename=filename.trim();
        // Dosya yolu oluşturulurken dizin ayırıcı ve boşluk ekleniyor
        String filepath = "/Users/ahmetefeersoy/Desktop/VSC/CMPE224HW2Q1/src/" + filename;
        FileReader file = new FileReader(filepath);
        graph=fileReader(file, graph);
        int limit;
        limit=scn.nextInt();
        String startCity;
        startCity=scn.next();
        DGraphDFS dfs = new DGraphDFS(graph,startCity,limit);
        ArrayList<ArrayList<String>> paths = dfs.getPaths();
        System.out.println("Number of total routes: "+paths.size());
        System.out.println("Routes are:");
        
        Comparator<ArrayList<String>> pathComparator = (path1, path2) -> {
            int minLength = Math.min(path1.size(), path2.size());
            for (int i = 0; i < minLength; i++) {
                int comparison = path1.get(i).compareTo(path2.get(i));
                if (comparison != 0) {
                    return comparison;
                }
            }
            return Integer.compare(path1.size(), path2.size());
        };
        
        paths.sort(pathComparator);
        


    

        for (List<String> path : paths) {
            if (path.size() == limit + 1) {
                System.out.println(String.join("-", path));
            }
        }
    }

    private static DGraph fileReader(FileReader fileReader, DGraph graph) {
        Set<String> listOfCities = new HashSet<>();
        List<String> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] graphEdges = line.split(",");
                listOfCities.add(graphEdges[0]);
                listOfCities.add(graphEdges[1]);
                list.add(graphEdges[0]);
                list.add(graphEdges[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DGraph newGraph = new DGraph(listOfCities.size());
        for (int i = 0; i < list.size(); i++) {
            newGraph.addEdge(list.get(i), list.get(++i));
        }
        return newGraph;
    }
}
