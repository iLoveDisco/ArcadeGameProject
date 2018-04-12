import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Level {
	private GameObjectHandler handler;
	public int currentLevel;
	public int numLevels;
	
	/**
	 * Has a dependency on handler.
	 * Tracks the current level, and the number of levels.
	 */
	public Level(GameObjectHandler handler) {
		this.handler = handler;
		this.currentLevel = 1;
		handler.numLevel = this.currentLevel;
		this.numLevels = 10;
	}
	
	/**
	 * Reads the inputed .txt files.
	 *  - Clears the all object arraylists
	 *  - Nested while/for loop that renders every object
	 *    based on the given CHAR
	 */
	public void readFile() {
		Scanner scanner = null;
		handler.numLevel = this.currentLevel;
		handler.rocks.clear();
		handler.dirts.clear();
		handler.monsters.clear();
		handler.powerups.clear();
		handler.fallenRocks = 0;
		if(currentLevel > 10) {
			return;
		}
		try {
			scanner = new Scanner(new File("level" + this.currentLevel + ".txt"));
		} catch (FileNotFoundException exception) {
			System.out.println("File not found");
			return;
		}
		int valX = 15;
		int valY = 150;
		int deltaX = 53;
		int deltaY = 53;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == 'D') {
					handler.dirts.add(new Dirt(valX, valY, handler));
				}
				if (line.charAt(i) == 'H') {
					Dirt tunnel = new Dirt(valX, valY, handler);
					tunnel.isDug = true;
					handler.dirts.add(tunnel);
					handler.hero = new Hero(valX, valY, handler);
				}
				if (line.charAt(i) == 'R') {
					handler.rocks.add(new Rock(valX, valY, handler));
				}
				if (line.charAt(i) == 'F') {
					Dirt tunnel = new Dirt(valX, valY, handler);
					tunnel.isDug = true;
					handler.dirts.add(tunnel);
					handler.monsters.add(new Fygar(valX, valY, handler));
				}
				if (line.charAt(i) == 'P') {
					Dirt tunnel = new Dirt(valX, valY, handler);
					tunnel.isDug = true;
					handler.dirts.add(tunnel);
					handler.monsters.add(new Pooka(valX, valY, handler));
				}
				if (line.charAt(i) == 'T') {
					Dirt tunnel = new Dirt(valX, valY, handler);
					tunnel.isDug = true;
					handler.dirts.add(tunnel);
				}
				valX += deltaX;
			}
			valY += deltaY;
			valX = 15;
		}
		scanner.close();
	}

}