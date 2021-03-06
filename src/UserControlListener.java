import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * UserControlListener is responsible for all of the keyboard inputs.
 */
public class UserControlListener implements KeyListener {
	public Main game;

	/**
	 * 
	 * Creates the instance of our key listener.
	 *
	 * @param game
	 *            - game's main
	 */
	public UserControlListener(Main game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		/**
		 * Changes between levels.
		 */

		if (e.getKeyCode() == KeyEvent.VK_U) {
			if (game.level.currentLevel >= game.level.numLevels) {
				this.game.isWinScreen = true;
			}
			if (game.level.currentLevel >= game.level.numLevels + 1) {
				return;
			}
			game.level.currentLevel++;
			game.level.readFile();

		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (game.level.currentLevel == 1) {
				return;
			}
			game.level.currentLevel--;
			game.level.readFile();
		}

		if (e.getKeyCode() == KeyEvent.VK_P) {
			if (game.isPaused) {
				game.isPaused = false;
			} else {
				game.isPaused = true;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (game.isStartMenu) {
				game.isStartMenu = false;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			game.isStartMenu = true;
			game.handler = new GameObjectHandler();
			game.level = new Level(game.handler);
			game.level.readFile();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/**
		 * Moves the Hero
		 */
		Hero hero = game.handler.hero;
		GameObjectHandler handler = game.handler;
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			hero.moveLeft();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			hero.moveRight();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			hero.moveUp();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			hero.moveDown();
		}
		/*
		 * Pump
		 */
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			// pump method in hero class
			hero.pump();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Does Nothing
	}
}
