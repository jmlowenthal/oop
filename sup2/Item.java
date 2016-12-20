package oop.sup2;

public class Item {
	private float price = 1.0f;
	private float taxRate = 0.2f;
	
	public float getSalePrice() {
		return price * (1.0f + taxRate);
	}
}