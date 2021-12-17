package graphics;

import entities.Bomber;
import main.GamePanel;
import main.Sound;

import javax.swing.*;
import java.awt.*;

public class EndGame {
    private Bomber bomber;
    private GamePanel gp;

    public EndGame(GamePanel gp, Bomber bomber) {
        this.bomber = bomber;
        this.gp = gp;
    }

    public void GameOverRender() {
        if (bomber.isDead) {
            Sound.playsound(Sound.bomber_Die);
            Sound.playsound(Sound.gameOver);
            gp.gameThread = null;
            JFrame windowGameOver = new JFrame();
            Box vBox = Box.createVerticalBox();
            JPanel gameOverPanel = new JPanel();
            gameOverPanel.setBackground(Color.WHITE);
            JLabel gameOverLabel = new JLabel();
            gameOverLabel.setText("You Lose!");
            JLabel scoreLabel = new JLabel();
            scoreLabel.setText("Your Score: " + gp.bomber.getScore());
            JLabel highScoreLabel = new JLabel();
            highScoreLabel.setText("High score: " + gp.bomber.getHighScore());
            gameOverPanel.add(vBox);
            vBox.add(gameOverLabel);
            vBox.add(scoreLabel);
            vBox.add(highScoreLabel);
            windowGameOver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            windowGameOver.setResizable(false);
            windowGameOver.setTitle("Game Over!");
            gameOverPanel.setPreferredSize(new Dimension(GamePanel.tileSize * 6, GamePanel.tileSize + 10));
            gameOverPanel.setFocusable(true);
            windowGameOver.add(gameOverPanel);
            windowGameOver.pack();
            windowGameOver.setLocationRelativeTo(null);
            windowGameOver.setVisible(true);
        }
    }

    public void GameVictoryRender() {
        if (bomber.isVictory) {
            Sound.playsound(Sound.victory);
            gp.gameThread = null;
            JFrame windowGameOver = new JFrame();
            Box vBox = Box.createVerticalBox();
            JPanel gameVictoryPanel = new JPanel();
            gameVictoryPanel.setBackground(Color.WHITE);
            JLabel gameVictoryLabel = new JLabel();
            gameVictoryLabel.setText("Congratulations! You win!");
            JLabel scoreLabel = new JLabel();
            scoreLabel.setText("Your Score: " + gp.bomber.getScore());
            JLabel highScoreLabel = new JLabel();
            highScoreLabel.setText("High score: " + gp.bomber.getHighScore());
            gameVictoryPanel.add(vBox);
            vBox.add(gameVictoryLabel);
            vBox.add(scoreLabel);
            vBox.add(highScoreLabel);
            windowGameOver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            windowGameOver.setResizable(false);
            windowGameOver.setTitle("Victory!");
            gameVictoryPanel.setPreferredSize(new Dimension(GamePanel.tileSize * 6, GamePanel.tileSize + 10));
            gameVictoryPanel.setFocusable(true);
            windowGameOver.add(gameVictoryPanel);
            windowGameOver.pack();
            windowGameOver.setLocationRelativeTo(null);
            windowGameOver.setVisible(true);
        }
    }
}
