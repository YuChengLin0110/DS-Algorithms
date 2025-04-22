package dataStructures.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * minStack 不儲存實際資料，只儲存當前對應的最小值 每次 push，minStack 就 push 一個 目前的最小值 每次 pop，兩邊都要
 * pop ，才能讓最小值同步更新
 */

// <T extends Comparable<T>> 是 限制 T 一定要有 implements Comparable<> ， 這樣才能使用 compareTo() 比較大小
public class MinStack<T extends Comparable<T>> {
	// 主 stack 實際資料儲存在這
	private Deque<T> stack;
	// 輔助 stack 只負責當前最小值
	private Deque<T> minStack;

	public MinStack() {
		stack = new ArrayDeque<>();
		minStack = new ArrayDeque<>();
	}

	public void push(T data) {
		stack.offerFirst(data);

		if (minStack.isEmpty() || compare(data, minStack.peek()) <= 0) {
			minStack.offerFirst(data);
		}
	}

	public T pop() {
		if (stack.peek().equals(minStack.peek())) {
			minStack.pollFirst();
		}

		return stack.pollFirst();
	}

	public T top() {
		return stack.peekFirst();
	}

	public T getMin() {
		return minStack.peekFirst();
	}

	// a < b return -1
	// a == return 0
	// a > b return 1
	private int compare(T a, T b) {
		return a.compareTo(b);
	}

	public static void main(String[] args) {
		MinStack<Integer> minStack = new MinStack<>();

		minStack.push(5);
		minStack.push(3);
		minStack.push(7);
		minStack.push(2);

		System.out.println("Min: " + minStack.getMin());

		System.out.println("Top: " + minStack.top());

		minStack.pop();
		System.out.println("Min after pop: " + minStack.getMin());

		minStack.pop();
		System.out.println("Min after pop: " + minStack.getMin());

		minStack.pop();
		System.out.println("Min after pop: " + minStack.getMin());
	}
}
