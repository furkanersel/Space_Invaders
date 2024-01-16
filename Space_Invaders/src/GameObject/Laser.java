package GameObject;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Laser extends GameObject{
    
	private BufferedImage img;
    private int width;
    private int height;
   
    
    public Laser(BufferedImage img,int x, int y, int speed, int width, int height) {
    	super(x, y, speed, 0);
        this.img = img;
    	this.width = width;
        this.height = height;
        
    }
    
    public void movePlayer() {
       super.setPos_y(super.getPos_y() - super.getSpeed());
    }
    
    public void moveEnemy() {
        super.setPos_y(super.getPos_y() + super.getSpeed());
     }
    
    public int getX() {
        return super.getPos_x();
    }
    
    public int getY() {
    	return super.getPos_y();
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void draw(Graphics g) {
    	g.drawImage(img, getX(), getY(),null);
    }
    
    public void drawEnemyLaser(Graphics g) {
    	g.drawImage(img, getX(), (getY() - 10),null);
    }

	public Rectangle getRectangle() {
		return new Rectangle(getPos_x(), getPos_y(), width, height);
	}

}