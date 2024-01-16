package GameObject;
import java.awt.Rectangle;

public class Enemy extends GameObject{
	
	private Rectangle rectangle;
	public int enemyWidth = 50;
	public int enemyHeight = 50;
	public int enemySpeed = 1;
	public int enemyHealth = 30;
	 
	
	public Enemy(Rectangle rectangle, int enemyHealth, int enemySpeed) {
		super(0, 0, enemySpeed, 1);
		this.rectangle = rectangle;
		this.enemyHealth = enemyHealth;
	}

	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public void takeDamage() {
		enemyHealth -= 10;
	}
	
	public boolean isDestroyed() {
		return enemyHealth <= 0;
	}
	
}