package oop.sup2;

public class Manager extends Employee {
	private long privateAssets = 0;

	@Override
	public void pay() {
		super.pay();
		privateAssets += Math.random() * 1000000;
	}
	
	@Override
	public void work() {
		System.out.println(getName() + " 'manages'.");
	}
}