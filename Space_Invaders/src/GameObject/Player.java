package GameObject;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends GameObject{
	
	protected BufferedImage imageplayer;
	private int damage;
	int playerHealth = 3;
	int playerDx = 0;
    int playerDy = 0;
	protected int playerX = 425;
	protected int playerY = 475;
	protected int playerSpeed = 5;
   
   
    public Player(BufferedImage imageplayer, int playerX, int playerY, int playerSpeed, int playerDx, int playerDy, int playerHealth, int damage) {
		super(playerX, playerY, playerSpeed, damage);
		this.imageplayer = imageplayer;
		this.playerDx = playerDx;
		this.playerDy = playerDy;
		this.playerHealth = playerHealth;
		this.setDamage(damage);
	}
	
	public void updatePlayerPosition() {
		playerX += playerDx;
		playerY += playerDy;
	}
	
	
	public int getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}
	
    
    public void draw(Graphics g) {
        g.drawImage(imageplayer, playerX, playerY, null);
    }
    
    public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}