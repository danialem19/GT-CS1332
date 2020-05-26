import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Your implementations of various graph algorithms.
 *
 * @author DANIEL TADESSE
 * @version 1.0
 */
public class GraphAlgorithms {
    /**
     * Perform breadth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You may import/use {@code java.util.Queue}, {@code java.util.Set},
     * {@code java.util.Map}, {@code java.util.List}, and any classes
     * that implement the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph you are traversing
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
            Graph<T> graph) {
        if ((start == null) || (graph == null)) {
            throw new IllegalArgumentException("Due to Vertex or Graph"
                    + " is null");
        }
        if (!(graph.getAdjacencyList().containsKey(start))) {
            throw new IllegalArgumentException(
                    "Due to start value does not exist in the graph");
        }

        java.util.Queue<Vertex<T>> next = new java.util.LinkedList<>();
        next.add(start);
        List<Vertex<T>> result = new java.util.ArrayList<>();
        result.add(start);

        Map<Vertex<T>, List<VertexDistancePair<T>>>
                adjacencyList = graph.getAdjacencyList();
        while (!(next.isEmpty())) {
            Vertex<T> curVertex = next.remove();
            for (VertexDistancePair<T> vertex : adjacencyList.get(curVertex)) {
                if (!(result.contains(vertex.getVertex()))) {
                    result.add(vertex.getVertex());
                    next.add(vertex.getVertex());

                }
            }
        }
        return result;
    }

    /**
     * Perform depth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You MUST implement this method recursively.
     * Do not use any data structure as a stack to avoid recursion.
     * Implementing it any other way WILL cause you to lose points!
     *
     * You may import/use {@code java.util.Set}, {@code java.util.Map},
     * {@code java.util.List}, and any classes that implement the
     * aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph you are traversing
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
            Graph<T> graph) {
        if ((start == null) || (graph == null)) {
            throw new IllegalArgumentException("Due to Vertex or Graph"
                    + " is null");
        }
        if (!(graph.getAdjacencyList().containsKey(start))) {
            throw new IllegalArgumentException(
                    "Due to start value does not exist in the graph");
        }

        Set<Vertex<T>> visitedSet = new java.util.HashSet<>();
        visitedSet.add(start);
        List<Vertex<T>> result = new java.util.ArrayList<>();
        result.add(start);
        Map<Vertex<T>, List<VertexDistancePair<T>>>
                adjacencyList = graph.getAdjacencyList();
        depthFirstSearchHelper(visitedSet, adjacencyList, start,  result);
        return result;
    }

    /**
     * A private recursive helper method that helps depthFirstSearch method
     * @param adjacencyList the Adjacency List of the Vertex currently pointed to
     * @param visitedSet a set that keeps track of visited vertices
     * @param curVertex the current vertex working wth
     * @param <T> the data type of the vertices
     * @param result list of vertices in the order they are visited
     */
    private static <T> void depthFirstSearchHelper(
            Set<Vertex<T>> visitedSet, Map<Vertex<T>,
            List<VertexDistancePair<T>>> adjacencyList, Vertex<T> curVertex,
            List<Vertex<T>> result) {
        for (VertexDistancePair<T> vertex : adjacencyList.get(curVertex)) {
            Vertex<T> temp = vertex.getVertex();
            if (!(visitedSet.contains(temp))) {
                result.add(temp);
                visitedSet.add(temp);
                depthFirstSearchHelper(visitedSet, adjacencyList, temp, result);
            }
        }
    }

    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph where the edges only have positive
     * weights.
     *
     * Return a map of the shortest distances such that the key of each entry is
     * a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists. You may assume that going from a vertex to itself
     * has a distance of 0.
     *
     * There are guaranteed to be no negative edge weights in the graph.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph you are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a map of the shortest distances from start to every other node
     *         in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
            Graph<T> graph) {
        if ((start == null) || (graph == null)) {
            throw new IllegalArgumentException("Due to Vertex or Graph"
                    + " is null");
        }
        if (!(graph.getAdjacencyList().containsKey(start))) {
            throw new IllegalArgumentException(
                    "Due to start value does not exist in the graph");
        }
        java.util.PriorityQueue<VertexDistancePair<T>> next = new
                java.util.PriorityQueue<>();
        next.add(new VertexDistancePair<>(start, 0));

        Map<Vertex<T>, Integer> result = new java.util.HashMap<>();
        result.put(start, 0);
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyMap
                = graph.getAdjacencyList();
        while (!(next.isEmpty())) {
            VertexDistancePair<T> curVertex = next.remove();
            List<VertexDistancePair<T>> neighbor
                    = adjacencyMap.get(curVertex.getVertex());
            for (VertexDistancePair<T> x : neighbor) {
                Vertex<T> tempV = x.getVertex();
                int tempD = curVertex.getDistance() + x.getDistance();
                if (result.containsKey(tempV)) {
                    if (result.get(tempV) > tempD) {
                        VertexDistancePair<T> add = new
                                VertexDistancePair<T>(tempV, tempD);
                        result.replace(tempV, tempD);
                        next.add(add);
                    }
                } else {
                    result.put(tempV, tempD);
                    next.add(new VertexDistancePair<T>(
                            tempV, tempD));
                }
            }
        }
        for (Vertex<T> element : adjacencyMap.keySet()) {
            if (!result.containsKey(element)) {
                result.put(element, Integer.MAX_VALUE);
            }
        }
        return result;
    }

    /**
     * Run Prim's algorithm on the given graph and return the minimum spanning
     * tree in the form of a set of Edges.  If the graph is disconnected, and
     * therefore there is no valid MST, return null.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You may assume that for a given starting vertex, there will only be
     * one valid MST that can be formed. In addition, only an undirected graph
     * will be passed in.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph you are creating the MST for
     * @param <T> the data type representing the vertices in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if ((start == null) || (graph == null)) {
            throw new IllegalArgumentException("Due to Vertex or Graph"
                    + " is null");
        }
        if (!(graph.getAdjacencyList().containsKey(start))) {
            throw new IllegalArgumentException(
                    "Due to start value does not exist in the graph");
        }

        Set<Edge<T>> result = new java.util.HashSet<>();
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjacencyMap = graph.getAdjacencyList();

        java.util.PriorityQueue<VertexDistancePair<T>> workOn = new java.util.PriorityQueue<>();
        workOn.addAll(adjacencyMap.get(start));

        java.util.PriorityQueue<VertexDistancePair<T>> next = new java.util.PriorityQueue<>();
        next.addAll(adjacencyMap.get(start));

        Set<Vertex<T>> visitedSet = new java.util.HashSet<>();

        while (!(next.isEmpty())) {
            VertexDistancePair<T> curVertex = next.peek();
            int minDistance = curVertex.getDistance();
            Vertex<T> minVertexTo = curVertex.getVertex();

            for (VertexDistancePair<T> x : next) {
                if(x.getDistance() < minDistance && !visitedSet.contains(x.getVertex())) {
                    curVertex = x;
                    minDistance = x.getDistance();
                    minVertexTo = x.getVertex();
                }
            }
            result.add(new Edge<>(start, minVertexTo, minDistance, graph.isDirected()));

            visitedSet.add(curVertex.getVertex());
            next.remove(curVertex);
        }
        return result;
    }

}
