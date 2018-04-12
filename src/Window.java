import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Window extends Canvas {
	/**
	 * The window that DigDug runs in.
	 */

	public Window(int width, int height, String title, final Main game) {
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.add(game);

		KeyListener userControl = new UserControlListener(game);
		frame.addKeyListener(userControl);

		game.start();
	}
}
