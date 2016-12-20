package oop.tick3;

import java.io.*;
import java.util.*;

public class GameOfLife {
	private World world = null;
	private PatternStore store;
	
	public GameOfLife(String src) throws IOException {
		store = new PatternStore(src);
	}

	public void play() throws IOException {
		String inp = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (!inp.equals("q")) {
			System.out.print("(l)ist, (p)lay <#>, (f)orward: ");
			inp = in.readLine();

			if (inp.length() <= 0) continue;
			
			String[] cmd = inp.split(" ");
			List<Pattern> names;

			switch (cmd[0]) {
				case "l":
					names = store.getPatternsNameSorted();
					int i = 0;
					for (Pattern p : names) {
						System.out.println(i + ".\t" + p.getName() + " (" + p.getAuthor() + ")");
						++i;
					}
					break;
				
				case "p":
					if (cmd.length != 2) continue;
					names = store.getPatternsNameSorted();
					try {
						Pattern p = names.get(Integer.parseInt(cmd[1]));
						if (p.getWidth() * p.getHeight() <= 64) {
							world = new PackedWorld(p);
						}
						else {
							world = new ArrayWorld(p);
						}
					}
					catch (NumberFormatException e) {
						continue;
					}
					catch (PatternFormatException e) {
						System.out.println(e.getMessage());
						world = null;
					}
					break;
				
				case "f":
					if (world != null) {
						world.print();
						world.nextGeneration();
					}
					break;

				case "q":
					return;
			}
		}
	}
	
	public void print() {
		world.print();
	}

	public static void usage() {
		System.out.println("USAGE: java oop.tick3.GameOfLife <pattern store>");
	}
	
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			usage();
			return;
		}
		
		GameOfLife gol = new GameOfLife(args[0]);
		gol.play();
	}
}
