
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerChoose extends JFrame {
    
    private static final int PLAYER_IMAGE_WIDTH = 550; 
    private static final int PLAYER_IMAGE_HEIGHT = 700; 

    private int selectedDifficulty = 1;
    private int selectedPlayer = 0; 

    private Audio chooseSound;
   
    public PlayerChoose() {
        setTitle("PlayerChoose");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        JPanel panel = new BackgroundPanel();
        panel.setLayout(null);
        
        chooseSound = new Audio("src/audio/Pling-Sound.wav", false);
        
//        JLabel lbl_diff = new JLabel("Choose Difficulty: ");
//        lbl_diff.setBounds(Main.SCREEN_WIDTH / 2 - 130, 50, 300, 50);
//        lbl_diff.setHorizontalAlignment(JLabel.CENTER);
//        lbl_diff.setForeground(Color.WHITE);
//        lbl_diff.setFont(new Font("Arial", Font.BOLD, 24));
//        panel.add(lbl_diff);
        
        JButton easyButton = createDifficultyButton("EASY", 250, 50, 1);
        JButton normalButton = createDifficultyButton("NORMAL", 750, 50, 2);
        JButton difficultButton = createDifficultyButton("DIFFICULT", 1250, 50, 3);

        panel.add(easyButton);
        panel.add(normalButton);
        panel.add(difficultButton);
        
        
//        JLabel lbl = new JLabel("Choose Player:");
//        lbl.setBounds(Main.SCREEN_WIDTH / 2 - 100, 200, 200, 50);
//        lbl.setHorizontalAlignment(JLabel.CENTER);
//        lbl.setForeground(Color.WHITE);
//        lbl.setFont(new Font("Arial", Font.BOLD, 24));
//        panel.add(lbl);

        JLabel player1Label = createPlayerLabel("src/images/charachoose_01.png", 130, 1);
        JLabel player2Label = createPlayerLabel("src/images/charachoose_02.png", 670, 2);
        JLabel player3Label = createPlayerLabel("src/images/charachoose_03.png", 1210, 3);

        panel.add(player1Label);
        panel.add(player2Label);
        panel.add(player3Label);

        JButton startButton = new JButton("START");
        startButton.setBounds(Main.SCREEN_WIDTH / 2 - 150, 910, 300, 75);
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(startButton);

        add(panel);
        setVisible(true);

        player1Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayerAttack.playerNum = 1;
                selectedPlayer = 1;
                chooseSound.start();
                repaint();
            }
        });

        player2Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayerAttack.playerNum = 2;
                selectedPlayer = 2;
                chooseSound.start();
                repaint();
            }
        });

        player3Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayerAttack.playerNum = 3;
                selectedPlayer = 3;
                chooseSound.start();
                repaint();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (selectedDifficulty) {
                    case 1:
                        Game.life = 8;   
                        break;
                    case 2:
                        Game.life = 5;
                        break;
                    case 3:
                        Game.life = 3;
                        break;
                }
                new ShootingGame(PlayerAttack.playerNum, Game.life);
                setVisible(false);
            }
        });
    }

    private JLabel createPlayerLabel(String imagePath, int x, int playerNum) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(PLAYER_IMAGE_WIDTH, PLAYER_IMAGE_HEIGHT, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaledImage)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (selectedPlayer == playerNum) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(Color.black);
                    g2.setStroke(new BasicStroke(5)); 
                    g2.drawRoundRect(0, 10, getWidth() - 1, getHeight() - 1, 25, 25);
                    g2.drawRoundRect(1, 11, getWidth() - 3, getHeight() - 3, 25, 25);
                }
            }
        };
        label.setBounds(x, 175, PLAYER_IMAGE_WIDTH, PLAYER_IMAGE_HEIGHT);
        return label;
    }


    private JButton createDifficultyButton(String text, int x, int y, int difficulty) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 400, 100);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDifficulty = difficulty;
            }
        });
        return button;
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon("src/images/charachoose_bg.png").getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
