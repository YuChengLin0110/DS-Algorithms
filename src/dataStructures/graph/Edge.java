package dataStructures.graph;

class Edge<T> {
	T from, to;
	int weight;

	Edge(T to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	Edge(T from, T to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}
