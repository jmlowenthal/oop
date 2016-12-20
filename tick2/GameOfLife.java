package oop.tick2;

import java.io.*;

public class GameOfLife {
	private World world;
	
	public GameOfLife(World w) {
		world = w;
	}
	
	public void play() throws IOException {
		while (true) {
			print();
			world.nextGeneration();
			if (System.in.read() == 'q') break;
		}
	}
	
	public void print() {
		world.print();
	}
	
	public static void main(String[] args) throws IOException {
		try {
			World w = null;
			
			if (args.length < 1) throw new PatternFormatException(Pattern.usage);
			switch (args[0]) {
				case "--array":
					w = new ArrayWorld(args[1]);
					break;
				case "--packed":
					w = new PackedWorld(args[1]);
					break;
				default:
					w = new ArrayWorld(args[0]);
					break;
			}
			
			GameOfLife gol = new GameOfLife(w);
			gol.play();
		}
		catch(PatternFormatException e) {
			System.out.println(e.getMessage());
		}
	}
}
