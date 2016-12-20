package oop.tick2;

public class ArrayWorld extends World {
	private boolean[][] world;
	
	public ArrayWorld(String format) throws PatternFormatException {
		super(format);
		world = new boolean[getWidth()][getHeight()];
		getPattern().initialise(this);
	}
	
	@Override
	public boolean getCell(int x, int y) {
		return (x >= 0 && y >= 0 && x < getWidth() && y < getHeight())
			&& (world[x][y]);
	}
	
	@Override
	public void setCell(int x, int y, boolean v) {
		if (x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
			world[x][y] = v;
		}
	}
}