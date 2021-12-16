package item.bombHandler;

import entities.Bomber;
import graphics.Sprite;
import item.Item;
import item.wall.Brick;
import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Bomb extends Item {
  private BufferedImage bomb1, bomb2, bomb3;
  private int countExplode = 2;
  private Flame flame;
  public boolean exploded = false;
  Bomber bomber;
  private Timer timer;
  private GamePanel gp;
  private ArrayList<Item> items = new ArrayList<>();
  public int[] flameArea;

  public Bomb(GamePanel gp, Bomber bomber) {
    this.gp = gp;
    this.bomber = bomber;
    items = gp.tileManager.getItems();
    checkBombExplosion = new CheckBombExplosion(gp);
    flameArea = new int[]{this.bomber.getFlameSize(), this.bomber.getFlameSize(), this.bomber.getFlameSize(), this.bomber.getFlameSize()};
    worldX = (bomber.worldX + bomber.solidArea.x) / GamePanel.tileSize * GamePanel.tileSize;
    worldY = (bomber.worldY + bomber.solidArea.height) / GamePanel.tileSize * GamePanel.tileSize;
    timer = new Timer(1000, e -> {
      if (countExplode > 0) {
        countExplode--;
      } else {
        timer.stop();
      }
    });
    timer.start();
    flameArea = new int[]{this.bomber.getFlameSize(), this.bomber.getFlameSize(), this.bomber.getFlameSize(), this.bomber.getFlameSize()};
    checkBombExplosion.checkFlameArea(this.flameArea, this);
    getImage();
  }

  @Override
  public void getImage() {
    try {
      bomb1 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomb));
      bomb2 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomb1));
      bomb3 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomb2));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update() {
    if (countExplode == 0 && flame == null) {
      exploded = true;
      flame = new Flame(this);
    } else {
      spriteCounter++;
      if (spriteCounter > 12) {
        if (spriteNum == 1) {
          spriteNum = 2;
        } else if (spriteNum == 2) {
          spriteNum = 3;
        } else if (spriteNum == 3) {
          spriteNum = 1;
        }
        spriteCounter = 0;
      }
    }
    if (exploded) {
      if (items != null) {
        for(Item item : items) {
          if (item instanceof Brick) {
            Brick brick = (Brick) item;
            checkBombExplosion.checkBombDestroy(brick, this);
          }
        }
      }
    }

    if (flame != null) {
      if (flame.isDisappeared) {
        isDisappeared = true;
      } else {
        flame.update();
      }
    }
  }

  @Override
  public void draw(Graphics2D g2) {
    if (flame != null) {
      flame.draw(g2);
    } else {
      BufferedImage image = null;
      if (spriteNum == 1) {
        image = bomb1;
      }
      if (spriteNum == 2) {
        image = bomb2;
      }
      if (spriteNum == 3) {
        image = bomb3;
      }
      g2.drawImage(image, worldX, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
    }
  }
}
