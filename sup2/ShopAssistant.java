package oop.sup2;

public class ShopAssistant extends Employee {
	@Override
	public void pay() {
		addMoney(minimumWage - 2);
	}
	
	@Override
	public void work() {
		System.out.println(getName() + " stacks shelves.");
	}
}