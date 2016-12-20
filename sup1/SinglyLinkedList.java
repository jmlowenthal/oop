package oop.sup1;

public class SinglyLinkedList {
	private SinglyLinkedNode front;
	
	public SinglyLinkedList() {
		front = new SinglyLinkedNode();
	}
	
	public int getElement(int n) throws ArrayIndexOutOfBoundsException {
		SinglyLinkedNode node = front;
		for (int i = 0; i < n; ++i) {
			if (node == null) throw new ArrayIndexOutOfBoundsException();
			node = node.getNext();
		}
		return node.getValue();
	}
	
	public int size() {
		SinglyLinkedNode node = front;
		int count = -1;
		while (node != null) {
			++count;
			node = node.getNext();
		}
		return count;
	}
	
	/*
		`pos` refers to the desired index of the new item in the list.
	*/
	public void insert(int pos, int value) throws NullPointerException {
		if (pos == 0) {
			SinglyLinkedNode node = new SinglyLinkedNode(value);
			node.setNext(front);
			front = node;
		}
		else {
			if (front == null) throw new NullPointerException("No list");
			SinglyLinkedNode node = front;
			while (--pos > 0) {
				if (node == null) throw new NullPointerException("Cannot find insertion position");
				node = node.getNext();
			}
			node.insertAfter(value);
		}
	}
	
	public void remove(int pos) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if (front == null) throw new NullPointerException("No list");
		
		if (pos == 0) {
			front = front.getNext();
		}
		else {
			SinglyLinkedNode node = front;
			while (--pos > 1) {
				if (node == null) throw new ArrayIndexOutOfBoundsException("No such index");
				node = node.getNext();
			}
			
			if (node == null) throw new ArrayIndexOutOfBoundsException("No such index");
			
			SinglyLinkedNode toRemove = node.getNext();
			if (toRemove == null) throw new ArrayIndexOutOfBoundsException("No such index");
			
			node.setNext(toRemove.getNext());
		}
	}
	
	public boolean hasCycles() {
		// Iterate list
		SinglyLinkedNode current = front;
		int i = 0;
		while (current != null) {
			current = current.getNext();
			++i;
			
			// Iterate previous elements to find match
			SinglyLinkedNode check = front;
			int j = 0;
			while (j < i) {
				if (current == check) return true;
				check = check.getNext();
				++j;
			}
		}
		return false;
	}
}