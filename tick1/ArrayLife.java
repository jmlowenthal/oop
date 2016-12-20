package uk.ac.cam.jl946.oop.tick1;

import java.io.*;

public class ArrayLife {
	protected boolean[][] world;
	protected int w, h;
	
	public ArrayLife(int row, int col) {
		world = new boolean[row][col];
		w = row; h = col;
	}
	
	public ArrayLife() {
		this(32, 32);
	}
	
	public ArrayLife(int n) {
		this(n, n);
	}
	
	public ArrayLife(ArrayLife life) {
		this(life.w, life.h);
		for (int i = 0; i < w; ++i) {
			for (int j = 0; j < h; ++j) {
				setCell(i, j, life.getCell(i, j));
			}
		}
	}
	
	public ArrayLife(String format) throws PatternFormatException {
		Pattern p = new Pattern(format);
		w = p.getWidth();
		h = p.getHeight();
		world = new boolean[w][h];
		p.initialise(world);
	}
	
	public boolean getCell(int x, int y) {
		return x > 0 && x < w && y > 0 && y < h && world[x][y];
	}
	
	public void setCell(int x, int y, boolean v) {
		world[x][y] = v;
	}
	
	public void print() {
		String border = "+" + new String(new char[w * 2 + 4]).replace("\0", "-") + "+";
		
		String board = border + "\n";
		for (int j = 0; j < h; ++j) {
			board += "|  ";
			for (int i = 0; i < w; ++i) {
				board += (world[i][j] ? "o " : "  ");
			}
			board += "  |\n";
		}
		board += border;
		System.out.println(board);
	}
	
	private int countNeighbours(int x, int y) {
		int count = 0;
		for (int i = -1; i < 2; ++i){
			for (int j = -1; j < 2; ++j) {
				if (i == 0 && j == 0) continue;
				if (getCell(x + i, y + j)) ++count;
			}
		}
		return count;
	}
	
	private boolean computeCell(int x, int y) {
		int neighbours = countNeighbours(x, y);
		return getCell(x, y) ? neighbours > 1 && neighbours < 4 : neighbours == 3;
	}
	
	public void nextGeneration() {
		ArrayLife old = new ArrayLife(this);
		for (int x = 0; x < w; ++x) {
			for (int y = 0; y < h; ++y) {
				setCell(x, y, old.computeCell(x, y));
			}
		}
	}
	
	public void play() {
		try {
			while (true) {
				print();
				nextGeneration();
				if (System.in.read() == 'q') break;
			}
		}
		catch (IOException e) {
			System.out.println("IOException raised: " + e.getMessage());
		}
	}
	
	public static void main(String[] cmd) {
		try {
			Pattern pattern = new Pattern(String.join(" ", cmd));
			ArrayLife life = new ArrayLife(pattern.getWidth(), pattern.getHeight());
			pattern.initialise(life.world);
			life.play();
		}
		catch (PatternFormatException e) {
			System.out.println("Invalid pattern format: " + e.getMessage());
		}
	}
}