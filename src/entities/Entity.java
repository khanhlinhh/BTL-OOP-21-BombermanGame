package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
  public static final int UP_DIRECTION = 1;
  public static final int DOWN_DIRECTION = 2;
  public static final int LEFT_DIRECTION = 3;
  public static final int RIGHT_DIRECTION = 4;
  public int worldX, worldY;
  public int speed;
  public BufferedImage up1,
      up2,
      up3, // Lấy sritesheet của vật.
      down1,
      down2,
      down3,
      left1,
      left2,
      left3,
      right1,
      right2,
      right3,
      dead1,
      dead2,
      dead3;
  public int direction; // Hướng đi (điều khiển, check va chạm)
  public int spriteCounter = 0;
  public int spriteNum = 1;
  public Rectangle solidArea; // phạm vi va chạm
  public boolean collisionOn = false;
  public boolean collisionDead = false;
  public boolean isDead = false;

  public abstract void getImage();

  public abstract void update();

  public abstract void draw(Graphics2D g2);
}
