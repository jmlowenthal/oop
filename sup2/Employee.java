package oop.sup2;

public class Employee {
	public static int minimumWage = 3;
	
	private String forename, surname;
	private int money = 0;
	
	public void pay() {
		addMoney(10);
	}
	
	public void addMoney(int amount) {
		money += amount;
	}
	
	public void work() {
		System.out.println(getName() + " bashes buttons.");
	}
	
	public String getName() {
		return forename + " " + surname;
	}
}