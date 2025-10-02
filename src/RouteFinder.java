import java.util.Scanner;

public interface RouteFinder {
    Scanner sc = new Scanner(System.in);
    Map map = new Map();

    default void findRoute() {
        // Asking users where to start and where to arrive.
        System.out.print("Enter Initial Location: ");
        String location = sc.nextLine();
        System.out.print("Enter Destination: ");
        String destination = sc.nextLine();

        if (location.equals(destination)) {
            System.out.println("You cannot travel from same location.");
        }
        else {
            search(location, destination);
        }
    }

    default void search(String location, String destination) {
        for (int i = 0; i < map.cities.length; i++) {
            for (int j = 0; j < map.cities.length; j++) {
                if (map.cities[i].equals(location) && map.cities[j].equals(destination)) {
                    if (map.distance[i][j] == 0) {
                        depthFirstSearch(i, j);
                    }
                    else {
                        System.out.println("Route Found: " + location + " -> " + destination);
                        System.out.println("Distance: " + map.distance[i][j]);
                    }
                    return;
                }
            }
        }
    }

    default void depthFirstSearch(int root, int des) {
        int next = root;
        for (int i = 0; i < map.cities.length; i++) {
            for (int j = 0; j < map.cities.length; j++) {
                if (map.distance[next][i] != 0) {
                    if (map.distance[next][i] == map.distance[next][des]) {
                        System.out.println("Route Found: " + map.cities[next] + " -> " + map.cities[des]);
                    }
                }
                else if (map.distance[next][i] == 0) {
//                    depthFirstSearch(i, j);
                    System.out.println("a");
                }
            }
        }
    }
}

