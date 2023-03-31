package Graph;

public class Main {
	public static void main(String[] args) {
		Graph graph = new Graph();
		
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);
	
		graph.addEdge(1, 1, 2);
		graph.addEdge(2, 1, 3);
		graph.addEdge(3, 1, 4);
		
		graph.removeEdge(1, 4);
		
		graph.removeVertex(1);
		
		graph.neighbors(1);
		
		System.out.println(graph.adjacent(2, 3));
		System.out.println(graph.adjacent(1, 4));

		graph.printVertexList();
		graph.printEdgeList();
	}
}
