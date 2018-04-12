import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Dirt extends GameObject {
	public boolean isDug;
	public Rectangle2D.Double rect;
	/**
	 * Constructs the dirt object at the given x and y and passes it into the game object handler
	 * Also creates the visual rectangle for the dirt block
	 * @param x
	 * @param y
	 * @param handler
	 */
	public Dirt(int x, int y, GameObjectHandler handler) {
		super(x, y, handler);
		this.rect = new Rectangle2D.Double(x, y, this.BLOCKSIZE, this.BLOCKSIZE);
	}

	@Override
	public void render(Graphics2D g2) {
		if (isDug) {
			g2.setColor(Color.BLACK);
			g2.fill(this.rect);
			return;
		}
		g2.setColor(Color.ORANGE);
		g2.fill(this.rect);
	}

	@Override
	void collideWith(GameObject other) {
		other.collideWithDirt(this);
	}

	@Override
	void collideWithDirt(Dirt thisDirt) {
		// Does nothing.
	}

	@Override
	void collideWithHero(Hero hero) {
		hero.collideWithDirt(this);
	}

	@Override
	void collideWithMonster(Monster thisMonster) {
		// Does nothing
	}

	@Override
	void collideWithRock(Rock thisRock) {
		thisRock.collideWithDirt(this);
	}

	@Override
	void collideWithFire(Rectangle2D.Double fireRect) {
		// Does nothing
	}

	@Override
	void collideWithPump(Rectangle2D.Double pumpRect) {
		if (pumpRect.intersects(this.rect) && !this.isDug) {
			handler.hero.pumpOut = false;
		}
	}

	@Override
	void collideWithFruit(Fruit fruit) {
		//Does nothing
	}

	@Override
	public void die() {
		//Does nothing
	}

	@Override
	void collideWithOneUp(OneUp oneUp) {
		//does nothing		
	}

}
