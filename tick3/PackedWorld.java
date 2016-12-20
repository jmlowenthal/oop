package oop.tick3;

public class PackedWorld extends World {
	private long world;
	
	private void init() throws PatternFormatException {
		if (getWidth() * getHeight() > 64) {
			throw new PatternFormatException("Not repesentable by a long");
		}
		world = 0L;
		getPattern().initialise(this);
	}

	public PackedWorld(String format) throws PatternFormatException {
		super(format);
		init();
	}

	public PackedWorld(Pattern p) throws PatternFormatException {
		super(p);
		init();
	}
	
	@Override
	public boolean getCell(int x, int y) {
		int pos = y * getWidth() + x;
		return (x >= 0 && y >= 0 && x < getWidth() && y < getHeight())
			&& (((world >>> pos) & 1) != 0);
	}
	
	@Override
	public void setCell(int x, int y, boolean v) {
		if (x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
			int pos = y * getWidth() + x;
			world = (world & ~(1L << pos)) | (v ? 1L << pos : 0L);
		}
	}
}
