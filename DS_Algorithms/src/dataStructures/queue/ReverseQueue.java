package dataStructures.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseQueue {

	/**
	 * 雖然可以用 Deque 的雙端特性直接處理，但這裡主要是練習只使用 Queue 和 Stack 的特性 將 Queue FIFO 取出 放入 Stack
	 * 再利用 Stack LIFO 放回 Queue 達成反轉
	 */
	public static <T> void reverseQueue(Deque<T> queue) {
		Deque<T> stack = new ArrayDeque<>();

		while (!queue.isEmpty()) {
			stack.offerFirst(queue.pollFirst());
		}

		while (!stack.isEmpty()) {
			queue.offerLast(stack.pollFirst());
		}
	}

	public static void main(String[] args) {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.offerLast(1);
		queue.offerLast(2);
		queue.offerLast(3);
		queue.offerLast(4);

		System.out.println("Original Queue: " + queue);

		ReverseQueue.reverseQueue(queue);

		System.out.println("Reversed Queue: " + queue);
	}
}
