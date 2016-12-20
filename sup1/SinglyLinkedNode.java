package oop.sup1;

public class SinglyLinkedNode {
	private SinglyLinkedNode next;
	private int value;
	
	public SinglyLinkedNode(int a) {
		next = null;
		value = a;
	}
	
	public SinglyLinkedNode() {
		this(0);
	}
	
	public SinglyLinkedNode getNext() {
		return next;
	}
	
	protected void setNext(SinglyLinkedNode node) {
		next = node;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int v) {
		value = v;
	}
	
	public SinglyLinkedNode insertAfter(int value) {
		SinglyLinkedNode node = new SinglyLinkedNode(value);
		node.setNext(this.getNext());
		this.setNext(node);
		return node;
	}
}