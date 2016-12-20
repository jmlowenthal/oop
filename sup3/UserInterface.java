package oop.sup3;

import java.util.*;

public interface UserInterface {	
	public abstract Pet pickPet() throws Exception;

	public abstract List<Action> getActions();
	public abstract void drawPet(Pet pet);
	
	public abstract void feed(Pet p, ActionStatus as);
	public abstract void play(Pet p, ActionStatus as);
	public abstract void sleep(Pet p, ActionStatus as);
}