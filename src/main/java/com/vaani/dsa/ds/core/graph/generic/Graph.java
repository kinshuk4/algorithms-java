package com.vaani.dsa.ds.core.graph.generic;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;

//https://www.geeksforgeeks.org/graph-and-its-representations/
//https://gist.github.com/smddzcy/bf8fc17dedf4d40b0a873fc44f855a58
//http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
@ToString
@EqualsAndHashCode
public class Graph<T> {
    private Map<T, Vertex<T>> adjVertices; // collection of all vertices
    private final boolean isDirected;

    public Graph(boolean isDirected) {
        adjVertices = new HashMap<>();
        this.isDirected = isDirected;
    }

    public List<Vertex<T>> getVertices() {
        return new ArrayList<>(adjVertices.values());
    }

    void addVertex(T label) {
        Vertex<T> v = new Vertex<>(label);
        addVertex(v);
    }

    void addVertex(Vertex<T> v) {
        if (!adjVertices.containsKey(v.getValue())) {
            adjVertices.put(v.getValue(), v);
        }

    }

    void removeVertex(T label) {
        Vertex<T> v = new Vertex<>(label);
        // Remove all vertices which has above vertex
        adjVertices.values()
                .forEach(v1 -> v1.getEdges().stream().filter(e -> e.contains(v)).forEach(v1::removeEdge));
        adjVertices.remove(v);
    }

    Vertex<T> vertexFrom(T v1Label) {
        return adjVertices.get(v1Label);
    }

    public void addEdge(T v1Label, T v2Label, int weight) {
        addVertex(v1Label);
        addVertex(v2Label);

        Vertex<T> v1 = vertexFrom(v1Label);
        Vertex<T> v2 = vertexFrom(v2Label);

        Edge<T> e1 = new Edge<>(v1, v2, weight);

        if (!v1.contains(e1)) {
            v1.addEdge(e1);

            if (!this.isDirected) {
                Edge<T> e2 = new Edge<>(v2, v1, weight);
                if (!v2.contains(e2)) {
                    v2.addEdge(e2);
                }
            }
        }
    }

    public void addEdge(T v1Label, T v2Label) {
        addEdge(v1Label, v2Label, 1);
    }

    void removeEdge(T v1Label, T v2Label) {
        Edge<T> e = new Edge<>(adjVertices.get(v1Label), adjVertices.get(v2Label));
        adjVertices.values().forEach(v -> v.removeEdge(e));
    }

    public int size() {
        return adjVertices.size();
    }

    //https://www.baeldung.com/java-graphs
    public Set<Vertex<T>> breadthFirstTraversal(T rootLabel) {
        Vertex<T> root = vertexFrom(rootLabel);
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.poll();
            for (Edge<T> e : vertex.getEdges()) {
                Vertex<T> v = e.getTo();
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }

        return visited;
    }

    //https://www.baeldung.com/java-graphs
    public Set<Vertex<T>> depthFirstTraversal(T root) {
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        Stack<Vertex<T>> stack = new Stack<>();
        Vertex<T> rootVertex = vertexFrom(root);
        stack.push(rootVertex);
        while (!stack.isEmpty()) {
            Vertex<T> vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Edge<T> e : vertex.getEdges()) {
                    Vertex<T> v = e.getTo();
                    stack.push(v);
                }
            }
        }
        return visited;
    }

    public Set<Vertex<T>> dfsRecursion(T startVertex) {
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        dfs(vertexFrom(startVertex), visited);
        return visited;
    }

    private void dfs(Vertex<T> start, Set<Vertex<T>> visited) {
        visited.add(start);

        System.out.print(start + " ");
        for (Edge<T> e : start.getEdges()) {
            Vertex<T> destination = e.getTo();
            if (!visited.contains(destination)) {
                dfs(destination, visited);
            }
        }
    }

    public List<Vertex<T>> topologicalSorting() {
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        Stack<Vertex<T>> stack = new Stack<>();
        //visit from each node if not already visited
        for (Vertex<T> v : adjVertices.values()) {
            if (!visited.contains(v)) {
                topologicalSortUtil(v, visited, stack);
            }
        }

        List<Vertex<T>> result = new ArrayList<>(stack);
        Collections.reverse(result);
        return result;
    }

    private void topologicalSortUtil(Vertex<T> start, Set<Vertex<T>> visited, Stack<Vertex<T>> stack) {
        visited.add(start);

        for (Edge<T> e : start.getEdges()) {
            Vertex<T> destination = e.getTo();
            if (!visited.contains(destination)) {
                topologicalSortUtil(destination, visited, stack);
            }
        }

        stack.push(start);
    }

    public boolean hasCycle() {
        if (isDirected) {
            throw new NotImplementedException("Method not available.");
        } else {
            return hasCycleUndirectedDfs();
        }
    }

    private boolean hasCycleUndirectedDfs() {
        boolean result = false;

        //visited array
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        //do DFS, from each vertex
        for (Vertex<T> v : adjVertices.values()) {
            if (!visited.contains(v)) {
                if (hasCycleUndirectedDfsUtil(v, visited, null)) {
                    return true;
                }
            }
        }
        return result;
    }

    private boolean hasCycleUndirectedDfsUtil(Vertex<T> currVertex, Set<Vertex<T>> visited, Vertex<T> parent) {

        //add this vertex to visited vertex
        visited.add(currVertex);

        //visit neighbors except its direct parent
        for (Edge<T> e : currVertex.getEdges()) {
            Vertex<T> vertex = e.getTo();
            //check the adjacent vertex from current vertex
            if (!vertex.equals(parent)) {
                //if destination vertex is not its direct parent then
                if (visited.contains(vertex)) {
                    //if here means this destination vertex is already visited
                    //means cycle has been detected
                    return true;
                } else {
                    //recursion from destination node
                    if (hasCycleUndirectedDfsUtil(vertex, visited, currVertex)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean isBipartiteDfs() {

        //check if graph is empty
        if (size() == 0) {
            return true;
        }


        //initialize colors for all vertices
        Map<Vertex<T>, Color> colors = new HashMap<>();
        //color all the vertices with white color
        for (Vertex<T> v : adjVertices.values()) {
            colors.put(v, Color.WHITE);
        }

        //start coloring vertices , this code will handle the disconnected graph as well
        //color the first vertex with RED
        int i = 0;
        for (Vertex<T> v : adjVertices.values()) {
            // If the vertex is not yet visited
            if (colors.get(v) == Color.WHITE) {
                //if is it first vertex, mark it RED i.e. visited
                colors.put(v, Color.RED);

                boolean result = isBipartiteDfsUtil(v, colors);
                if (!result)
                    return false;
            }
        }
        return true;
    }

    private boolean isBipartiteDfsUtil(Vertex<T> u, Map<Vertex<T>, Color> colors) {
        //travel all adjacent vertices
        for (Edge<T> e : u.getEdges()) {
            Vertex<T> v = e.getTo();
            // v is not yet visited
            if (colors.get(v) == Color.WHITE) {
                //color is with the alternate color of vertex v compared to parent
                if (colors.get(u) == Color.RED) {
                    //if u is in red, make vertex v in green
                    colors.put(v, Color.GREEN);
                } else if (colors.get(u) == Color.GREEN) {
                    //if u is in green, make vertex v in red
                    colors.put(v, Color.RED);
                }

                //make recursive call
                boolean result = isBipartiteDfsUtil(v, colors);
                if (!result) {
                    return false;
                }
            } else if (colors.get(u) == colors.get(v)) {
                return false;
            }

        }
        // if here means graph is successfully colored in 2 color, red and green
        //graph is bipartite
        return true;
    }

    public boolean isBipartiteBfs() {
        // Create a color array to store colors assigned to all veritces.
        // Vertex number is used as index in this array.
        // The value '-1' of colorArr[i] is used to indicate that no color is assigned to vertex 'i'.
        // The value 1 is used to indicate first color is assigned and value 0 indicates second color is assigned.
        //initialize colors for all vertices
        Map<Vertex<T>, Color> colors = new HashMap<>();

        //color all the vertices with white color
        adjVertices.values().forEach(v -> colors.put(v, Color.WHITE));

        List<Vertex<T>> vertices = getVertices();

        for (Vertex<T> v: getVertices()) {
            // if vertex is yet not visited
            if (colors.get(v) == Color.WHITE) {
                colors.put(v, Color.RED);
                boolean result = isBipartiteBfsUtil(v, colors);
                if (!result) {
                    return false;
                }
            }



        }
        // Assign first color to source




        // If we reach here, then all adjacent vertices can
        // be colored with alternate color
        return true;
    }

    private boolean isBipartiteBfsUtil(Vertex<T> p, Map<Vertex<T>, Color> colors) {
        // Create a queue (FIFO) of vertex numbers
        // and enqueue source vertex for BFS traversal
        Queue<Vertex<T>> q = new LinkedList<>();
        q.add(p);

        // Run while there are vertices in queue (Similar to BFS)
        while (q.size() != 0) {
            // Dequeue a vertex from queue
            Vertex<T> u = q.poll();

//            // Return false if there is a self-loop
//            if (G[u][u] == 1)
//                return false;

            // Find all non-colored adjacent vertices
            for (Edge<T> e : u.getEdges()){
                Vertex<T> v = e.getTo();

                // v is not visited yet.
                if(colors.get(v) == Color.WHITE){
                    //color is with the alternate color of vertex v compared to parent
                    if (colors.get(u) == Color.RED) {
                        //if u is in red, make vertex v in green
                        //if u is in red, make vertex v in green
                        colors.put(v, Color.GREEN);
                    } else if (colors.get(u) == Color.GREEN) {
                        //if u is in green, make vertex v in red
                        colors.put(v, Color.RED);
                    }
                    q.add(v);
                }else if (colors.get(u) == colors.get(v)) {
                    return false;
                }
            }

        }
        return true;
    }

}
