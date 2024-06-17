//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class ShootingGame extends JFrame {
//    private Image bufferImage;
//    private Graphics screenGraphic;
//
//    private Game game;
//
//    private Image gameScreen = new ImageIcon("src/images/background01.png").getImage();
//    Player player = new Player(100, Main.SCREEN_HEIGHT / 2, 150, 200 ,1 , 5 );
//
//    public ShootingGame(int playerNum , int lifeNum) {
//        setTitle("Shooting Game");
//        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
//        setResizable(false);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        player = new Player(100, Main.SCREEN_HEIGHT / 2, 200, 200, playerNum , lifeNum);
//        init();
//        
//        setVisible(true);
//        
//        
//    }
//
//    private void init() {
//        game = new Game(player);
//        GamePanel gamePanel = new GamePanel();
//        this.add(gamePanel);
//        gamePanel.setFocusable(true);
//        gamePanel.requestFocusInWindow();
//        gamePanel.addKeyListener(new KeyListener());
//    }
//
//    class KeyListener extends KeyAdapter {
//        public void keyPressed(KeyEvent e) {
//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_W:
//                    player.setUP(true);
//                    break;
//                case KeyEvent.VK_A:
//                    player.setLEFT(true);
//                    break;
//                case KeyEvent.VK_S:
//                    player.setDOWN(true);
//                    break;
//                case KeyEvent.VK_D:
//                    player.setRIGHT(true);
//                    break;
//                case KeyEvent.VK_SPACE:
//                    player.setSPACE(true);
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        public void keyReleased(KeyEvent e) {
//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_W:
//                    player.setUP(false);
//                    break;
//                case KeyEvent.VK_A:
//                    player.setLEFT(false);
//                    break;
//                case KeyEvent.VK_S:
//                    player.setDOWN(false);
//                    break;
//                case KeyEvent.VK_D:
//                    player.setRIGHT(false);
//                    break;
//                case KeyEvent.VK_SPACE:
//                    player.setSPACE(false);
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//
//    class GamePanel extends JPanel {
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            screenDraw(g);
//        }
//
//        public void screenDraw(Graphics g) {
//            g.drawImage(gameScreen, Process.scroll, 0, null);
//            game.gameDraw(g);
//            repaint();
//        }
//    }
//
//}


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShootingGame extends JFrame {
	private Image bufferImage;
	private Graphics screenGraphic;

    private Game game;
    
    private Image gameScreen = new ImageIcon("src/images/background01.png").getImage();
    
    Player player = new Player(100, Main.SCREEN_HEIGHT / 2, 150, 200 ,1 , 5);
    
    public ShootingGame(int playerNum , int lifeNum) {
      setTitle("Shooting Game");
      setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
      setResizable(false);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      player = new Player(100, Main.SCREEN_HEIGHT / 2, 200, 200, playerNum , lifeNum);
      init();
      
      setVisible(true);
      
      
  }

    private void init() {
        game = new Game(player);
        

        addKeyListener(new KeyListener());
    }

    class KeyListener extends KeyAdapter{
    	public void keyPressed(KeyEvent e) {
    		switch(e.getKeyCode()) {
    		case KeyEvent.VK_W:
    			player.setUP(true);
    			break;
    		case KeyEvent.VK_A:
    			player.setLEFT(true);
    			break;
    		case KeyEvent.VK_S:
    			player.setDOWN(true);
    			break;
    		case KeyEvent.VK_D:
    			player.setRIGHT(true);
    			break;
    		case KeyEvent.VK_SPACE:
    			player.setSPACE(true);
    			break;
    		default:
    			break;
    		}
    	}
    	
    	public void keyReleased(KeyEvent e) {
    		switch(e.getKeyCode()) {
    		case KeyEvent.VK_W:
    			player.setUP(false);
    			break;
    		case KeyEvent.VK_A:
    			player.setLEFT(false);
    			break;
    		case KeyEvent.VK_S:
    			player.setDOWN(false);
    			break;
    		case KeyEvent.VK_D:
    			player.setRIGHT(false);
    			break;
    		case KeyEvent.VK_SPACE:
    			player.setSPACE(false);
    			break;
    		case KeyEvent.VK_ESCAPE:
    			if (Process.isPause == false) Process.isPause = true;
    			else Process.isPause = false;
    		default:
    			break;
    		}
    	}
    }
    
    public void paint(Graphics g) {
    	bufferImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
    	screenGraphic = bufferImage.getGraphics();
    	screenDraw(screenGraphic);
    	g.drawImage(bufferImage, 0, 0, null);
    	}

    public void screenDraw(Graphics g) {
    	g.drawImage(gameScreen, Process.scroll, 0, null);
    	game.gameDraw(g);
    	this.repaint();
    }
    
  
}

