package dataStructures.linkedList;

public class MyLinkedListTest {

	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();

		System.out.println("Append Test");
		list.append(10);
		list.append(20);
		list.append(30);
		System.out.println(list);

		System.out.println("InsertAt Test");
		list.insertAt(1, 15);
		System.out.println(list);

		list.insertAt(0, 5);
		System.out.println(list);

		list.insertAt(list.length(), 35);
		System.out.println(list);

		System.out.println("Delete Test");
		list.delete(15);
		list.delete(5);
		list.delete(35);
		System.out.println(list);

		System.out.println("DeleteAt Test");
		list.deleteAt(1);
		System.out.println(list);

		System.out.println("Reverse Test");
		list.reverse();
		System.out.println(list);

		System.out.println("Contains Test");
		System.out.println(list.contains(10));
		System.out.println(list.contains(99));

		System.out.println("Clear Test");
		list.clear();
		System.out.println(list);
		System.out.println("Size = " + list.length());
	}
}
