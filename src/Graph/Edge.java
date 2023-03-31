package Graph;

public class Edge {
	private Integer value;
	private Vertex vertex1;
	private Vertex vertex2;
	
	public Edge(Integer value, Vertex vertex1, Vertex vertex2) {
		this.value = value;
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Vertex getVertex1() {
		return vertex1;
	}

	public void setVertex1(Vertex vertex) {
		this.vertex1 = vertex;
	}

	public Vertex getVertex2() {
		return vertex2;
	}

	public void setVertex2(Vertex vertex) {
		this.vertex2 = vertex;
	}
}
