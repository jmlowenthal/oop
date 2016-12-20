package oop.tick4;

import java.util.Arrays;

public class ArrayWorld extends World implements Cloneable {
	private boolean[][] mWorld;
	private boolean[] mDeadRow;
	
	private void init() throws PatternFormatException {
		mWorld = new boolean[getHeight()][getWidth()];
		getPattern().initialise(this);
	}
	
	public ArrayWorld(String format) throws PatternFormatException {
		super(format);
		mDeadRow = new boolean[getWidth()];
		init();
		reduceRedundancy();
	}

	public ArrayWorld(Pattern p) throws PatternFormatException {
		super(p);
		mDeadRow = new boolean[getWidth()];
		init();
		reduceRedundancy();
	}
	
	public ArrayWorld(ArrayWorld w) throws PatternFormatException {
		super(w);
		mDeadRow = w.mDeadRow;
		init();
		copy(w);
		reduceRedundancy();
		for (int y = 0; y < getHeight(); ++y) {
			if (Arrays.equals(mWorld[y], mDeadRow) && mWorld[y] != mDeadRow) {
				System.out.println(y);
			}
		}
	}
	
	private void reduceRedundancy() {
		for (int y = 0; y < getHeight(); ++y) {
			if (Arrays.equals(mWorld[y], mDeadRow)) {
				mWorld[y] = mDeadRow;
			}
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		ArrayWorld w = (ArrayWorld)super.clone();
		w.mWorld = new boolean[getHeight()][getWidth()];
		w.copy(this);
		return w;
	}
	
	@Override
	public boolean getCell(int x, int y) {
		return (x >= 0 && y >= 0 && x < getWidth() && y < getHeight())
			&& (mWorld[y][x]);
	}
	
	@Override
	public void setCell(int x, int y, boolean v) {
		if (x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
			if (mWorld[y] == mDeadRow) {  // reference equality
				if (v) {
					mWorld[y] = new boolean[getWidth()];
					mWorld[y][x] = v;
				}
				else return;
			}
			else {
				mWorld[y][x] = v;
				if (Arrays.equals(mWorld[y], mDeadRow)) {
					mWorld[y] = mDeadRow;
				}
			}
		}
	}
}
