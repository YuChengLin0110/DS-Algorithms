package dataStructures.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {

	/**
	 * 每遇到一個右括號，就要把 最後一個未配對的左括號 拿出來檢查是否能配對 假設 先放入 { 再放 [ 最後放 ( 那配對時會先處理 ) → ] →
	 * }，順序正好是反過來的
	 */
	public static boolean isValid(String s) {
		Deque<Character> stack = new ArrayDeque<>();

		for (Character c : s.toCharArray()) {

			// 左括號，放入 stack
			if (c == '(' || c == '[' || c == '{') {
				stack.offerFirst(c);
			} else {
				// 如果遇到右括號，但 stack 是空的，表示沒有匹配
				if (stack.isEmpty()) {
					return false;
				}

				char curr = stack.pollFirst();

				if ((c == ')' && curr != '(') || (c == ']' && curr != '[') || (c == '}' && curr != '{')) {
					return false;
				}
			}
		}

		// 最後 stack 要是空的，不然就有左括號沒匹配到
		return stack.isEmpty();
	}

	public static void main(String[] arg) {
		System.out.println(isValid("()[]{}"));
		System.out.println(isValid("(]"));
		System.out.println(isValid("([)]"));
		System.out.println(isValid("{[]}"));
	}
}
