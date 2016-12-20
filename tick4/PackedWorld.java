package oop.tick4;

public class PackedWorld extends World implements Cloneable {
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
	
	public PackedWorld(PackedWorld w) throws PatternFormatException {
		super(w);
		init();
		copy(w);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		PackedWorld w = (PackedWorld)super.clone();
		w.copy(this);
		return w;
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
