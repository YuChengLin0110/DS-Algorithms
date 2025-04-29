package dataStructures.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 用兩個 Queue 模擬 Stack queue1 主要的 stack queue2 暫存資料
 */
public class StackWithQueues<T> {
	private Deque<T> queue1;
	private Deque<T> queue2;

	public StackWithQueues() {
		queue1 = new ArrayDeque<>();
		queue2 = new ArrayDeque<>();
	}

	/**
	 * 將新元素加入到 queue2 ，為了讓他成為最前面的元素，模擬 Stack 的 LIFO 將 queue1 的所有元素移到 queue2
	 * ，保持它們的順序排在新加入的元素之後 將 queue2 與 queue1 交換， queue1 會是主要的容器， queue2 則變回空容器準備下一次使用
	 */
	public void push(T data) {
		queue2.offerLast(data);

		while (!queue1.isEmpty()) {
			queue2.offerLast(queue1.pollFirst());
		}

		Deque<T> temp = queue1;
		queue1 = queue2;
		queue2 = temp;
	}

	public T pop() {
		return queue1.pollFirst();
	}

	public T peek() {
		return queue1.peek();
	}

	public static void main(String[] args) {
		StackWithQueues<Integer> stack = new StackWithQueues<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);

		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.peek());
	}
}
