package oop.sup3;

public class Dog extends Pet {
	@Override
	public String getType() {
		return "dog";
	}
	
	@Override
	public ActionStatus feed() {
		health += 40;
		energy += 10;
		return ActionStatus.SUC‎CESS;
	}
	
	@Override
	public ActionStatus play() {
		if (energy > 60) {
			health += 20;
			energy -= 50;
			return ActionStatus.SUC‎CESS;
		}
		else {
			return ActionStatus.FAILED_TOO_TIRED;
		}
	}
	
	@Override
	public ActionStatus sleep() {
		energy += 100;
		return ActionStatus.SUC‎CESS;
	}
}