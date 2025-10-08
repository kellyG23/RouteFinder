import java.util.*;

public interface RouteFinder {
    Scanner sc = new Scanner(System.in);
    Map map = new Map();

    default void findRoute() {
        System.out.print("Enter Starting Location: ");
        String start = sc.nextLine().trim().toUpperCase();
        System.out.print("Enter Destination: ");
        String end = sc.nextLine().trim().toUpperCase();

        if (start.equals(end)) {
            System.out.println("\nStarting and destination are the same. Traversing the entire map...\n");
            traverse(start);
        } else {
            search(start, end);
        }
    }

    default void search(String start, String end) {
        int source = getCityIndex(start);
        int destination = getCityIndex(end);

        if (source == -1 || destination == -1) {
            System.out.println("Invalid city name entered.");
            return;
        }

        if (map.distance[source][destination] != 0) {
            System.out.println("Route Found: " + start + " -> " + end);
            System.out.println("Direct Distance: " + map.distance[source][destination]);
        } else {
            dijkstra(source, destination);
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
            if (u == -1) break;
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

        System.out.println("\nShortest Route Found:");
        printPath(parent, destination);
        System.out.println("\nTotal Distance: " + dist[destination]);
    }

    default void traverse(String startCity) {
        int startIndex = getCityIndex(startCity);
        if (startIndex == -1) {
            System.out.println("Invalid city name.");
            return;
        }

        boolean[] visited = new boolean[map.cities.length];
        int totalDistance = 0;
        int current = startIndex;

        System.out.print("Traverse Path: ");
        System.out.print(map.cities[current]);
        visited[current] = true;

        for (int i = 1; i < map.cities.length; i++) {
            int next = findNearestUnvisited(current, visited);
            if (next == -1) break;
            System.out.print(" -> " + map.cities[next]);
            totalDistance += map.distance[current][next];
            visited[next] = true;
            current = next;
        }

        if (map.distance[current][startIndex] != 0) {
            totalDistance += map.distance[current][startIndex];
            System.out.print(" -> " + map.cities[startIndex]);
        }

        System.out.println("\nTotal Distance Travelled: " + totalDistance);
    }

    default int findNearestUnvisited(int current, boolean[] visited) {
        int minDist = Integer.MAX_VALUE;
        int nearest = -1;
        for (int i = 0; i < map.cities.length; i++) {
            if (!visited[i] && map.distance[current][i] != 0 && map.distance[current][i] < minDist) {
                minDist = map.distance[current][i];
                nearest = i;
            }
        }
        return nearest;
    }

    default int getCityIndex(String city) {
        for (int i = 0; i < map.cities.length; i++) {
            if (map.cities[i].equals(city)) return i;
        }
        return -1;
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
