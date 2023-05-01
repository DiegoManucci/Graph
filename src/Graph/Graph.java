package Graph;

import java.util.*;
import java.util.stream.IntStream;

public class Graph {
	public ArrayList<Vertex> vertexList = new ArrayList<>(); 
	public ArrayList<Edge> edgeList = new ArrayList<>(); 
	
	public Boolean adjacent(Integer vertex1, Integer vertex2) {
		return findEdge(vertex1, vertex2) != null;
	}
	public void neighbors(Integer vertex) {
		for(int i = 0; i < edgeList.size(); i++) {
			if(edgeList.get(i).getVertex1().getValue() == vertex) {
				System.out.println(edgeList.get(i).getVertex2().getValue());
			} 
			else if(edgeList.get(i).getVertex2().getValue() == vertex) {
				System.out.println(edgeList.get(i).getVertex1().getValue());
			}
		}
	}
	
	public void addVertex(Integer value) {
		vertexList.add(new Vertex(value));
	}
	public void removeVertex(Integer value) {
		vertexList.remove(findVertex(value));	
	}
	
	public void addEdge(Integer value, Integer vertex1, Integer vertex2) {
		Vertex v1 = findVertex(vertex1);
		Vertex v2 = findVertex(vertex2);
		
		edgeList.add(new Edge(value, v1, v2));
	}
	public void removeEdge(Integer vertex1, Integer vertex2) {
			edgeList.remove(findEdge(vertex1, vertex2));	
	}	
	
	public void printVertexList() {
		vertexList.forEach(vertex -> System.out.println(vertex.getValue()));
	}
	public void printEdgeList() {
		edgeList.forEach(edge -> System.out.println(edge.getValue() + " " + edge.getVertex1().getValue() + " " + edge.getVertex2().getValue()));
	}
	
	private Vertex findVertex(Integer value) {
		OptionalInt result = IntStream.range(0, vertexList.size())
				.filter(x -> vertexList.get(x).getValue() == value)
				.findFirst();
		
		if(result.isPresent()) {
			return vertexList.get(result.getAsInt());	
		}
		
		return null;
	}
	private Edge findEdge(Integer vertex1, Integer vertex2) {
		OptionalInt result = IntStream.range(0, edgeList.size())
				.filter(x -> edgeList.get(x).getVertex1().getValue() == vertex1 && edgeList.get(x).getVertex2().getValue() == vertex2)
				.findFirst();
		
		if(result.isPresent()) {
			return edgeList.get(result.getAsInt());	
		}
		
		return null;
	}

	public Integer getVertexValue(Integer vertex) {
		return findVertex(vertex).getValue();
	}
	public Integer getEdgeValue(Integer vertex1, Integer vertex2) {
		return findEdge(vertex1, vertex2).getValue();
	}

	public void setVertexValue(Integer vertex, Integer value) {
		 findVertex(vertex).setValue(value);
	}
	public void setEdgeValue(Integer vertex1, Integer vertex2, Integer value) {
		 findEdge(vertex1, vertex2).setValue(value);
	}

	public ArrayList<Vertex> caminho(Integer start, Integer end) {
		ArrayList<Vertex> path = new ArrayList<>();
		HashSet<Vertex> visited = new HashSet<>();
		Vertex startVertex = findVertex(start);
		Vertex endVertex = findVertex(end);

		if (startVertex == null || endVertex == null) {
			return path;
		}

		LinkedList<Vertex> queue = new LinkedList<>();
		HashMap<Vertex, Vertex> parentMap = new HashMap<>();
		queue.add(startVertex);
		visited.add(startVertex);

		while (!queue.isEmpty()) {
			Vertex current = queue.poll();
			if (current == endVertex) {
				Vertex node = endVertex;
				while (node != null) {
					path.add(node);
					node = parentMap.get(node);
				}
				Collections.reverse(path);
				break;
			}
			for (Edge edge : edgeList) {
				if (edge.getVertex1() == current && !visited.contains(edge.getVertex2())) {
					visited.add(edge.getVertex2());
					queue.add(edge.getVertex2());
					parentMap.put(edge.getVertex2(), current);
				}
				else if (edge.getVertex2() == current && !visited.contains(edge.getVertex1())) {
					visited.add(edge.getVertex1());
					queue.add(edge.getVertex1());
					parentMap.put(edge.getVertex1(), current);
				}
			}
		}

		return path;
	}

	public Graph uniao(Graph other) {
		Graph union = new Graph();
		HashSet<Vertex> seenVertices = new HashSet<>();

		for (Vertex vertex : this.vertexList) {
			union.addVertex(vertex.getValue());
			seenVertices.add(vertex);
		}
		for (Vertex vertex : other.vertexList) {
			if (!seenVertices.contains(vertex)) {
				union.addVertex(vertex.getValue());
				seenVertices.add(vertex);
			}
		}

		for (Edge edge : this.edgeList) {
			Vertex v1 = edge.getVertex1();
			Vertex v2 = edge.getVertex2();
			union.addEdge(edge.getValue(), v1.getValue(), v2.getValue());
		}
		for (Edge edge : other.edgeList) {
			Vertex v1 = edge.getVertex1();
			Vertex v2 = edge.getVertex2();
			union.addEdge(edge.getValue(), v1.getValue(), v2.getValue());
		}

		return union;
	}

	public Graph interseccao(Graph other) {
		Graph intersection = new Graph();
		HashSet<Vertex> seenVertices = new HashSet<>();

		for (Vertex vertex : this.vertexList) {
			if (other.findVertex(vertex.getValue()) != null) {
				intersection.addVertex(vertex.getValue());
				seenVertices.add(vertex);
			}
		}

		for (Edge edge : this.edgeList) {
			Vertex v1 = edge.getVertex1();
			Vertex v2 = edge.getVertex2();
			if (seenVertices.contains(v1) && seenVertices.contains(v2)) {
				intersection.addEdge(edge.getValue(), v1.getValue(), v2.getValue());
			}
		}

		return intersection;
	}
}
