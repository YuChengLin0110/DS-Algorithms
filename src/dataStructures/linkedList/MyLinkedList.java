package dataStructures.linkedList;

public class MyLinkedList {
	Node head;
	Node tail;
	int size = 0;

	public void append(int data) {
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			tail = head;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node node = head;

		while (node != null) {
			sb.append(node.data);
			if (node.next != null) {
				sb.append(" <-> ");
			}

			node = node.next;
		}
		return sb.toString();
	}

	public void delete(int target) {
		if (head == null)
			return;

		if (head.data == target) {
			head = head.next;

			if (head != null) {
				head.prev = null;
			} else {
				tail = null;
			}

			size--;
			return;
		}

		// head 不是 target 才執行以下
		Node node = head;

		while (node != null) {
			if (node.data == target) {
				if (node.next != null) {
					node.next.prev = node.prev;
				} else {
					tail = node.prev;
				}

				if (node.prev != null) {
					node.prev.next = node.next;
				}

				size--;
				return;
			}

			node = node.next;
		}
	}

	public void reverse() {
		if (head == null) {
			tail = null;
			return;
		}
		;

		Node node = head;
		Node prev = null;
		Node next;
		tail = head;

		while (node != null) {
			next = node.next;
			node.next = prev;
			node.prev = next;
			prev = node;
			node = next;
		}

		head = prev;
		tail.prev = null;
	}

	public int length() {
		int length = 0;
		Node node = head;
		while (node != null) {
			length++;
			node = node.next;
		}
		return length;
	}

	public void insertAt(int index, int data) {
		if (index < 0 || index > size) {
			System.out.println("Error : index out of bounds !");
			return;
		}

		if (index == 0) {
			Node newNode = new Node(data);
			newNode.next = head;

			if (head != null) {
				head.prev = newNode;
			}
			head = newNode;

			if (head.next == null) {
				tail = head;
			}

			size++;
			return;
		}

		if (index == size) {
			append(data);
			return;
		}

		Node node = head;
		Node newNode = new Node(data);
		int currIndex = 0;

		// 找到要插入的前一個位置
		while (node != null && currIndex < index - 1) {
			node = node.next;
			currIndex++;
		}

		if (node != null) {
			newNode.next = node.next;

			if (node.next != null) {
				node.next.prev = newNode;
			}

			node.next = newNode;
			newNode.prev = node;
			size++;
		}

	}

	public void deleteAt(int index) {
		if (index < 0 || index >= size) {
			System.out.println("Error : index out of bound !");
			return;
		}

		if (index == 0) {
			if (head != null) {
				head = head.next;

				if (head != null) {
					head.prev = null;
				} else {
					tail = null;
				}

				size--;
			}
			return;
		}

		// 刪除的點是tail
		if (index == size - 1) {
			if (tail != null) {
				tail = tail.prev;

				if (tail != null) {
					tail.next = null;
				}
				size--;
			}
			return;
		}

		// 刪除的點 不是head 也不是 tail 才執行以下
		Node node = head;
		int currIndex = 0;

		// 找到要刪除的位置
		while (node != null && currIndex < index) {
			node = node.next;
			currIndex++;
		}

		if (node != null) {
			node.prev.next = node.next;

			if (node.next != null) {
				node.next.prev = node.prev;
			}
			size--;
		}
	}

	public boolean contains(int target) {
		Node node = head;

		while (node != null) {
			if (node.data == target) {
				return true;
			}
			node = node.next;
		}
		return false;
	}

	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
}
