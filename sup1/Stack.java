package oop.sup1;

public class Stack {
	private SinglyLinkedList data;
	
	Stack() {
		data = new SinglyLinkedList();
	}
	
	public void push(int v) {
		data.insert(0, v);
	}
	
	public int pop() {
		int v = data.getElement(0);
		data.remove(0);
		return v;
	}
	
	public int peek() {
		return data.getElement(0);
	}
	
	public int size() {
		return data.size();
	}
	
	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(10);
		s.push(11);
		int y = s.size();
		int z = s.peek();
		s.pop();
	}
}