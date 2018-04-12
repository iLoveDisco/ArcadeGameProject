import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fruit extends GameObject {
	BufferedImage image;
	public Rectangle2D.Double rect;
	/**
	 * Constructs the fruit object at the given x and y coordinates and adds it to the game object handler
	 * @param x
	 * @param y
	 * @param handler
	 */
	public Fruit(int x, int y, GameObjectHandler handler) {
		super(x, y, handler);
		this.rect = new Rectangle2D.Double(this.x, this.y, this.BLOCKSIZE, this.BLOCKSIZE);
		try {
			image = ImageIO.read(new File("fruit.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing Hero: " + e);
		}
	}

	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(image, this.x, this.y, null);
	}

	@Override
	void collideWith(GameObject other) {
		other.collideWithFruit(this);
	}

	@Override
	void collideWithDirt(Dirt thisDirt) {
		// Nothing happens
	}

	@Override
	void collideWithHero(Hero hero) {
		hero.collideWithFruit(this);
	}

	@Override
	void collideWithMonster(Monster thisMonster) {
		// Nothing happens
	}

	@Override
	void collideWithRock(Rock thisRock) {
		// Nothing happens.
	}

	@Override
	void collideWithFire(Rectangle2D.Double fireRect) {
		// Nothing happens
	}

	@Override
	void collideWithPump(Rectangle2D.Double pumpRect) {
		// Nothing happens
	}

	@Override
	void collideWithFruit(Fruit fruit) {
		// Nothing happens
	}

	@Override
	public void die() {
		//Does Nothing
	}

	@Override
	void collideWithOneUp(OneUp oneUp) {
		//does nothing		
	}

}
