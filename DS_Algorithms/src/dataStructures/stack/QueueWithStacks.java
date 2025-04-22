package dataStructures.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 用兩個Stack 模擬 Queue inStack 負責接收資料 push outStack 負責輸出資料 pop、peek
 * 
 * 當 outStack 為空時，將 inStack 的資料 反轉搬移 到 outStack 就可以模擬 Queue 的 FIFO
 */
public class QueueWithStacks<T> {
	private Deque<T> inStack;
	private Deque<T> outStack;

	public QueueWithStacks() {
		inStack = new ArrayDeque<>();
		outStack = new ArrayDeque<>();
	}

	public void push(T data) {
		inStack.offerFirst(data);
	}

	public T pop() {

		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.offerFirst(inStack.pollFirst());
			}
		}

		return outStack.pollFirst();
	}

	public T peek() {

		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.offerFirst(inStack.pollFirst());
			}
		}

		return outStack.peek();
	}

	public static void main(String[] args) {
		QueueWithStacks<Integer> queue = new QueueWithStacks<>();

		queue.push(1);
		queue.push(2);
		queue.push(3);

		System.out.println(queue.peek());

		System.out.println(queue.pop());
		System.out.println(queue.pop());
	}
}
