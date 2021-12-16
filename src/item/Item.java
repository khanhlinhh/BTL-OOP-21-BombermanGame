package item;

import item.bombHandler.CheckBombExplosion;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item {
  public BufferedImage image;
  public String name;
  public boolean collision = false;
  public boolean isPowerUp = false;
  public int worldX, worldY;
  public boolean isDisappeared = false;
  public int spriteCounter = 0;
  public int spriteNum = 1;
  public CheckBombExplosion checkBombExplosion;

  public abstract void getImage();

  public abstract void update() throws InterruptedException;

  public abstract void draw(Graphics2D g2);
}
