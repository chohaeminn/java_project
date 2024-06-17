

import javax.swing.*;
import java.awt.*;

public class GameOver extends JFrame {
    public GameOver(int score, boolean midTermBossDefeated, boolean finalTermBossDefeated) {
        setTitle("Game Over");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new BackgroundPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

//        JLabel overStr = new JLabel("Game Over");
//        overStr.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
//        overStr.setForeground(Color.BLACK);  
//        overStr.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        scoreLabel.setForeground(Color.BLACK);  
//        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        scoreLabel.setAlignmentY(600);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 이미지를 저장할 JLabel
        JLabel gradeImageLabel = new JLabel();
        gradeImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 점수에 따른 이미지 설정
        String gradeImagePath = getGradeImagePath(score, midTermBossDefeated, finalTermBossDefeated);
        if (gradeImagePath != null) {
            ImageIcon gradeImageIcon = new ImageIcon(gradeImagePath);
            gradeImageLabel.setIcon(gradeImageIcon);
        } else {
            gradeImageLabel.setText("No Image Available");
            gradeImageLabel.setForeground(Color.WHITE);
            gradeImageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        }

        panel.add(Box.createVerticalGlue());
        //panel.add(overStr);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(Box.createVerticalGlue());
        panel.add(scoreLabel);
        panel.add(Box.createVerticalGlue());
        panel.add(gradeImageLabel);
        panel.add(Box.createVerticalGlue());
        add(panel);

        setVisible(true);
    }

    private String getGradeImagePath(int score, boolean midTermBossDefeated, boolean finalTermBossDefeated) {
        if (!midTermBossDefeated) {
            return "src/images/leave_of_absence.png"; // 중도휴학 이미지 경로
        } else if (!finalTermBossDefeated) {
            return "src/images/f_n.png";
        } else if (score > 20000) {
            return "src/images/a+_n.png";
        } else if (score > 13000) {
            return "src/images/a_n.png";
        } else if (score > 9000) {
            return "src/images/b_n.png";
        } else if (score > 5000) {
            return "src/images/c_n.png";
        } else if (score > 2000) {
            return "src/images/d_n.png";
        } else {
            return "src/images/f_n.png";
        }
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon("src/images/grade.png").getImage();
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


