import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * This powerup gives you an extra life after seven monsters are killed.
 *
 * @author colomeda.
 *         Created Feb 16, 2018.
 */
public class OneUp extends GameObject {
	public Rectangle2D.Double rect;
	private BufferedImage image;
	/**
	 * 
	 * This constructor creates a OneUp object at the starting coordinates.
	 *
	 * @param x - starting x coordinates
	 * @param y - starting y coordinates
	 * @param handler - game's object handler
	 */
	public OneUp(int x, int y, GameObjectHandler handler) {
		super(x, y, handler);
		this.rect = new Rectangle2D.Double(this.x, this.y, this.BLOCKSIZE, this.BLOCKSIZE);
		try {
			image = ImageIO.read(new File("oneup.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing Hero: " + e);
		}
	}

	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(image, this.x, this.y, null);
	}

	@Override
	public void die() {
		// does nothing
	}

	@Override
	void collideWith(GameObject other) {
		other.collideWithOneUp(this);
	}

	@Override
	void collideWithDirt(Dirt thisDirt) {
		// does nothing
	}

	@Override
	void collideWithHero(Hero hero) {
		hero.collideWithOneUp(this);
	}

	@Override
	void collideWithMonster(Monster thisMonster) {
		// does nothing
	}

	@Override
	void collideWithRock(Rock thisRock) {
		// does nothing
	}

	@Override
	void collideWithFire(Double fireRect) {
		// does nothing
	}

	@Override
	void collideWithPump(Double pumpRect) {
		// does nothing
	}

	@Override
	void collideWithFruit(Fruit fruit) {
		// does nothing
	}

	@Override
	void collideWithOneUp(OneUp oneUp) {
		// Does nothing		
	}

}