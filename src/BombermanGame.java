import main.GamePanel;
import main.Sound;

import javax.swing.*;

public class BombermanGame {
  public static void main(String[] args) {
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("Bomberman Game");

    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);

    window.pack();

    window.setLocationRelativeTo(null); // Cửa sổ mở ở giữa màn hình.
    window.setVisible(true);

    gamePanel.setUpGame();
    gamePanel.startGameThread();
//    Sound.playBackGround();
  }
}
