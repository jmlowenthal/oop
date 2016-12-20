package oop.sup3;

public abstract class Pet {
	protected String name = "<name>";
	protected int health = 100;
	protected int energy = 100;
	protected int age = 0;
	
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public int getHealth() {
		return health;
	}
	
	public boolean isAlive() {
		return getHealth() > 0;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public int getAge() {
		return age;
	}
	
	public abstract String getType();
	public abstract ActionStatus feed();	
	public abstract ActionStatus play();
	public abstract ActionStatus sleep();
	
	public void update(int dt) {
		health -= dt * 20;
		energy -= dt * 10;
		age += dt;
	}
}