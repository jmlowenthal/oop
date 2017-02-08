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
            double dx = (double)this.getWidth() / mWorld.getWidth();
            double dy = (double)this.getHeight() / mWorld.getHeight();
            double d = Math.min(dx, dy);

            g.setColor(Color.BLACK);
			for (int x = 0; x < mWorld.getWidth(); ++x) {
				for (int y = 0; y < mWorld.getHeight(); ++y) {
                    if (mWorld.getCell(x, y)) {
                        g.fillRect((int)(x * d), (int)(y * d), (int)d, (int)d);
                    }
				}
			}
            
            g.drawString("Generation: " + mWorld.getGenerationCount(), 10, this.getHeight() - 20);
		
            g.setColor(Color.LIGHT_GRAY);
            for (int x = 0; x <= mWorld.getWidth(); ++x) {
                g.drawLine((int)(x * d), 0, (int)(x * d), (int)(mWorld.getHeight() * d));
            }

            for (int y = 0; y <= mWorld.getHeight(); ++y) {
                g.drawLine(0, (int)(y * d), (int)(mWorld.getWidth() * d), (int)(y * d));
            }
        }
    }

    public void display(World w) {
        mWorld = w;
        repaint();
    }
}