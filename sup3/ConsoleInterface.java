package oop.sup3;

import java.io.*;
import java.util.*;

public class ConsoleInterface implements UserInterface {
	BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public Pet pickPet() throws IOException {
		Pet pet = null;
		
		System.out.print("Pick a pet (dog, squid):\n> ");
		while (true) {
			String choice = inp.readLine();
			if (choice.equals("dog")) {
				pet = new Dog();
				break;
			}
			else if (choice.equals("squid")) {
				pet = new Squid();
				break;
			}
		}
		
		System.out.print("Choose a name:\n> ");
		pet.setName(inp.readLine());
		
		return pet;
	}
	
	@Override
	public List<Action> getActions() {
		LinkedList<Action> l = new LinkedList<Action>();
		try {
			System.out.print("> ");
			String input = inp.readLine();
			if (input.equals("feed")) {
				l.add(Action.FEED);
			}
			else if (input.equals("play")) {
				l.add(Action.PLAY);
			}
			else if (input.equals("sleep")) {
				l.add(Action.SLEEP);
			}
		}
		catch (IOException e) {}
		return l;
	}
	
	private void say(Pet pet, String msg) {
		System.out.println(pet.getName() + ":\t\t" + msg);
	}
	
	@Override
	public void drawPet(Pet pet) {
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {}
		
		System.out.format("%n%nName: %s%nAge: %d%nHealth: %d%nEnergy: %d%n%n", pet.getName(), pet.getAge(), pet.getHealth(), pet.getEnergy());
		
		if (pet.getType().equals("dog")) {
			say(pet, "Woof!!");
		}
		else if (pet.getType().equals("squid")) {
			say(pet, "Blooooooooop...");
		}
		
		say(pet, "Hello! It's good to see you.");
		
		if (pet.getHealth() < 10) {
			say(pet, "I'm pretty sick rn.");
		}
		else if (pet.getHealth() < 30) {
			say(pet, "I'm not feeling as good as I could be today.");
		}
		else {
			say(pet, "I'm feeling super, on top form and ready to go!");
		}
	}
	
	@Override
	public void feed(Pet pet, ActionStatus as) {
		switch (as) {
			case SUC‎CESS:
				say(pet, "Yum!, Thank you for the food!");
				break;
			case FAILED_TOO_TIRED:
				say(pet, "I neeeeed sleeeep...");
				break;
			case FAILED_TOO_ILL:
				say(pet, "I don't want to eat, I feel sick");
				break;
			case FAILED_OTHER:
				say(pet, "Yuck!");
				break;
		}
	}
	
	@Override
	public void play(Pet pet, ActionStatus as) {
		switch (as) {
			case SUC‎CESS:
				say(pet, "Yay! I love to play!");
				break;
			case FAILED_TOO_TIRED:
				say(pet, "I just want to sleep...");
				break;
			case FAILED_TOO_ILL:
				say(pet, "I don't want to play, I feel ill");
				break;
			case FAILED_OTHER:
				say(pet, "Go away!");
				break;
		}
	}
	
	@Override
	public void sleep(Pet pet, ActionStatus as) {
		switch (as) {
			case SUC‎CESS:
				say(pet, "Zzzzz...");
				break;
			case FAILED_TOO_ILL:
				say(pet, "I can't sleep, I don't feel good");
				break;
			case FAILED_OTHER:
				say(pet, "I don't want to sleep");
		}
	}
}