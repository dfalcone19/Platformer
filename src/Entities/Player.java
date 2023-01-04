package entities;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.PlayerConstants.getSpriteAmount;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player extends Entity {

	private BufferedImage[][] animations;
	private int tick, index, speed = 15;
	private int playerAction = IDLE;
	private boolean moving = false;
	private boolean attacking = false;
	private boolean left, up, right, down;
	private float playerSpeed = 2.0f;

	public Player(float x, float y) {
		super(x, y);
		loadAnimations();
	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][index], (int) x, (int) y, 256, 160, null);
	}

	private void setAnimation() {
		
		int startAni = playerAction;
		
		if (moving) {
			playerAction = RUNNING;
		} else {
			playerAction = IDLE;
		}
		
		if (attacking) {
			playerAction = ATTACK_1;
		}
		
		if (startAni != playerAction) {
			resetTick();
		}

	}

	private void resetTick() {
		tick = 0;
		index = 0;
	}

	private void updatePos() {
		
		moving = false;
		
		if (left && !right) {
			x -= playerSpeed;
			moving = true;
		} else if (right && !left) {
			x += playerSpeed;
			moving = true;
		}
		
		if (up && !down) {
			y -= playerSpeed;
			moving = true;
		} else if (down && !up) {
			y += playerSpeed;
			moving = true;
		}

	}

	private void updateAnimationTick() {

		tick++;
		if (tick >= speed) {
			tick = 0;
			index++;
			if (index >= getSpriteAmount(playerAction)) {
				index = 0;
				attacking = false;
			}
		}

	}

	private void loadAnimations() {

		InputStream is = getClass().getResourceAsStream("/player_sprites.png");

		try {
			BufferedImage img = ImageIO.read(is);
			animations = new BufferedImage[9][6];
			for (int i = 0; i < animations.length; i++) {
				for (int j = 0; j < animations[i].length; j++) {
					animations[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void resetDirBooleans() {
		left = false;
		right = false;
		down = false;
		up = false;
	}
	
	public void setAttack(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	

}
