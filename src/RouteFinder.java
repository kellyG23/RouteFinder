import java.util.*;

public interface RouteFinder {
    Scanner sc = new Scanner(System.in);
    Map map = new Map(); // Uses your Map class with cities & distance

    default void findRoute() {
        System.out.print("Enter Initial Location: ");
        String location = sc.nextLine().toUpperCase();
        System.out.print("Enter Destination: ");
        String destination = sc.nextLine().toUpperCase();

        if (location.equals(destination)) {
            System.out.println("You cannot travel from the same location.");
        } else {
            search(location, destination);
        }
    }

    default void search(String location, String destination) {
        int source = -1, dest = -1;

        // Find the index of source and destination
        for (int i = 0; i < map.cities.length; i++) {
            if (map.cities[i].equals(location)) source = i;
            if (map.cities[i].equals(destination)) dest = i;
        }

        if (source == -1 || dest == -1) {
            System.out.println("Invalid city name entered.");
            return;
        }

        // If direct connection exists
        if (map.distance[source][dest] != 0) {
            System.out.println("Route Found: " + location + " -> " + destination);
            System.out.println("Direct Distance: " + map.distance[source][dest]);
        } else {
            // Otherwise, find shortest path via Dijkstra
            dijkstra(source, dest);
        }
    }

    default void dijkstra(int source, int destination) {
        int n = map.cities.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[source] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(dist, visited);
            if (u == -1) break; // no reachable vertex
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && map.distance[u][v] != 0 &&
                        dist[u] + map.distance[u][v] < dist[v]) {
                    dist[v] = dist[u] + map.distance[u][v];
                    parent[v] = u;
                }
            }
        }

        if (dist[destination] == Integer.MAX_VALUE) {
            System.out.println("No available route between these cities.");
            return;
        }

        // Display shortest path
        System.out.println("\nShortest Route Found:");
        printPath(parent, destination);
        System.out.println("\nTotal Distance: " + dist[destination]);
    }

    default int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    default void printPath(int[] parent, int j) {
        if (parent[j] == -1) {
            System.out.print(map.cities[j]);
            return;
        }
        printPath(parent, parent[j]);
        System.out.print(" -> " + map.cities[j]);
    }
}
