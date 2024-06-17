import javax.swing.*;
import java.awt.*;

public class BossProjectile {
	Image image = new ImageIcon("src/images/boss_projectile01.png").getImage();
	
	int x, y; int width = image.getWidth(null); int height = image.getHeight(null);
	
public BossProjectile(int x, int y, int bossNum) {
	this.x = x; this.y = y;
	if (bossNum == 2) image = new ImageIcon("src/images/boss_projectile01.png").getImage();
	}

public void shot() { this.x -= 12; }

public void flush() {
	image.flush();
	}

public Rectangle getBounds() { return new Rectangle(x + 110, y + 110, 80, 80);
	}
}

