package dataStructures.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class MyGraph<T> {
	private final Map<T, List<Edge<T>>> adjList = new HashMap<>();

	public void addVertex(T vertex) {
		adjList.putIfAbsent(vertex, new ArrayList<>());
	}

	public void addEdge(T from, T to, int weight) {
		adjList.putIfAbsent(from, new ArrayList<>());
		adjList.putIfAbsent(to, new ArrayList<>());
		adjList.get(from).add(new Edge<>(to, weight));
	}
	
	// 無向圖，節點互相連結
	public void addUndirectedEdge(T a, T b, int weight) {
		addEdge(a, b, weight);
		addEdge(b, a, weight);
	}

	public List<T> getVertex() {
		return new ArrayList<>(adjList.keySet());
	}

	public List<T> getNeighbors(T vertex) {
		List<T> neighbors = new ArrayList<>();

		for (Edge<T> edge : adjList.getOrDefault(vertex, new ArrayList<>())) {
			neighbors.add(edge.to);
		}
		return neighbors;
	}

	public void printBFS(T start) {
		Set<T> visited = bfs(start);

		for (T node : visited) {
			System.out.print(node + " ");
		}
		System.out.println();
	}

	public void printDFS(T start) {
		Set<T> visited = dfs(start);

		for (T node : visited) {
			System.out.print(node + " ");
		}
		System.out.println();
	}

	public boolean hasPath(T start, T end) {
		Set<T> visited = new HashSet<>();
		return hasPathDFS(start, end, visited);
	}

	public boolean isConnected() {
		if (adjList.isEmpty()) {
			return true;
		}

		T start = adjList.keySet().iterator().next();
		Set<T> visited = dfs(start);

		return visited.size() == adjList.size();
	}
	
	/**
	 * 使用 DFS 的方式進行拓撲排序
	 * 適用於有向無環圖，會回傳一個任務的執行順序
	 * 如果圖中有環，會印出提示並回傳空 List
	 */
	public List<T> topologicalSortDFS(){
		Set<T> visited = new HashSet<>();
		Set<T> visiting = new HashSet<>(); // 記錄目前處理中的節點，避免遇到環
		Deque<T> stack = new ArrayDeque<>(); // 從遞迴回來時，把節點加進 stack，最後反轉就是正確順序
		
		for(T node : adjList.keySet()) {
			if(!visited.contains(node)) {
				if(!topologicalSortDFSHelper(node, visited, visiting, stack)) {
					System.out.println("Graph has cycle");
					return new ArrayList<>();
				}
			}
		}
		
		List<T> sortList = new ArrayList<>();
		
		while(!stack.isEmpty()) {
			sortList.add(stack.pollFirst());
		}
		
		return sortList;
	}
	
	/**
	 * 使用 BFS（Kahn's Algorithm）進行拓撲排序
	 * 適用於 有向無環圖
	 * 如果圖中有環，會印出提示並回傳空 List。
	 */
	public List<T> topologicalSortBFS() {
		List<T> result = new ArrayList<>();
		Map<T, Integer> indegree = new HashMap<>(); // 紀錄每個節點的入度
		
		// 初始化每個節點入度為0
		for(T node : adjList.keySet()) {
			indegree.put(node, 0);
		}
		
		// 計算入度
		for(List<Edge<T>> edges : adjList.values()) {
			for(Edge<T> edge : edges) {
				indegree.put(edge.to, indegree.getOrDefault(edge.to, 0) + 1);
			}
		}
		
		// 入度為 0 的放入 queue ，可以先處理
		Deque<T> queue = new ArrayDeque<>();
		for(T node : indegree.keySet()) {
			if(indegree.get(node) == 0) {
				queue.offerLast(node);
			}
		}
		
		while(!queue.isEmpty()) {
			T node = queue.pollFirst();
			result.add(node); // 此節點可以完成
			
			for(Edge<T> edge : adjList.getOrDefault(node, new ArrayList<>())) {
				T neighbor = edge.to;
				indegree.put(neighbor, indegree.get(neighbor) - 1);
				
				// 入度變 0 ，代表他的前置已完成，可以加入 queue 去處理
				if(indegree.get(neighbor) == 0) {
					queue.offerLast(neighbor);
				}
			}
		}
		
		// 最後結果數量不等於所有節點數量，表示有環
		if(result.size() != adjList.size()) {
			System.out.println("Graph has cycle");
			
			return new ArrayList<>();
		}
		
		return result;
	}

	private Set<T> bfs(T start) {
		Set<T> visited = new LinkedHashSet<>();
		Queue<T> queue = new LinkedList<>();

		queue.offer(start);
		visited.add(start);

		while (!queue.isEmpty()) {
			T current = queue.poll();

			for (Edge<T> edge : adjList.getOrDefault(current, new ArrayList<>())) {
				if (!visited.contains(edge.to)) {
					visited.add(edge.to);
					queue.offer(edge.to);
				}
			}
		}
		return visited;
	}

	private Set<T> dfs(T start) {
		Set<T> visited = new LinkedHashSet<>();

		dfsHelper(start, visited);

		return visited;
	}

	private void dfsHelper(T current, Set<T> visited) {
		visited.add(current);

		for (Edge<T> edge : adjList.getOrDefault(current, new ArrayList<>())) {
			if (!visited.contains(edge.to)) {
				dfsHelper(edge.to, visited);
			}
		}
	}

	private boolean hasPathDFS(T current, T target, Set<T> visited) {
		// 泛型，不確定資料型態，用equals 比較安全
		if (current.equals(target)) {
			return true;
		}

		visited.add(current);

		for (Edge<T> edge : adjList.getOrDefault(current, new ArrayList<>())) {
			if(!visited.contains(edge.to)) {
				if (hasPathDFS(edge.to, target, visited)) {
					return true;
				}
			}
		}
		return false;
	}
	
	// false 表示有環
	private boolean topologicalSortDFSHelper(T current, Set<T> visited, Set<T> visiting, Deque<T> stack) {
		// 此節點已經是處理中，又遇到表示有環
		if(visiting.contains(current)) {
			return false;
		}
		
		visiting.add(current);
		
		for(Edge<T> edge : adjList.getOrDefault(current, new ArrayList<>())) {
			if(!visited.contains(edge.to)) {
				topologicalSortDFSHelper(edge.to, visited, visiting, stack);
			}
		}
		
		// 此節點處理完畢，移出 visiting ， 加入 visited
		visiting.remove(current);
		visited.add(current);
		stack.offerFirst(current);
		
		return true;
	}
}

