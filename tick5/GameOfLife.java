package oop.tick5;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class GameOfLife {
	private World mWorld = null;
	private PatternStore store;
	private List<World> cachedWorlds = new ArrayList<World>();
	
	public GameOfLife(String src) throws IOException {
		store = new PatternStore(src);
	}
	
	public GameOfLife(PatternStore ps) throws IOException {
		store = ps;
	}

	public void play() throws IOException {
		String inp = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (!inp.equals("q")) {
			System.out.print("(l)ist, (p)lay <#>, (f)orward, (b)ackward: ");
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
							mWorld = new PackedWorld(p);
						}
						else {
							mWorld = new ArrayWorld(p);
						}
						cachedWorlds.clear();
						cachedWorlds.add(mWorld);
						mWorld.print();
					}
					catch (NumberFormatException e) {
						continue;
					}
					catch (PatternFormatException e) {
						System.out.println(e.getMessage());
						mWorld = null;
					}
					break;
				
				case "f":
					if (mWorld == null) break;
					try {
						mWorld = cachedWorlds.get(mWorld.getGenerationCount() + 1);
					}
					catch (IndexOutOfBoundsException e) {
						mWorld = copyWorld(true);
						mWorld.nextGeneration();
						cachedWorlds.add(mWorld);
					}
					mWorld.print();
					break;
					
				case "b":
					if (mWorld != null) {
						if (mWorld.getGenerationCount() < 1) {
							System.out.println("No mWorlds to go back to");
						}
						else {
							mWorld = cachedWorlds.get(mWorld.getGenerationCount() - 1);
							mWorld.print();
						}
					}
					break;

				case "q":
					return;
			}
		}
	}
	
	public void print() {
		mWorld.print();
	}
	
	private World copyWorld(boolean clone) {
		if (clone) {
			try {
				return (World)mWorld.clone();
			}
			catch (CloneNotSupportedException e) {
				return null;
			}
		}
		else {
			/*if (this instanceof ArrayWorld) {
				return new ArrayWorld(mWorld);
			}
			else if (this instanceof PackedWorld) {
				return new PackedWorld(mWorld);
			}
			else {
				return null;
			}*/
			try {
				return mWorld.getClass().getConstructor(mWorld.getClass()).newInstance(mWorld);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
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
