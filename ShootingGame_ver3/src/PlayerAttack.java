

import javax.swing.*;
import java.awt.*;

public class PlayerAttack {
	
    Image image = new ImageIcon("src/images/player_projectile01.png").getImage();
    private int x, y;
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    private int plusPower = 0;
    private int attackPower = 0; 
    static int playerNum; 
   
    
    public PlayerAttack(int x, int y, int plus) {
        this.x = x;
        this.y = y;
        plusPower = plus;
       
        if (playerNum == 2) {
        	attackPower = 5;
        	
            image = new ImageIcon("src/images/player_projectile02.png").getImage();
        } else if (playerNum == 3) {
        	attackPower = 7;
        	
            image = new ImageIcon("src/images/player_projectile03.png").getImage();
        } else attackPower = 10;
    }

    public int getX() { return this.x; }
    
    public int getY() { return this.y; }
    
    public void flush() {
    	image.flush();
    }
    
    public int getPower() { return attackPower; }
    
    public int getPlus() { return plusPower; }
    
    public void fire() {
        this.x += 30; 
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

