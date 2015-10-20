public class One {

	private Node deleteNodeByValue(Node head, int dValue) {
		Node p = head;
		Node next;

		while ((next = p.next) != null) {
			if (next.value == dValue) {
				p.next = next.next;
				return next;
			}

			p = p.next;
		}

		return null;

	}
}

class Node {
	public int value;
	public Node next;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
