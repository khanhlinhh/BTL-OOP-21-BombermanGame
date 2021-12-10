package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
  public int worldX, worldY;
  public int speed;

  public BufferedImage up1, up2, up3, // Lấy sritesheet của vật.
      down1, down2, down3,
      left1, left2, left3,
      right1, right2, right3;

  public final int LEFT_DIRECTION = 1;
  public final int RIGHT_DIRECTION = 2;
  public final int UP_DIRECTION = 3;
  public final int DOWN_DIRECTION = 4;

  public int direction ;
  public int spriteCounter = 0;
  public int spriteNum = 1;
  public Rectangle solidArea; // phạm vi va chạm
  public boolean collisionOn = false;
}
