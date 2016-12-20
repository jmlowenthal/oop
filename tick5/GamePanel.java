package oop.tick5;

import java.awt.Color;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    private World mWorld = null;

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        // Paint the background white
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        if (mWorld != null) {
			double dx = this.getWidth() / mWorld.getWidth();
			double dy = this.getHeight() / mWorld.getHeight();
            int d = (int)Math.min(dx, dy);

			for (int x = 0; x < mWorld.getWidth(); ++x) {
				for (int y = 0; y < mWorld.getHeight(); ++y) {
                    if (mWorld.getCell(x, y)) {
                        g.setColor(Color.BLACK);
                        g.fillRect(x * d, y * d, d, d);
                    }
					g.setColor(Color.LIGHT_GRAY);
                    g.drawRect(x * d, y * d, d, d);
				}
			}

            g.setColor(Color.BLACK);
            g.drawString("Generation: " + mWorld.getGenerationCount(), 10, this.getHeight() - 20);
		}
    }

    public void display(World w) {
        mWorld = w;
        repaint();
    }
}