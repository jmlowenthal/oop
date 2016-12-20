package oop.sup3;

public class Squid extends Pet {
	@Override
	public String getType() {
		return "squid";
	}
	
	@Override
	public ActionStatus feed() {
		health += 1;
		return ActionStatus.SUC‎CESS;
	}
	
	@Override
	public ActionStatus play() {
		return ActionStatus.SUC‎CESS;
	}
	
	@Override
	public ActionStatus sleep() {
		energy += 1;
		return ActionStatus.SUC‎CESS;
	}
	
	@Override
	public void update(int dt) {
		age += dt;
		// SQUID THE IMMORTAL
	}
}