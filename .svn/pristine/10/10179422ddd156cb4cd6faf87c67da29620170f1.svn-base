import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * This is a type of monster that moves around in tunnels and has the ability to
 * kill a hero or ghost.
 *
 * @author colomeda. Created Feb 16, 2018.
 */
public class Pooka extends Monster {

	/**
	 * 
	 * This creates the pooka object at the desired starting coordinates
	 * determined by the level txt files.
	 *
	 * @param x
	 *            - starting x coordinate
	 * @param y
	 *            - starting y coordinate
	 * @param handler
	 *            - game's object handler
	 */
	public Pooka(int x, int y, GameObjectHandler handler) {
		super(x, y, handler);
		this.hitBox = new Rectangle2D.Double(this.x, y, this.height, width);
		this.height = this.BLOCKSIZE;
		this.width = this.BLOCKSIZE;
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		super.render(g2);
	}
}
