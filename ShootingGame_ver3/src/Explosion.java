import java.awt.Image;

import javax.swing.ImageIcon;

public class Explosion {
	private Image image = new ImageIcon("src/images/explode01.png").getImage();
	private int x, y;
	private int imageCnt;
	
	Explosion(int x, int y){
		this.x = x;
		this.y = y;
		imageCnt = 1;
	}
	
	public void animation() {
		imageCnt += 1;
		image = new ImageIcon("src/images/explode0" + imageCnt + ".png").getImage();
	}
	
	public int getCnt() { return this.imageCnt; }
	
	public int getX() { return this.x; }
	
    public int getY() { return this.y; }
    
    public Image getImage() { return this.image; }
    
    public void flush() {
    	image.flush();
    }
}
