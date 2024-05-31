package Item;

import java.util.Scanner;

/**
 *
 * @author NURINA
 */

//THIS IS PART AKU APPLY GRAPH 
//NI MAP KITA
//KAU TRY RUN THIS ONE FIRST TO KNOW THE MAP

public class TheMap<T extends Comparable<T>, N extends Comparable<N>> {

    Vertex<T, N> head;
    int size;

    public TheMap() {
        head = null;
        size = 0;
    }

    // Vertex class using 2 generic types T and N
    // Implementing comparable interface where objects of T and N can be compared using natural ordering
    class Vertex<T extends Comparable<T>, N extends Comparable<N>> {

        T vertexInfo; // stores information about the vertex
        int indeg;    // number of edges coming into vertex
        int outdeg;   // number of edges going out of vertex
        Vertex<T, N> nextVertex; // reference to the next vertex in the list
        Edge<T, N> firstEdge;    // reference to the first edge connected to this vertex

        public Vertex(T vInfo, Vertex<T, N> next) {
            vertexInfo = vInfo;
            indeg = 0;
            outdeg = 0;
            nextVertex = next;
            firstEdge = null;
        }
    }

    class Edge<T extends Comparable<T>, N extends Comparable<N>> {
    Vertex<T, N> toVertex; // variable that stores the destination vertex
    N weight;              // weight of the edge (if applicable)
    Edge<T, N> nextEdge;   // reference to the next edge

    public Edge(Vertex<T, N> destination, N w, Edge<T, N> a) {
        toVertex = destination;
        weight = w;
        nextEdge = a;
    }
}


    public int getSize() {
        return this.size;
    }

    public boolean hasVertex(T v) {
        if (head == null)
            return false;
        Vertex<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0) // determine whether it is the vertex we are looking for
                return true;
            temp = temp.nextVertex;
        }
        return false;
    }
    
    public int getIndeg(T v){
        if(hasVertex(v)==true){
            Vertex<T,N>temp=head;
            while(temp!=null){
                if(temp.vertexInfo.compareTo(v)==0)
                    return temp.indeg;
                temp=temp.nextVertex;
            }
    }
        return -1;
    }
       

    public void addVertex(T v) {
        if (!hasVertex(v)) {
            Vertex<T, N> newVertex = new Vertex<>(v, head);
            head = newVertex;
            size++;
        }
    }
    
    
    public int getIndex(T v){
        Vertex<T,N> temp=head;
        int pos=0;
        while(temp!=null){
            if(temp.vertexInfo.compareTo(v)==0)
                return pos;
            temp=temp.nextVertex;
            pos+=1;
        }
        return-1;
    }
    
    public T getVertex(int pos){
        if(pos>size-1 || pos<0)
            return null;
       Vertex<T,N>temp = head;
       for(int i=0; i<pos; i++)
           temp=temp.nextVertex;
           return temp.vertexInfo;
    }
    
    public boolean hasEdge(T source,T destination){
        if(head==null)
            return false;
        if(!hasVertex(source)||!hasVertex(destination))
            return false;
        Vertex<T,N>sourceVertex =head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source)==0){
                Edge<T,N>currentEdge=sourceVertex.firstEdge;
                while(currentEdge!=null){
                    if(currentEdge.toVertex.vertexInfo.compareTo(destination)==0)
                        return true;
                    currentEdge=currentEdge.nextEdge;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return false;
    }
    
    public boolean addEdge(T source, T destination, N weight, int resourceCost) {
        if (head == null)
            return false;
        if (!hasVertex(source) || !hasVertex(destination))
            return false;

        Vertex<T, N> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                Vertex<T, N> destinationVertex = head;
                while (destinationVertex != null) {
                    if (destinationVertex.vertexInfo.compareTo(destination) == 0) {
                        Edge<T, N> currentEdge = sourceVertex.firstEdge;
                        Edge<T, N> newEdge = new Edge<>(destinationVertex, weight, currentEdge);
                        sourceVertex.firstEdge = newEdge;
                        sourceVertex.outdeg++;
                        destinationVertex.indeg++;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }
    
    public boolean AddEdge(T source, T destination, N weight, int resourceCost) {
        if (!hasVertex(source) || !hasVertex(destination))
            return false;

        Vertex<T, N> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                Vertex<T, N> destinationVertex = head;
                while (destinationVertex != null) {
                    if (destinationVertex.vertexInfo.compareTo(destination) == 0) {
                        Edge<T, N> newEdge = new Edge<>(destinationVertex, weight, sourceVertex.firstEdge);
                        sourceVertex.firstEdge = newEdge;
                        sourceVertex.outdeg++;
                        destinationVertex.indeg++;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    
    public N getEdgeWeight(T source,T destination){
        N notFound=null;
        if(head==null)
            return null;
        if(!hasVertex(source)||!hasVertex(destination))
            return notFound;
        Vertex<T,N>sourceVertex=head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source)==0){
                Edge<T,N>currentEdge=sourceVertex.firstEdge;
                while(currentEdge!=null){
                    if(currentEdge.toVertex.vertexInfo.compareTo(destination)==0)
                        return currentEdge.weight;
                        currentEdge=currentEdge.nextEdge;
            }
                
        }
            sourceVertex=sourceVertex.nextVertex;
    }
        return notFound;
    }

    public void printGraph() {
        Vertex<T, N> temp = head;
        while (temp != null) {
            System.out.print(temp.vertexInfo + " -> ");
            Edge<T, N> currentEdge = temp.firstEdge;
            while (currentEdge != null) {
                System.out.print("["+temp.vertexInfo+","+ currentEdge.toVertex.vertexInfo+"]");
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            temp = temp.nextVertex;
        }
    }

    
    
    public void checkPathAndDisplayInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your location: ");
        T source = (T) scanner.nextLine();

        System.out.println("Enter the destination teleportation point: ");
        T destination = (T) scanner.nextLine();

        // Check if a path exists between source and destination
        if (hasEdge(source, destination)) {
            // If a path exists, display information about weight and resource cost
            N weight = getEdgeWeight(source, destination);
            System.out.println("Path exists from " + source + " to " + destination + ".");
            System.out.println("Distance: " + weight +"m ");
        } else {
            // If no path exists, display a warning message
            System.out.println("No path exists between " + source + " and " + destination + ".");
        }

        scanner.close();
    }
    
    
    
    public static void main(String[] args) {
        TheMap<String, Integer> graph = new TheMap<>();

        // Adding teleportation points (nodes)
        graph.addVertex("Tree House");
        graph.addVertex("Ice Kingdom");
        graph.addVertex("Candy Kingdom");
        graph.addVertex("Fire Kingdom");

        // Adding paths (edges) between teleportation points
        graph.AddEdge("Tree House", "Ice Kingdom", 200,60);
        graph.AddEdge("Tree House", "Candy Kingdom", 700,120);
        graph.AddEdge("Tree House", "Fire Kingdom", 500,80);
        graph.AddEdge("Ice Kingdom", "Tree House", 200,60);
        graph.AddEdge("Candy Kingdom", "Tree House", 700,120);
        graph.AddEdge("Fire Kingdom", "Tree House", 500,80);
        graph.AddEdge("Ice Kingdom", "Candy Kingdom", 400,70); 
        graph.AddEdge("Candy Kingdom", "Fire Kingdom", 800,140);

        // Print the graph to see the teleportation points and paths
        graph.printGraph();
        
         graph.checkPathAndDisplayInfo();
        
    }
}
