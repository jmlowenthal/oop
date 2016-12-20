package oop.tick5;

import java.util.*;

public abstract class World implements Cloneable {
	private int mGeneration = 0;
	private Pattern mPattern;
	
	public World(String format) throws PatternFormatException {
		mPattern = new Pattern(format);
	}

	public World(Pattern p) {
		mPattern = p;
	}
	
	public World(World w) {
		mGeneration = w.mGeneration;
		mPattern = w.mPattern;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return (World)super.clone();
	}
	
	public int getWidth() {
		return mPattern.getWidth();
	}
	
	public int getHeight() {
		return mPattern.getHeight();
	}
	
	public int getGenerationCount() {
		return mGeneration;
	}
	
	protected void incrementGenerationCount() {
		++mGeneration;
	}
	
	public Pattern getPattern() {
		return mPattern;
	}
	
	public void nextGeneration() {
		//ArrayList<Vector2> changes = new ArrayList<Vector2>();
		ArrayList<Integer> changesX = new ArrayList<Integer>();
		ArrayList<Integer> changesY = new ArrayList<Integer>();
		
		for (int x = 0; x < getWidth(); ++x) {
			for (int y = 0; y < getHeight(); ++y) {
				boolean v = computeCell(x, y);
				if (v != getCell(x, y)) //changes.add(new Vector2(x, y));
				{
					changesX.add(x);
					changesY.add(y);
				}
			}
		}
		
		/*for (Vector2 cell : changes) {
			setCell(cell.x, cell.y, !getCell(cell.x, cell.y));
		}*/
		
		Iterator<Integer> xs = changesX.iterator();
		Iterator<Integer> ys = changesY.iterator();
		
		while (xs.hasNext() && ys.hasNext()) {
			int x = xs.next(); int y = ys.next();
			setCell(x, y, !getCell(x, y));
		}
		
		incrementGenerationCount();
	}
	
	public int countNeighbours(int x, int y) {
		int count = 0;
		for (int i = -1; i < 2; ++i){
			for (int j = -1; j < 2; ++j) {
				if (i == 0 && j == 0) continue;
				if (getCell(x + i, y + j)) ++count;
			}
		}
		return count;
	}
	
	public boolean computeCell(int x, int y) {
		int neighbours = countNeighbours(x, y);
		return getCell(x, y) ? neighbours > 1 && neighbours < 4 : neighbours == 3;
	}
	
	public void print() {
		String board = "- " + Integer.toString(mGeneration) + "\n";
		for (int j = 0; j < getHeight(); ++j) {
			for (int i = 0; i < getWidth(); ++i) {
				board += (getCell(i, j) ? "#" : "_");
			}
			board += "\n";
		}
		System.out.println(board);
	}
	
	public void copy(World w) {
		for (int x = 0; x < w.getWidth(); ++x) {
			for (int y = 0; y < w.getHeight(); ++y) {
				setCell(x, y, w.getCell(x, y));
			}
		}
	}
	
	public abstract boolean getCell(int x, int y);
	public abstract void setCell(int x, int y, boolean v);
}
