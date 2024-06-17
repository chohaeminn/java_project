import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;

public class Process implements Runnable {
    private Player player;
    private ArrayList<PlayerAttack> playerShot; 
    private PlayerAttack newAttack;
    private ArrayList<Enemy> enemies; 
    private Enemy newenemy;
    private ArrayList<EnemyProjectile> enemyShot; 
    private EnemyProjectile newEShot;
    private PlayerAttack colAttack; 
    private Enemy colEnemy;
    private ArrayList<Explosion> exs; 
    private Explosion explode;
    
    private ArrayList<Boss> bosses; 
    private Boss boss = null;
    private ArrayList<BossProjectile> bossShot; 
    private BossProjectile newBShot;
    private int counter = 0, effectCounter = 0, bossCounter = 0;
    private ArrayList<Potion> items; 
    private Potion item;
    static int plus = 0;
    static boolean isPause = false;
    
    

    private Audio backgroundMusic;
    
    private Audio attackSound;
    private Audio itemSound;
    private Audio hitSound;
    private Audio hitPlayerSound;
    
    public int spawnRate = 200;
    public static int scroll = 0;
    
 
    Game game = new Game();
    
    public Process(Player playerSet, ArrayList<PlayerAttack> ps, ArrayList<Enemy> en, ArrayList<EnemyProjectile> ep,
                   ArrayList<Explosion> ex, ArrayList<Potion> i, ArrayList<Boss> b, ArrayList<BossProjectile> bp) {
        player = playerSet;
        playerShot = ps;
        enemies = en;
        enemyShot = ep;
        exs = ex;
        items = i;
        bosses = b;
        bossShot = bp;
        
        
        backgroundMusic = new Audio("src/audio/SellBuyMusic-밝은-하우스-브금.wav", true); 
        
        attackSound = new Audio("src/audio/Laser-gun-7-short.wav", false);
        itemSound = new Audio("src/audio/Coin-1.wav", false);
        hitSound = new Audio("src/audio/Correct-1.wav", false);
        hitPlayerSound = new Audio("src/audio/Slap-Sound.wav", false);
    }
    
    @Override
    public void run() {
    	
    	backgroundMusic.start();
    	
        while (!Game.gameOver) {
            try {
                Thread.sleep(10);
                if (isPause) {
                	continue;
                	
             
                } else {
                    if (counter > 10000) counter = 0;
                    else {
                        counter++;
                        effectCounter++;
                    }
                    bossCounter++;
                    keyProcess();
                    enemySpawnProcess();
                    enemyShotProcess();
                    movingProcess();
                    removingProcess();
                    checkCollisions();
                    explodeProcess();
                    backGroundProcess();
                    itemProcess();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        backgroundMusic.stop();
    }
    
    private void keyProcess() {
        int tempX = player.getX();
        int tempY = player.getY();
        if (player.getUP() && tempY > -150) player.setY(tempY - player.getSpeed());
        if (player.getDOWN() && tempY < Main.SCREEN_HEIGHT - 300) player.setY(tempY + player.getSpeed());
        if (player.getLEFT() && tempX > -200) player.setX(tempX - player.getSpeed());
        if (player.getRIGHT() && tempX < Main.SCREEN_HEIGHT + 300) player.setX(tempX + player.getSpeed());
        if (player.getSPACE() && counter % 20 == 0) {
            newAttack = new PlayerAttack(player.getX() + 200, player.getY() + 230, plus);
            playerShot.add(newAttack);
            
            attackSound.start();
        }
        
        
    }
    
    private void movingProcess() {
        if (playerShot != null) {
            for (PlayerAttack newshot : playerShot) {
                newshot.fire();
            }
        }
        if (enemies != null) {
            for (Enemy newenemy : enemies) {
                newenemy.move();
            }
        }
        if (enemyShot != null) {
            for (EnemyProjectile shots : enemyShot) {
                shots.shot();
            }
        }
        if (items != null) {
            for (Potion newItem : items) {
                newItem.move();
            }
        }
        if (bossShot != null) {
            for (BossProjectile shot : bossShot) {
                shot.shot();
            }
        }
        if (bosses != null) {
            for (Boss boss : bosses) {
                if (boss.getX() >= 1400) {
                    boss.Xmove();
                } else {
                    boss.Ymove();
                }
            }
        }
    }
    
    private void removingProcess() {
        if (playerShot != null) {
            for (int i = 0; i < playerShot.size(); i++) {
                newAttack = playerShot.get(i);
                if (newAttack.getX() > Main.SCREEN_HEIGHT + 700) {
                    playerShot.get(i).flush();
                    playerShot.remove(i);
                }
            }
        }
        if (enemies != null) {
            for (int i = 0; i < enemies.size(); i++) {
                newenemy = enemies.get(i);
                if (newenemy.getX() < -500) {
                    enemies.get(i).flush();
                    enemies.remove(i);
                }
            }
        }
        if (enemyShot != null) {
            for (int i = 0; i < enemyShot.size(); i++) {
                newEShot = enemyShot.get(i);
                if (newEShot.getX() < -500) {
                    enemyShot.get(i).flush();
                    enemyShot.remove(i);
                }
            }
        }
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                item = items.get(i);
                if (item.getX() < -500) {
                    items.get(i).flush();
                    items.remove(i);
                }
            }
        }
        if (bossShot != null) {
            for (int i = 0; i < bossShot.size(); i++) {
                newBShot = bossShot.get(i);
                if (newBShot.x < -500) {
                    bossShot.get(i).flush();
                    bossShot.remove(i);
                }
            }
        }
    }
    
  
    private void enemySpawnProcess() {
        if (counter % spawnRate == 0 && counter != 0) {
            if (enemies.size() < 10) {
                newenemy = new Enemy(Main.SCREEN_WIDTH, new Random().nextInt(800) - 100, new Random().nextInt() % 3 + 1);
                enemies.add(newenemy);
            }
        }
        
       
        if (!game.getMidDefeated()) {
            if (boss == null && bossCounter == 100 * 5) {
                System.out.println("Mid-term boss spawn");
                boss = new Boss(Main.SCREEN_WIDTH + 100, Main.SCREEN_HEIGHT / 2 - 300, 1);
                bosses.add(boss);
            }
        }
        
      
        if (game.getMidDefeated() && !game.getfinalDefeated()) {
            if (boss == null && 99 == bossCounter % 100) {
                System.out.println("Final boss spawn");
                boss = new Boss(Main.SCREEN_WIDTH + 100, Main.SCREEN_HEIGHT / 2 - 300, 2);
                bosses.add(boss);
            }
        }
    }
    
    private void itemProcess() {
        if (counter % 500 == 0 && counter != 0) {
            if (items.size() < 10) {
                switch (new Random().nextInt(2)) {
                    case 0:
                        item = new Potion(Main.SCREEN_WIDTH, new Random().nextInt(800) - 100, "potion");
                        break;
                    case 1:
                        item = new Potion(Main.SCREEN_WIDTH, new Random().nextInt(800) - 100, "gpt");
                        break;
                    default:
                        item = new Potion(Main.SCREEN_WIDTH, new Random().nextInt(800) - 100, "potion");
                        break;
                }
                items.add(item);
            }
        }
    }
    
    private void enemyShotProcess() {
        Random random = new Random();
        
        for (Enemy newenemy : enemies) {
            if (counter % 70 == 0) {
                newEShot = new EnemyProjectile(newenemy.getX(), newenemy.getY() + 50, newenemy.getEnemyNum());
                enemyShot.add(newEShot);
            }
        }

        for (Boss newboss : bosses) {
            if (counter % 70 == 0) {
                
                int xOffset = random.nextInt(100) - 50;  
                int yOffset = random.nextInt(100) - 50; 

                int projectileX = newboss.getX() + xOffset;
                int projectileY = newboss.getY() + yOffset;

               
                newBShot = new BossProjectile(projectileX, projectileY, newboss.getBossNum());
                bossShot.add(newBShot);
            }
        }
    }

    private void checkCollisions() {
        for (int i = 0; i < enemies.size(); i++) {
            if (player.getBounds().intersects(enemies.get(i).getBounds())) {
            	hitPlayerSound.start();
                enemies.remove(i);
                Game.life--;
                if (Game.life <= 0) {
                    System.out.println("Game over");
                    Game.gameOver = true;
                    
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new GameOver(Game.score, game.getMidDefeated(), game.getfinalDefeated()).setVisible(true);
                        }
                    });
                }
            }
        }
        
        for (int i = 0; i < enemyShot.size(); i++) {
            if (player.getBounds().intersects(enemyShot.get(i).getBounds())) {
            	hitPlayerSound.start();
                enemyShot.remove(i);
                Game.life--;
                if (Game.life <= 0) {
                    System.out.println("Game over");
                    Game.gameOver = true;
                    
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new GameOver(Game.score, game.getMidDefeated(), game.getfinalDefeated()).setVisible(true);
                        }
                    });
                }
            }
        }
        
        for (int i = 0; i < bossShot.size(); i++) {
            if (player.getBounds().intersects(bossShot.get(i).getBounds())) {
            	hitPlayerSound.start();
                bossShot.remove(i);
                Game.life--;
                if (Game.life <= 0) {
                    System.out.println("Game over");
                    Game.gameOver = true;
                    
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new GameOver(Game.score, game.getMidDefeated(), game.getfinalDefeated()).setVisible(true);
                        }
                    });
                }
            }
        }
        
        if (boss != null && !bosses.isEmpty()) {
            for (int i = 0; i < playerShot.size(); i++) {
                colAttack = playerShot.get(i);
                if (colAttack.getBounds().intersects(boss.getBounds())) {
                    boss.setHealth(colAttack.getPower() + colAttack.getPlus());
                    explode = new Explosion(colAttack.getX(), colAttack.getY());
                    exs.add(explode);
                    playerShot.remove(i);
                    
                    if (boss.getHealth() <= 0) {
                        if (boss.getBossNum() == 1) {
                            game.setMidDefeated(true);  
                        } else if (boss.getBossNum() == 2) {
                            game.setFinalDefeated(true);
                        }
                        hitSound.start();
                        bosses.clear();
                        boss = null;
                    }
                }
            }
        }
        
        for (int i = 0; i < playerShot.size(); i++) {
            colAttack = playerShot.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                colEnemy = enemies.get(j);
                if (colAttack.getBounds().intersects(colEnemy.getBounds())) {
                    colEnemy.setHealth(colAttack.getPower() + colAttack.getPlus());
                    explode = new Explosion(colAttack.getX(), colAttack.getY());
                    exs.add(explode);
                    playerShot.remove(i);
                    
                    if (colEnemy.getHealth() <= 0) {
                        switch (colEnemy.getEnemyNum()) {
                            case 2:
                                Game.score += 2000;
                                break;
                            case 3:
                                Game.score += 3000;
                                break;
                            default:
                                Game.score += 1000;
                                break;
                        }
                        hitSound.start();
                        enemies.remove(j);
                    }
                }
            }
        }
        
        for (int i = 0; i < items.size(); i++) {
            if (player.getBounds().intersects(items.get(i).getBounds())) {
                items.get(i).getEffect();
                items.remove(i);
                itemSound.start();
            }
        }
    }
    
    private void explodeProcess() {
        if (!exs.isEmpty()) {
            for (int i = 0; i < exs.size(); i++) {
                if (effectCounter % 3 == 0) {
                    explode = exs.get(i);
                    if (explode.getCnt() >= 6) {
                        exs.get(i).getImage().flush();
                        exs.remove(i);
                    } else {
                        explode.animation();
                    }
                }
            }
        }
    }
    
    private void backGroundProcess() {
        scroll -= 3;
        if (scroll <= -Main.SCREEN_WIDTH) scroll = 0;
    }
}
