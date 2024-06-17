import javax.swing.*;
import java.util.Iterator; 
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

public class Game extends JPanel implements ActionListener{
	
    private Player player;
    
    private ArrayList<Enemy> enemies = new ArrayList<>(); private Enemy newenemy;
    private ArrayList<EnemyProjectile> enemyShot = new ArrayList<>();
    private EnemyProjectile eShot; private ArrayList<Boss> bosses = new ArrayList<>();
    private BossProjectile bShot; private ArrayList<BossProjectile> bossShot = new ArrayList<>();
    
    private ArrayList<PlayerAttack> playerShot = new ArrayList<>();
    private PlayerAttack newAttack; 
    
    private ArrayList<Potion> items = new ArrayList<>(); private Potion newItem; 
    private ArrayList<Explosion> exs = new ArrayList<>(); private Explosion explode; 
    
    private Boss boss;
    
    public static int score;
    public static int life; 
    public final static int MAX_LIFE = 10;
    public static boolean gameOver;
    static boolean ismidTermBoss = false;
    private boolean midTermBossDefeated;
    private boolean finalTermBossDefeated; 
    
    private long startTime;
    private long finishTime; 
    
    private Image heartImage = new ImageIcon("src/images/heart01.png").getImage();

   public Game() {
	   
   }
    
    public Game(Player players) {
    	player = players;
        init();
    }
    
    public void setMidDefeated(boolean bool_) {
    	midTermBossDefeated = bool_;
    }
    
    public void setFinalDefeated(boolean bool_) {
    	finalTermBossDefeated = bool_;
    }
    
    public boolean getMidDefeated() {
    	return midTermBossDefeated;
    }
    
    
    public boolean getfinalDefeated() {
    	return finalTermBossDefeated;
    }
    
    private void init() {
        this.setFocusable(true);

        score = 0;
        
        gameOver = false;
        midTermBossDefeated = false;
        finalTermBossDefeated = false;
        startTime = System.currentTimeMillis();

        
        Runnable r = new Process(player, playerShot, enemies, enemyShot, exs, items, bosses, bossShot);
        new Thread(r).start();

    }

	@Override
	public void actionPerformed(ActionEvent e) {
	
		
	}
	
	public void gameDraw(Graphics g) {
		drawPlayer(g);
		drawHeart(g);
		drawObject(g);
		drawScore(g);
		}

	public void drawScore(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("SCORE: " + score, 50, Main.SCREEN_HEIGHT - 40);
	}
	
	public void drawPlayer(Graphics g) {
		g.drawImage(player.getImage(), player.getX(), player.getY(), null);
		if (playerShot != null) {
			for (int i = 0; i < playerShot.size(); i++) {
				newAttack = playerShot.get(i);
				g.drawImage(newAttack.image, newAttack.getX(), newAttack.getY(), null);
				}
			}
		}

	public void drawHeart(Graphics g) {
		for(int i = 0; i < life; i ++) {
			g.drawImage(heartImage, i*100 + 20, 30, null);
		}
	}
	
	public void drawObject(Graphics g) { 
		for(int i = 0; i < enemies.size(); i++) {
			newenemy = enemies.get(i);
			g.drawImage(newenemy.getImage(), newenemy.getX(), newenemy.getY(), null);
			g.setColor(Color.BLACK);
			g.fillRect(newenemy.getX() + 130, newenemy.getY() + 70, newenemy.getFHealth() * 10, 30);
			if (newenemy.getFHealth() / 2 < newenemy.getHealth()) {
                g.setColor(Color.GREEN);
                g.fillRect(newenemy.getX() + 130, newenemy.getY() + 70, newenemy.getHealth() * 10, 30);
            } else {
                g.setColor(Color.RED);
                g.fillRect(newenemy.getX() + 130, newenemy.getY() + 70, newenemy.getHealth() * 10, 30);
            }
		}
		
		
		for(int i = 0; i < enemyShot.size(); i++) {
			eShot = enemyShot.get(i);
			g.drawImage(eShot.getImage(), eShot.getX(), eShot.getY(), null);
		}
		
		for(int i = 0; i < bossShot.size(); i ++) {
			bShot = bossShot.get(i);
			g.drawImage(bShot.image, bShot.x, bShot.y, null);
		}
		
		for(int i = 0; i < exs.size(); i++) {
			explode = exs.get(i);
			g.drawImage(explode.getImage(), explode.getX(), explode.getY(), null);
		}
		
		for(int i = 0; i < items.size(); i++) {
			newItem = items.get(i);
			g.drawImage(newItem.getImage(), newItem.getX(), newItem.getY(), null);
		}
		for(int i = 0; i < bosses.size();i++) {
			boss = bosses.get(i);
			g.drawImage(boss.getBoss(), boss.getX(), boss.getY(), null);
			g.setColor(Color.BLACK);
			g.fillRect(boss.getX() + 130, boss.getY() + 40, boss.getFHealth() * 1, 30);
			g.setColor(Color.GREEN);
			g.fillRect(boss.getX() + 130, boss.getY() + 40, boss.getHealth() * 1, 30);
		}
	}

}
