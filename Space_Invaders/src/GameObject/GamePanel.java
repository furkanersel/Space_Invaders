package GameObject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import GameSounds.Sounds;
import UserInterface.UserInterface;

public class GamePanel extends JPanel implements Runnable {

	File file = new File("src/scores.txt/");

	
	private Sounds sounds;
	private ArrayList<Laser> lasers;
	private ArrayList<Laser> lasersEnemy;
	private ArrayList<Enemy> enemies;
	private BufferedImage imageLaser;
	private BufferedImage imageHeart;
	private BufferedImage imagePlayer;
	private BufferedImage imagePlayer2;
	private BufferedImage imageEnemy1;
	private BufferedImage imageEnemy2;
	private BufferedImage imageEnemyLaser;
	private BufferedImage imageBackGround;
	protected Player player;
	protected Enemy enemy;
	private int score = 0;
	private long startTime;
	private int frames;
	private int fps;
	public boolean gameover = false;
	private boolean playerInvulnerable = false;
	private int AnimationStart = 0;
	private int AnimationEnd = 0;
	private int level = 0;
	private Thread gameLoopThread;
	private int levelChoice;
	

	public GamePanel(int levelChoice) {
		
		player = new Player(imagePlayer, 425, 475, 5, 0, 0, 3, 10);
		enemy = new Enemy(new Rectangle(),30,0);
		sounds = new Sounds();

		setLevelChoice(levelChoice);

		enemy.enemySpeed *= levelChoice;
		
		setPreferredSize(new Dimension(900, 600));
		setFocusable(true);

		try {
			
			imageBackGround = ImageIO.read(new File("img/backgrond.png"));
			imageHeart = ImageIO.read(new File("img/heart.png"));
			imagePlayer = ImageIO.read(new File("img/Player-1.png"));
			imagePlayer2 = ImageIO.read(new File("img/Player1.png"));
			imageLaser = ImageIO.read(new File("img/laser.png"));
			imageEnemy1 = ImageIO.read(new File("img/Enemy1.png"));
			imageEnemy2 = ImageIO.read(new File("img/Enemy2.png"));
			imageEnemyLaser = ImageIO.read(new File("img/EnemyLaser.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	

		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {
					player.playerDy = 0;
				}
				if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {
					player.playerDx = 0;
				}
			}

			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();

				if (code == KeyEvent.VK_UP) {
					player.playerDy = -player.playerSpeed;
				}
				if (code == KeyEvent.VK_DOWN) {
					player.playerDy = player.playerSpeed;
				}
				if (code == KeyEvent.VK_LEFT) {
					player.playerDx = -player.playerSpeed;
				}
				if (code == KeyEvent.VK_RIGHT) {
					player.playerDx = player.playerSpeed;
				}

				player.playerX = Math.max(0, Math.min(player.playerX, getWidth() - imagePlayer.getWidth()));
				player.playerY = Math.max(0, Math.min(player.playerY, getHeight() - imagePlayer.getHeight()));

				if (code == KeyEvent.VK_SPACE) {
					shoot();
				}
			}
		});

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				shoot();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				addMouseMotionListener(new MouseMotionListener() {
					private void movePlayerToMouse(int mouseX, int mouseY) {
						
						int dx = mouseX - (player.playerX + imagePlayer.getWidth() / 2);
						int dy = mouseY - (player.playerY + imagePlayer.getHeight() / 2);

						int absDx = Math.abs(dx);
						int absDy = Math.abs(dy);

						if (absDx > absDy) {
							if (dx > 0) {
								player.playerDx = player.playerSpeed;
							} else {
								player.playerDx = -player.playerSpeed;
							}
							player.playerDy = 0;
						} else {
							if (dy > 0) {
								player.playerDy = player.playerSpeed;
							} else {
								player.playerDy = -player.playerSpeed;
							}
							player.playerDx = 0;
						}
						player.playerX = Math.max(0, Math.min(player.playerX, getWidth() - imagePlayer.getWidth()));
						player.playerY = Math.max(0, Math.min(player.playerY, getHeight() - imagePlayer.getHeight()));
					}

					@Override
					public void mouseDragged(MouseEvent e) {
						movePlayerToMouse(e.getX(), e.getY());
					}

					@Override
					public void mouseMoved(MouseEvent e) {
						movePlayerToMouse(e.getX(), e.getY());
					}
				});
			}

			@Override
			public void mouseExited(MouseEvent e) {
				removeMouseMotionListener(getMouseMotionListeners()[0]);
				player.playerDx = 0;
				player.playerDy = 0;
			}
		});

		lasers = new ArrayList<>();
		lasersEnemy = new ArrayList<>();
		enemies = new ArrayList<>();

	}

	public void StartGameThread() {
		gameLoopThread = new Thread(this);
		gameLoopThread.start();
		createEnemies();
		sounds.soundGame();
	}

	@Override
	public void run() {
		while (!gameover) {
			updateGame();
			repaint();
			AnimationStart++;
			if (AnimationStart > 15) {
				if (AnimationEnd == 0) {
					AnimationEnd = 1;
				} else {
					AnimationEnd = 0;
				}
				AnimationStart = 0;
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected void shoot() {
		sounds.soundShoot();
		Laser laser = new Laser(imageLaser, player.playerX + imagePlayer.getWidth() / 2 - imageLaser.getWidth() / 2,
				player.playerY - imageLaser.getHeight(), 10, imageLaser.getWidth(), imageLaser.getHeight());
		lasers.add(laser);
	}

	protected void updateGame() {
		
		player.updatePlayerPosition();
		updateLasers();
		updateEnemyLasers();
		updateEnemiesPositions();
		checkCollision();
		checkGameOver();
	
	}

	private void createEnemies() {

		Timer enemyAppearsTimer = new Timer(((5000 - 1000 * getLevelChoice())), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!gameover) {
					int randomX = (int) (Math.random() * (getWidth() - imageEnemy1.getWidth()));
					Rectangle enemyRectangle = new Rectangle(randomX, -enemy.enemyHeight, enemy.enemyWidth, enemy.enemyHeight);
					enemies.add(new Enemy(enemyRectangle, enemy.enemyHealth,enemy.enemySpeed));
					shootEnemy();
				}
				level++;
			}
		});
		enemyAppearsTimer.start();
	}

	protected void shootEnemy() {
		Iterator<Enemy> iterator = enemies.iterator();
		while (iterator.hasNext()) {
			Enemy enemy = iterator.next();
			Laser laserEnemy = new Laser(imageEnemyLaser,
					enemy.getRectangle().x - imageEnemyLaser.getWidth() / 2 + imageEnemyLaser.getWidth() / 2,
					enemy.getRectangle().y + imageEnemyLaser.getHeight(), (2 + levelChoice), imageEnemyLaser.getWidth(),
					imageEnemyLaser.getHeight());
			lasersEnemy.add(laserEnemy);
		}
	}

	public int getLevelChoice() {
		return levelChoice;
	}

	public void setLevelChoice(int levelChoice) {
		this.levelChoice = levelChoice;
	}

	protected void updateLasers() {
		Iterator<Laser> iterator = lasers.iterator();
		while (iterator.hasNext()) {
			Laser laser = iterator.next();
			laser.movePlayer();
			if (laser.getY() < -laser.getHeight()) {
				iterator.remove();
			}
		}
	}

	protected void updateEnemyLasers() {
		Iterator<Laser> iterator = lasersEnemy.iterator();
		while (iterator.hasNext()) {
			Laser laser = iterator.next();
			laser.moveEnemy();
			if (laser.getY() > getHeight()) {
				iterator.remove();
			}
		}
	}

	private void updateEnemiesPositions() {
		Iterator<Enemy> iterator = enemies.iterator();
		while (iterator.hasNext()) {
			Enemy enemy = iterator.next();
			enemy.getRectangle().y += enemy.enemySpeed;

			if (enemy.getRectangle().y > getHeight()) {
				iterator.remove();
			}

		}
	}

	private void checkCollision() {
		Iterator<Laser> laserIterator = lasers.iterator();
		while (laserIterator.hasNext()) {
			Laser laser = laserIterator.next();
			Iterator<Enemy> enemyIterator = enemies.iterator();
			while (enemyIterator.hasNext()) {
				Enemy enemy = enemyIterator.next();

				if (laser.getRectangle().intersects(enemy.getRectangle())) {
					enemy.takeDamage();
					laserIterator.remove();
					score += 10;
					sounds.soundHit();
					if (enemy.isDestroyed()) {
						enemyIterator.remove();
						sounds.soundDestroyedEnemy();
					}
					break;
				}
			}
		}

		Rectangle playerRectangle = new Rectangle(player.playerX, player.playerY, imagePlayer.getWidth(),
				imagePlayer.getHeight());
		Iterator<Laser> laserEnemyIterator = lasersEnemy.iterator();
		while (laserEnemyIterator.hasNext()) {
			Laser laserEnemy = laserEnemyIterator.next();

			for (Enemy enemy : enemies) {
				if (enemy.getRectangle().intersects(playerRectangle)
						|| laserEnemy.getRectangle().intersects(playerRectangle)) {
					if (!playerInvulnerable) {
						sounds.soundTakeDamage();
						player.setPlayerHealth(player.getPlayerHealth() - 1);
						if (player.getPlayerHealth() == 0) {
							gameover = true;
						} else {
							playerInvulnerable = true;
							Timer noDamageTime = new Timer(2000, new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									playerInvulnerable = false;
								}
							});
							noDamageTime.setRepeats(false);
							noDamageTime.start();
						}
					}

				}
			}
		}
	}

	private void checkGameOver() {

		if (gameover) {

			lasers.clear();
			lasersEnemy.clear();
			enemies.clear();
			
			JLabel gameOverLabel = new JLabel("Game Over");
			gameOverLabel.setForeground(Color.red);
			gameOverLabel.setFont(new Font("Console", Font.BOLD, 50));
			gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
			setLayout(new BorderLayout());
			add(gameOverLabel, BorderLayout.CENTER);
			revalidate();
			repaint();
			sounds.soundGameOver();
			try {
				FileWriter myWriter = new FileWriter(file, true);
				myWriter.write(UserInterface.name + "\n" + score + "\n");
				myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			gameLoopThread.stop();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(imageBackGround, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawImage(imagePlayer, player.playerX, player.playerY, null);
		if (playerInvulnerable) {
			if (AnimationEnd == 1) {
				g.drawImage(imagePlayer, player.playerX, player.playerY, null);
			} else {
				g.drawImage(imagePlayer2, player.playerX, player.playerY, null);
			}
		}
		g.drawImage(imageHeart, 175, 5, null);
		g.drawString("X: " + String.valueOf(player.getPlayerHealth()), 200, 20);
		g.drawString("LEVEL" + levelChoice + "%" + level, 10, 20);
		if (level == 100) {
			JLabel WinLabel = new JLabel("YOU WİN");
			WinLabel.setForeground(Color.RED);
			WinLabel.setFont(new Font("Console", Font.BOLD, 50));
			WinLabel.setHorizontalAlignment(SwingConstants.CENTER);
			setLayout(new BorderLayout());
			add(WinLabel, BorderLayout.CENTER);
			revalidate();
			repaint();
			gameLoopThread.stop();

		}
		for (Laser laser : lasers) {
			laser.draw(g);
		}

		for (Laser laser : lasersEnemy) {
			laser.drawEnemyLaser(g);
		}

		for (Enemy enemy : enemies) {
			if (AnimationEnd == 1) {
				g.drawImage(imageEnemy1, enemy.getRectangle().x, enemy.getRectangle().y, enemy.getRectangle().width,
						enemy.getRectangle().height, null);
			} else {
				g.drawImage(imageEnemy2, enemy.getRectangle().x, enemy.getRectangle().y, enemy.getRectangle().width,
						enemy.getRectangle().height, null);
			}
		}

		g.drawString("" + score, 450, 20);

		frames++;
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - startTime;
		if (elapsedTime >= 1000) {
			fps = frames;
			frames = 0;
			startTime = currentTime;
		}

		g.setColor(Color.red);
		g.drawString(String.valueOf(fps + " FPS"), 800, 20);
	}
}
