import javax.swing.*;
import java.awt.*;

public class Potion {
	Image image = new ImageIcon("src/images/potion.png").getImage();
	
	int x, y; 
	int width = image.getWidth(null); int height = image.getHeight(null);
	String type = null;
	
	public Potion(int x, int y, String type) {
		this.x = x; 
		this.y = y; 
		this.type = type;
		
		switch(type) {
			case "gpt":
				image = new ImageIcon("src/images/gpt.png").getImage();
				break;
			default:
				break;
			}
		}
 
	public void getEffect() {
		switch(type) {
		case "potion":
			if (Game.life < Game.MAX_LIFE ) Game.life += 1;
			break;
		case "gpt":
			Process.plus += 5;
			break;
		}
	}
 
	public int getX() { return this.x; }
	
    public int getY() { return this.y; }
    
    public Image getImage() { return this.image; }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    	
    }
	
	public void move() { this.x -= 7; }
	
	public void flush() {
    	image.flush();
    }
 
}
