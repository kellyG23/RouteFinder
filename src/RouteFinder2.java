import java.util.*;

public interface RouteFinder2 {
    Scanner sc = new Scanner(System.in);
    Map map = new Map();

    default void findRoute() {
        System.out.print("Enter Initial Location: ");
        String location = sc.nextLine();
        System.out.print("Enter Destination: ");
        String destination = sc.nextLine();

        if (location.equals(destination)) {
            System.out.println("You cannot travel from same location.");
        } else {
            search(location, destination);
        }
    }

    default void search(String location, String destination) {
        for (int i = 0; i < map.cities.length; i++) {
            for (int j = 0; j < map.cities.length; j++) {
                if (map.cities[i].equals(location) && map.cities[j].equals(destination)) {
                    if (map.distance[i][j] != 0) {
                        System.out.println("Direct Route Found: " + location + " -> " + destination);
                        System.out.println("Distance: " + map.distance[i][j]);
                    } else {
                        // No direct route → do DFS
                        boolean[] visited = new boolean[map.cities.length];
                        List<String> path = new ArrayList<>();
                        if (!depthFirstSearch(i, j, visited, path)) {
                            System.out.println("No route found from " + location + " to " + destination);
                        }
                    }
                    return;
                }
            }
        }
    }

    // DFS to find a path from root to destination
    default boolean depthFirstSearch(int current, int des, boolean[] visited, List<String> path) {
        visited[current] = true;
        path.add(map.cities[current]);

        if (current == des) {
            System.out.println("Route Found: " + String.join(" -> ", path));
            // Calculate distance
            int total = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                int from = Arrays.asList(map.cities).indexOf(path.get(i));
                int to = Arrays.asList(map.cities).indexOf(path.get(i + 1));
                total += map.distance[from][to];
            }
            System.out.println("Total Distance: " + total);
            return true;
        }

        // Explore neighbors
        for (int i = 0; i < map.cities.length; i++) {
            if (map.distance[current][i] != 0 && !visited[i]) {
                if (depthFirstSearch(i, des, visited, path)) {
                    return true;
                }
            }
        }

        // Backtrack
        path.remove(path.size() - 1);
        return false;
    }
}

