package oop.sup3;

import java.util.*;
import java.io.*;

public class Game {
	Pet pet;
	UserInterface ui;
	
	public Game(Pet p, UserInterface u) {
		pet = p;
		ui = u;
	}
	
	private boolean step() {
		ui.drawPet(pet);
		List<Action> actions = ui.getActions();
		for (Action action : actions) {
			ActionStatus status;
			switch (action) {
				case FEED:
					ui.feed(pet, pet.feed());
					break;
				case PLAY:
					ui.play(pet, pet.play());
					break;
				case SLEEP:
					ui.sleep(pet, pet.sleep());
					break;
			}
		}
		
		return pet.isAlive();
	}
	
	public void run() {
		long previous = System.nanoTime();
		long step = 1000000000;
		while (step()) {
			long current = System.nanoTime();
			long elapsed = current - previous;
			long steps = elapsed / step;
			pet.update((int)steps);
			previous += steps * step;
		}
	}
	
	public static void main(String[] args) {
		try {
			UserInterface ui = new ConsoleInterface();
			Game g = new Game(ui.pickPet(), ui);
			g.run();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}