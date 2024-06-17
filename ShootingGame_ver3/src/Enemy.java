import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy {
	private Image image = new ImageIcon("src/images/enemy01.png").getImage();
	
	int enemyNum = 1;
	private int x, y; int width = image.getWidth(null); int height = image.getHeight(null);
	private int health = 15, fullhealth = 15;
	
	public Enemy(int x, int y, int enemyNum) {
		this.x = x; this.y = y;
		this.enemyNum = enemyNum;
		
		
		switch(enemyNum) {
		case 1:
			break;
		case 2:
			image = new ImageIcon("src/images/enemy02.png").getImage();
			health = 20; fullhealth = 20;
			break;
		case 3:
			image = new ImageIcon("src/images/enemy03.png").getImage();
			health = 25; fullhealth = 25;
			break;
		default:
			break;
		}
	}
	
	public void flush() {
    	image.flush(); 
    }
	
	public int getEnemyNum() { return this.enemyNum; }
	
	public int getX() { return this.x; }
	
    public int getY() { return this.y; }
    
    public int getHealth() { return this.health; }
    
    public void setHealth(int damage) {
    	this.health -= damage;
    }
    
    public int getFHealth() { return this.fullhealth; }
    
    public Image getImage() { return this.image; }
    
    public Rectangle getBounds() {
        return new Rectangle(x + 125, y + 150, 250, 200);
    	
    }
	
	public void move() { this.x -= 5; }
}
