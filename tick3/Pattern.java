package oop.tick3;

public class Pattern implements Comparable<Pattern> {
	public static String usage = "Usage: NAME:AUTHOR:WIDTH:HEIGHT:STARTUPPERCOL:STARTUPPERROW:CELLS";
	
	protected String name;
	protected String author;
	protected int width;
	protected int height;
	protected int col;
	protected int row;
	protected String cells;
	
	public Pattern(String format) throws PatternFormatException {
		String[] args = format.split(":");
		
		if (args.length != 7) throw new PatternFormatException("Incorrect number of fields in pattern (found " + args.length + ")");
		
		name = args[0];
		author = args[1];
		
		try {
			width = Integer.decode(args[2]);
		}
		catch (NumberFormatException e) {
			throw new PatternFormatException("Could not interpret the width field (" + args[2] + "given)");
		}
		
		try {
			height = Integer.decode(args[3]);
		}
		catch (NumberFormatException e) {
			throw new PatternFormatException("Could not interpret the height field (" + args[3] + "given)");
		}
		
		try {
			col = Integer.decode(args[4]);
		}
		catch (NumberFormatException e) {
			throw new PatternFormatException("Could not interpret the start column field (" + args[4] + "given)");
		}
		
		try {
			row = Integer.decode(args[5]);
		}
		catch (NumberFormatException e) {
			throw new PatternFormatException("Could not interpret the start row field (" + args[5] + "given)");
		}
			
		cells = args[6];
	}
	
	public void initialise(World world) throws PatternFormatException {
		String[] rows = cells.split(" ");
		for (int i = 0; i < rows.length; ++i) {
			char[] thisrow = rows[i].toCharArray();
			for (int j = 0; j < thisrow.length; ++j) {
				
				if (thisrow[j] != '1' && thisrow[j] != '0')
					throw new PatternFormatException("Malformed pattern '" + cells + "'");
				
				world.setCell(row + j, col + i, thisrow[j] == '1');
				
			}
		}
	}
	
	@Override
	public int compareTo(Pattern other) {
		return getName().compareTo(other.getName());
	}

	public String getName() {
		return name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getStartCol() {
		return col;
	}
	
	public int getStartRow() {
		return row;
	}
	
	public String getCells() {
		return cells;
	}
}
