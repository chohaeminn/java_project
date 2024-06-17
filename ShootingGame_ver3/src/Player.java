import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Rectangle;

public class Player {

    private long gameTime; 

    private int x, y; 
    private int width, height; 
    private int speed = 10;
   
    
    private Image image;
    private boolean UP = false, DOWN = false, LEFT = false, RIGHT = false, SPACE = false;

    public Player(int x, int y, int width, int height, int playerNum , int lifeNum) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        Game.life = lifeNum;
        switch(playerNum) {
            case 1:
                image = new ImageIcon("src/images/player01.png").getImage();
                break;
            case 2:
                image = new ImageIcon("src/images/player02.png").getImage();
                break;
            case 3:
                image = new ImageIcon("src/images/player03.png").getImage();
                break;
        }
    }

    public Image getImage() {
    	return this.image;
    }
    
    public Rectangle getBounds() { 
        return new Rectangle(x + 175, y + 150, 150, 200);
        
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

   
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

   
    public int getSpeed() {
    	return this.speed;
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
    	this.x = x;
    }

    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
    	this.y = y;
    }

  
    public int getWidth() {
        return width;
    }

   
    public int getHeight() {
        return height;
    }
    
    public void setUP(boolean isUP) {
    	this.UP = isUP;
    }
    public boolean getUP() { return this.UP; }
    
    public void setDOWN(boolean isDOWN) {
    	this.DOWN = isDOWN;
    }
    public boolean getDOWN() { return this.DOWN; }
    
    public void setLEFT(boolean isLEFT) {
    	this.LEFT = isLEFT;
    }
    public boolean getLEFT() { return this.LEFT; }

    public void setRIGHT(boolean isRIGHT) {
    	this.RIGHT = isRIGHT;
    }
    public boolean getRIGHT() { return this.RIGHT; }
    
    public void setSPACE(boolean isSPACE) {
    	this.SPACE = isSPACE;
    }
    public boolean getSPACE() { return this.SPACE; }
    
    public void updateGameTime(long time) {
        gameTime = time;
    }

   
   
    
    public void displayInfo() {
        System.out.println("Player Info - " + ", Position: (" + x + ", " + y + "), Game Time: " + gameTime + "ms");
    }
}





