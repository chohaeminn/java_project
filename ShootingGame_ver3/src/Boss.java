import javax.swing.*;
import java.awt.*;

public class Boss {
	private Image image = new ImageIcon("src/images/boss01.png").getImage();
	
	int x, y; 
	int width = image.getWidth(null); 
	int height = image.getHeight(null);
	int health = 350; 
	final int fullhealth = 350; 
	private int bossNum = 1;
	private String type = "Mid";
	int moveCount = 0;
 
 public Boss(int x, int y, int bossNum) {
	 this.x = x; this.y = y;
	 if (bossNum == 2) {
		 image = new ImageIcon("src/images/boss01_open.png").getImage();
		 this.type = "Final";
		 this.bossNum = bossNum;
	 	}
	 }
 
 public String getType() {
	 return this.type;
 }
 
 public int getBossNum() { return this.bossNum; }
 
 public Image getBoss() { return this.image; }
 
 public int getX() { return this.x; }
	
 public int getY() { return this.y; }
 
 public int getHealth() { return this.health; }
 
 public void setHealth(int damage) {
 	this.health -= damage;
 }
 
 public int getFHealth() { return this.fullhealth; }
 
 public void Xmove() { this.x -= 5; }
 
 public void Ymove() {
	 if (this.moveCount <= 30) {
		 this.y -= 3; moveCount += 1;
	 }else if (this.moveCount > 30) {
		 this.y += 3; moveCount += 1;
		 if (this.moveCount > 60) this.moveCount = 0;
	 }
 }
 
 public Rectangle getBounds() {
     return new Rectangle(x+250, y+100, 400, 500);
 
 }

}