package Graph;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Graph graph1 = new Graph();
		Graph graph2 = new Graph();

		graph1.addVertex(1);
		graph1.addVertex(2);
		graph1.addVertex(3);
		graph1.addVertex(4);

		graph1.addEdge(1, 1, 2);
		graph1.addEdge(2, 1, 3);
		graph1.addEdge(3, 2, 3);
		graph1.addEdge(4, 3, 4);
		graph1.addEdge(5, 4, 2);

		graph2.addVertex(3);
		graph2.addVertex(4);
		graph2.addVertex(5);
		graph2.addVertex(6);

		graph2.addEdge(1, 3, 4);
		graph2.addEdge(2, 4, 5);
		graph2.addEdge(3, 5, 6);

		ArrayList<Vertex> path = graph1.caminho(1, 4);
		if (path != null) {
			System.out.print("Path from 1 to 4: ");
			for (Vertex vertex : path) {
				System.out.print(vertex.getValue() + " ");
			}
			System.out.println();
		} else {
			System.out.println("There is no path from 1 to 4.");
		}

		path = graph1.caminho(2, 5);
		if (path != null) {
			System.out.print("Path from 2 to 5: ");
			for (Vertex vertex : path) {
				System.out.print(vertex.getValue() + " ");
			}
			System.out.println();
		} else {
			System.out.println("There is no path from 2 to 5.");
		}

		Graph union = graph1.uniao(graph2);
		System.out.println("Union of G1 and G2:");
		union.printVertexList();
		union.printEdgeList();

		Graph intersection = graph1.interseccao(graph2);
		System.out.println("Intersection of G1 and G2:");
		intersection.printVertexList();
		intersection.printEdgeList();
	}
}
