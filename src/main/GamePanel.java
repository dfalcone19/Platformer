package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInput;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel {

	private MouseInput mouse;
	private Game game;

	public GamePanel(Game game) {
		this.game = game;
		mouse = new MouseInput(this);

		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		setPanelSize();
	}


	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}

	public void paintComponent(Graphics g) {
		requestFocus(true);
		super.paintComponent(g);
		
		game.render(g);
		
	}
	
	public Game getGame() {
		return game;
	}

}
