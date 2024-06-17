import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.*;

public class EnemyProjectile {
	Image image = new ImageIcon("src/images/enemy_projectile01.png").getImage();
	
	private int x, y, speed = 13;
	int width = image.getWidth(null); 
	int height = image.getHeight(null);
	
	 public EnemyProjectile(int x, int y, int enemyNum) {
		 this.x = x; this.y = y;
	
			switch(enemyNum) {
			case 1:
				break;
			case 2:
				image = new ImageIcon("src/images/enemy_projectile02.png").getImage();
				speed = 9;
				break;
			case 3:
				image = new ImageIcon("src/images/enemy_projectile03.png").getImage();
				speed = 8;
				break;
			default:
				break;
			}
	 }
	  
	public void shot() {
		this.x -= speed;
	}
	
	public Rectangle getBounds() { return new Rectangle(x + 110, y + 110, 80, 80); }
	
	public Image getImage() { return this.image; }
	
	public int getX() { return this.x; }
	
	public int getY() { return this.y; }
	
	public void flush() {
		image.flush();
	}
}
