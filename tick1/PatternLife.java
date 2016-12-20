package uk.ac.cam.jl946.oop.tick1;

public class PatternLife extends ArrayLife {
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