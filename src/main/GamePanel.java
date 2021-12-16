package main;

import entities.Bomber;
import graphics.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
  // Screen settings
  public static final int originalTileSize = 16;
  public static final int scale = 3;
  public static final int tileSize = originalTileSize * scale;
  public final int maxScreenCol = 31;
  public final int maxScreenRow = 13;
  public final int screenWidth = tileSize * maxScreenCol;
  public final int screenHeight = tileSize * maxScreenRow;

  final int FPS = 60;
  public CheckCollision checkCollision = new CheckCollision(this);
  public TileManager tileManager = new TileManager(this);
  public AssetSetter assetSetter = new AssetSetter(this);
  KeyHandler keyHandler = new KeyHandler();
  public Bomber bomber = new Bomber(this, keyHandler);
  Thread gameThread;

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyHandler);
    this.setFocusable(true);
  }

  public void setUpGame() {
    assetSetter.setObject();
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    double drawInterval = 1000000000 / FPS;
    double nextDrawTime = System.nanoTime() + drawInterval;
    while (gameThread != null) {
      try {
        update();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      repaint();
      try {
        double remainingTime = nextDrawTime - System.nanoTime();
        remainingTime /= 1000000;
        if (remainingTime < 0) {
          remainingTime = 0;
        }
        Thread.sleep((long) remainingTime);
        nextDrawTime += drawInterval;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void update() throws InterruptedException {
    bomber.update();
    assetSetter.update();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    tileManager.draw(g2);
    assetSetter.draw(g2);
    bomber.draw(g2);
    g2.dispose();
  }
}
