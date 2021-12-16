package item.bombHandler;

import graphics.Sprite;
import item.Item;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Flame extends Item {
  private BufferedImage exploded,
      exploded1,
      exploded2,
      horizontal,
      horizontal1,
      horizontal2,
      horizontal_left,
      horizontal_left1,
      horizontal_left2,
      horizontal_right,
      horizontal_right1,
      horizontal_right2,
      vertical,
      vertical1,
      vertical2,
      vertical_down,
      vertical_down1,
      vertical_down2,
      vertical_top,
      vertical_top1,
      vertical_top2;
  private Bomb bomb;
  private int flameSize;
  private int spriteRepeat;

  public Flame(Bomb bomb) {
    this.bomb = bomb;
    worldX = bomb.worldX;
    worldY = bomb.worldY;
    flameSize = bomb.bomber.getFlameSize();
    spriteRepeat = 0;
    isDisappeared = false;
    getImage();
  }

  @Override
  public void getImage() {
    try {
      exploded = ImageIO.read(getClass().getResourceAsStream(Sprite.exploded));
      exploded1 = ImageIO.read(getClass().getResourceAsStream(Sprite.exploded1));
      exploded2 = ImageIO.read(getClass().getResourceAsStream(Sprite.exploded2));
      horizontal = ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_horizontal));
      horizontal1 = ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_horizontal1));
      horizontal2 = ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_horizontal2));
      horizontal_left =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_horizontal_left_last));
      horizontal_left1 =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_horizontal_left_last1));
      horizontal_left2 =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_horizontal_left_last2));
      horizontal_right =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_horizontal_right_last));
      horizontal_right1 =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_horizontal_right_last1));
      horizontal_right2 =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_horizontal_right_last2));
      vertical = ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_vertical));
      vertical1 = ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_vertical1));
      vertical2 = ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_vertical2));
      vertical_down =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_vertical_down_last));
      vertical_down1 =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_vertical_down_last1));
      vertical_down2 =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_vertical_down_last2));
      vertical_top =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_vertical_top_last));
      vertical_top1 =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_vertical_top_last1));
      vertical_top2 =
          ImageIO.read(getClass().getResourceAsStream(Sprite.explosion_vertical_top_last2));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update() {
    spriteCounter++;
    if (spriteCounter > 12) {
      if (spriteRepeat == 1) {
        if (spriteNum == 1) {
          spriteRepeat++;
        } else if (spriteNum == 2) {
          spriteNum = 1;
        } else if (spriteNum == 3) {
          spriteNum = 2;
        }
      } else if (spriteRepeat == 2) {
        isDisappeared = true;
      } else if (spriteRepeat == 0) {
        if (spriteNum == 1) {
          spriteNum = 2;
        } else if (spriteNum == 2) {
          spriteNum = 3;
        } else if (spriteNum == 3) {
          spriteRepeat++;
          spriteNum = 1;
        }
      }
      spriteCounter = 0;
    }
  }

  @Override
  public void draw(Graphics2D g2) {
    int up = bomb.flameArea[0];
    int down = bomb.flameArea[1];
    int left = bomb.flameArea[2];
    int right = bomb.flameArea[3];
    if (spriteNum == 1) {
      g2.drawImage(exploded, worldX, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
      for (int i = 0; i < flameSize; i++) {
        for (int j = 0; j < 4; j++) {
          int pos = GamePanel.tileSize * (i + 1);
          if (i == flameSize - 1) {
            if (i < up)
              g2.drawImage(
                  vertical_top, worldX, worldY - pos, GamePanel.tileSize, GamePanel.tileSize, null);
            if (i < left)
              g2.drawImage(
                  horizontal_left,
                  worldX - pos,
                  worldY,
                  GamePanel.tileSize,
                  GamePanel.tileSize,
                  null);
            if (i < down)
              g2.drawImage(
                  vertical_down,
                  worldX,
                  worldY + pos,
                  GamePanel.tileSize,
                  GamePanel.tileSize,
                  null);
            if (i < right)
              g2.drawImage(
                  horizontal_right,
                  worldX + pos,
                  worldY,
                  GamePanel.tileSize,
                  GamePanel.tileSize,
                  null);
          } else {
            if (i < up)
              g2.drawImage(
                  vertical, worldX, worldY - pos, GamePanel.tileSize, GamePanel.tileSize, null);
            if (i < left)
              g2.drawImage(
                  horizontal, worldX - pos, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
            if (i < down)
              g2.drawImage(
                  vertical, worldX, worldY + pos, GamePanel.tileSize, GamePanel.tileSize, null);
            if (i < right)
              g2.drawImage(
                  horizontal, worldX + pos, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
          }
        }
      }
    }
    if (spriteNum == 2) {
      g2.drawImage(exploded1, worldX, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
      for (int i = 0; i < flameSize; i++) {
        for (int j = 0; j < 4; j++) {
          int pos = GamePanel.tileSize * (i + 1);
          if (i == flameSize - 1) {
            if (i < up)
              g2.drawImage(
                  vertical_top1,
                  worldX,
                  worldY - pos,
                  GamePanel.tileSize,
                  GamePanel.tileSize,
                  null);
            if (i < left)
              g2.drawImage(
                  horizontal_left1,
                  worldX - pos,
                  worldY,
                  GamePanel.tileSize,
                  GamePanel.tileSize,
                  null);
            if (i < down)
              g2.drawImage(
                  vertical_down1,
                  worldX,
                  worldY + pos,
                  GamePanel.tileSize,
                  GamePanel.tileSize,
                  null);
            if (i < right)
              g2.drawImage(
                  horizontal_right1,
                  worldX + pos,
                  worldY,
                  GamePanel.tileSize,
                  GamePanel.tileSize,
                  null);
          } else {
            if (i < up)
              g2.drawImage(
                  vertical1, worldX, worldY - pos, GamePanel.tileSize, GamePanel.tileSize, null);
            if (i < left)
              g2.drawImage(
                  horizontal1, worldX - pos, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
            if (i < down)
              g2.drawImage(
                  vertical1, worldX, worldY + pos, GamePanel.tileSize, GamePanel.tileSize, null);
            if (i < right)
              g2.drawImage(
                  horizontal1, worldX + pos, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
          }
        }
      }
    }
    if (spriteNum == 3) {
      g2.drawImage(exploded2, worldX, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
      for (int i = 0; i < flameSize; i++) {
        for (int j = 0; j < 4; j++) {
          int pos = GamePanel.tileSize * (i + 1);
          if (i == flameSize - 1) {
            if (i < up)
              g2.drawImage(
                  vertical_top2,
                  worldX,
                  worldY - pos,
                  GamePanel.tileSize,
                  GamePanel.tileSize,
                  null);
            if (i < left)
              g2.drawImage(
                  horizontal_left2,
                  worldX - pos,
                  worldY,
                  GamePanel.tileSize,
                  GamePanel.tileSize,
                  null);
            if (i < down)
              g2.drawImage(
                  vertical_down2,
                  worldX,
                  worldY + pos,
                  GamePanel.tileSize,
                  GamePanel.tileSize,
                  null);
            if (i < right)
              g2.drawImage(
                  horizontal_right2,
                  worldX + pos,
                  worldY,
                  GamePanel.tileSize,
                  GamePanel.tileSize,
                  null);
          } else {
            if (i < up)
              g2.drawImage(
                  vertical2, worldX, worldY - pos, GamePanel.tileSize, GamePanel.tileSize, null);
            if (i < left)
              g2.drawImage(
                  horizontal2, worldX - pos, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
            if (i < down)
              g2.drawImage(
                  vertical2, worldX, worldY + pos, GamePanel.tileSize, GamePanel.tileSize, null);
            if (i < right)
              g2.drawImage(
                  horizontal2, worldX + pos, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
          }
        }
      }
    }
  }
}
