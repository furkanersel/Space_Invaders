package GameObject;

public class GameObject {
	
	private int pos_x;
	private int pos_y;
	private int speed;
	private int damage;
	
	public GameObject(int pos_x, int pos_y, int speed, int damage) {
		super();
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.speed = speed;
		this.setDamage(damage);
	}

	public int getPos_x() {
		return pos_x;
	}

	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}

	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}