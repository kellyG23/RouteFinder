# RouteFinder

## Overview

RouteFinder is a Java-based pathfinding application that implements Dijkstra's algorithm to find the shortest route between two locations on a weighted graph. The program provides an interactive command-line interface for users to explore various routes and calculate optimal paths based on distance.

## Features

- **Shortest Path Calculation**: Uses Dijkstra's algorithm to compute the optimal route between any two nodes
- **Interactive Menu System**: User-friendly command-line interface with multiple options
- **Weighted Graph Implementation**: Supports custom graph structures with weighted edges
- **Distance Tracking**: Maintains and displays cumulative distances along routes
- **Multiple Route Queries**: Allows users to find multiple routes in a single session

## Project Structure

```
RouteFinder/
├── RouteFinder.java    # Main application file containing all core functionality
└── README.md           # Project documentation
```

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Command-line terminal or IDE with Java support

### Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/kellyG23/RouteFinder.git
   cd RouteFinder
   ```

2. Compile the Java file:
   ```bash
   javac RouteFinder.java
   ```

3. Run the application:
   ```bash
   java RouteFinder
   ```

## How to Use

### Running the Program

Once you start the application, you'll be presented with a menu:

Option 1: Find Shortest Route
- Enter the starting location
- Enter the destination location
- The program will calculate and display:
  - The optimal path from start to destination
  - Total distance of the route
  - All intermediate stops along the way
Option 2: Traverse
 - Enter “N”as the starting location and destination.
 - Circular graphs will show a circular route.
Option 3: Exit
 - Closes the application

### Example Usage

```
Enter starting location: A
Enter destination: D

Shortest Route Found:
A -> B -> D
Total Distance: 13

Process finished with exit code 0
```

## Algorithm Details

### Dijkstra's Algorithm Implementation

The program implements Dijkstra's algorithm, a popular graph traversal algorithm for finding the shortest path between nodes:

1. **Initialization**: Sets the distance to the starting node as 0 and all other nodes as infinity
2. **Priority Queue**: Uses a priority queue to efficiently select the next closest unvisited node
3. **Relaxation**: Updates distances to neighboring nodes if a shorter path is found
4. **Path Reconstruction**: Traces back through the path to construct the final route

### Time Complexity

- **Time**: O((V + E) log V) where V is the number of vertices and E is the number of edges
- **Space**: O(V) for storing distances and visited nodes

## Code Structure

### Main Components

#### 1. Graph Representation
- Uses adjacency list or matrix to represent the network of locations
- Stores weighted edges representing distances between locations

#### 2. Node Class
- Represents individual locations in the graph
- Stores node identifier and associated edge weights

#### 3. Pathfinding Engine
- Implements core Dijkstra's algorithm
- Handles distance calculations and path reconstruction

#### 4. User Interface
- Menu-driven command-line interface
- Input validation and error handling
- Formatted output display

## Customization

### Adding New Locations

To add new locations to the graph, modify the graph initialization section in the `RouteFinder.java` file:

```java
// Add edges with weights (distances)
graph.addEdge("LocationA", "LocationB", distance);
```

### Modifying Edge Weights

Update the weight parameter when adding edges to reflect new distances:

```java
graph.addEdge("CityA", "CityB", newDistance);
```

## Technical Details

### Data Structures Used

- **HashMap**: For storing graph adjacency relationships
- **PriorityQueue**: For efficient node selection during pathfinding
- **ArrayList**: For maintaining paths and node lists

### Key Methods

- `findShortestPath()`: Main pathfinding method implementing Dijkstra's algorithm
- `displayLocations()`: Shows all available nodes in the graph
- `reconstructPath()`: Builds the final path from start to destination
- `initializeGraph()`: Sets up the graph structure with nodes and edges

## Error Handling

The program includes robust error handling for:
- Invalid location names
- Disconnected graph components (no path exists)
- Invalid menu selections
- Empty or null inputs

## Future Enhancements

Potential improvements for future versions:

- [ ] GUI implementation with visual graph representation
- [ ] Support for A* algorithm as an alternative pathfinding method
- [ ] Import/export graph data from external files (JSON, CSV)
- [ ] Multi-criteria routing (time, cost, distance)
- [ ] Real-time route updates and dynamic graph modifications
- [ ] Support for bidirectional edges with different weights
- [ ] Visualization of the pathfinding process

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is available for educational and personal use.

## Author

**kellyG23**

## Acknowledgments

- Dijkstra's algorithm implementation based on classical computer science principles
- Inspired by real-world navigation and routing problems

---

**Note**: This is an educational project demonstrating graph algorithms and pathfinding techniques. For production routing applications, consider using established libraries and frameworks.
