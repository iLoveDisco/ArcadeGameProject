import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

/**
 * This class handles all objects on the screen(The hero, monsters, rocks, dirt,
 * etc.) It's main job is to update the interactions between the classes.
 */
public class GameObjectHandler {
	public int fallenRocks = 0;
	public int killedMonsters = 0;
	public int points = 0;
	public boolean running = true;
	public boolean pause;
	public int lives = 3;
	public Hero hero;
	ArrayList<Rock> rocks = new ArrayList<>();
	ArrayList<Dirt> dirts = new ArrayList<>();
	ArrayList<Monster> monsters = new ArrayList<>();
	ArrayList<GameObject> powerups = new ArrayList<>();
	private BufferedImage image;
	private BufferedImage image2;
	private BufferedImage deadImage;
	public int numLevel;

	/**
	 * This method runs the entire game; it is called when we want to create our objects
	 * and have the hero and monsters interact and move. 
	 * @param g2
	 */
	public void render(Graphics2D g2) {
		handleCollisions();
		if (this.pause) {
			if (this.lives <= 0) {
				this.running = false;
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
			this.pause = false;
			if (!(this.hero == null)) {
				hero.dead = false;
				hero.x = hero.startX;
				hero.y = hero.startY;
			}
		}
		
		for (Dirt d : dirts) {
			d.render(g2);
		}
		for (Rock r : rocks) {
			r.render(g2);
		}
		for (Monster m : monsters) {
			m.render(g2);
		}
		for (GameObject f : powerups) {
			f.render(g2);
		}
		this.hero.render(g2);
		if (this.fallenRocks >= 2) {
			this.createFruit();
			this.fallenRocks = 0;
		}
		if (this.killedMonsters >= 7){
			this.createOneUp();
			this.killedMonsters = 0;
		}
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		g2.drawString("Points: " + this.points, 200, 100);
		g2.drawString("Level: " + this.numLevel, 600, 100);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		g2.drawString("Heroes are made by the paths they choose, not the powers they are graced with - Iron Man", 50, 25);
		try {
			image = ImageIO.read(new File("Iron_Man_face.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing iron man face: " + e);
		}
		if(this.lives == 3) {
			int head1 = 650;
			for(int i = 0; i < 3; i++){
				g2.drawImage(image, 770, head1, null);
				head1-= 250;
			}
		}
		if(this.lives == 2) {
			g2.drawImage(image, 770, 650, null);
			g2.drawImage(image, 770, 400, null);
		}
		if(this.lives == 1) {
			g2.drawImage(image, 770, 650, null);
		}
		
		if (this.hero.dead) {
			for (Monster m : monsters) {
				m.x = m.startX;
				m.y = m.startY;
				m.dx = m.startDX;
				m.dy = m.startDY;
				m.width = 53;
				m.height = 53;
				m.isGhost = false;
			}
			hero.x = hero.startX;
			hero.y = hero.startY;
			this.pause = true;
			this.lives--;
		}
		if (!this.running) {
			try {
				deadImage = ImageIO.read(new File("IronManGameOver.png"));
			} catch (IOException e) {
				throw new RuntimeException("Error drawing iron man face: " + e);
			}
			g2.drawImage(deadImage, 0, 0, null);
		}
	}
	/**
	 * This method handles all the collisions that occur between objects in the game
	 * 
	 */
	private void handleCollisions() {
		ArrayList<GameObject> objects = new ArrayList<>();
		for (Monster m : this.monsters) {
			objects.add(m);
		}
		for (Dirt d : this.dirts) {
			objects.add(d);
		}
		for (Rock r : this.rocks) {
			objects.add(r);
		}
		for (GameObject f : this.powerups) {
			objects.add(f);
		}
		objects.add(hero);
		for (GameObject o1 : objects) {
			for (GameObject o2 : objects) {
				o1.collideWith(o2);
			}
		}
		ArrayList<GameObject> objectsToRemove = new ArrayList<>();
		for (GameObject o1 : objects) {
			if (o1.shouldRemove) {
				objectsToRemove.add(o1);
			}
		}
		for (GameObject o1 : objectsToRemove) {
			if (o1.getClass().getSimpleName().equals("Rock")) {
				this.points += 200;
				this.fallenRocks++;
			} 
			if (o1.getClass().getSimpleName().equals("OneUp")) {
				if(this.lives < 3){
					this.lives++;
				}
			}
			rocks.remove(o1);
			dirts.remove(o1);
			powerups.remove(o1);
			monsters.remove(o1);
		}
	}
	/**
	 * this method creates a fruit object when two rocks fall
	 */
	private void createFruit() {
		this.powerups.add(new Fruit(439, 574, this));
	}
	
	private void createOneUp() {
		this.powerups.add(new OneUp(492, 574, this));
	}
}
