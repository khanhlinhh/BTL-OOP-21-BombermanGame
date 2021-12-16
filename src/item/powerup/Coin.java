package item.powerup;

import graphics.Sprite;
import item.Item;
import main.GamePanel;
import main.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Coin extends Item {
  private GamePanel gp;

  public Coin(GamePanel gp, int xUnit, int yUnit) {
    this.gp = gp;
    isPowerUp = true;
    worldX = xUnit * GamePanel.tileSize;
    worldY = yUnit * GamePanel.tileSize;
    getImage();
  }

  @Override
  public void getImage() {
    try {
      image = ImageIO.read(getClass().getResourceAsStream(Sprite.coin));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update() {
    gp.checkCollision.checkPowerUp(gp.bomber, this);
    if (collision) {
      Sound.playsound(Sound.earnCoin);
      int score = gp.bomber.getScore() + 10;
      gp.bomber.setScore(score);
      isDisappeared = true;
    }
  }

  @Override
  public void draw(Graphics2D g2) {
    g2.drawImage(image, worldX, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
  }
}
