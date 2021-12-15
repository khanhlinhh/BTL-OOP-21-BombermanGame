package entities;

import graphics.Sprite;
import item.Item;
import item.bombHandler.Bomb;
import item.wall.Brick;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Bomber extends Entity {
  private int numOfBomb = 1;
  private int flameSize = 1;

  private GamePanel gp;
  private KeyHandler keyHandler;
  private ArrayList<Bomb> bombs = new ArrayList<>();

  public Bomber(GamePanel gp, KeyHandler keyHandler) {
    this.gp = gp;
    this.keyHandler = keyHandler;
    // giới hạn phạm vi va chạm của nhân vật
    solidArea = new Rectangle(8, 18, 20, 25);

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {
    worldX = GamePanel.tileSize;
    worldY = GamePanel.tileSize;
    speed = 2; // tốc độ di chuyển
    direction = DOWN_DIRECTION;
  }

  public void getPlayerImage() {
    try {
      up1 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_up1));
      up2 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_up2));
      up3 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_up3));
      down1 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_down1));
      down2 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_down2));
      down3 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_down3));
      left1 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_left1));
      left2 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_left2));
      left3 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_left3));
      right1 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_right1));
      right2 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_right2));
      right3 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_right3));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Điểu khiển nhân vật. */
  public void update() {
    if (keyHandler.upPressed
        || keyHandler.downPressed
        || keyHandler.rightPressed
        || keyHandler.leftPressed) {
      if (keyHandler.upPressed) {
        direction = UP_DIRECTION;
      } else if (keyHandler.downPressed) {
        direction = DOWN_DIRECTION;
      } else if (keyHandler.leftPressed) {
        direction = LEFT_DIRECTION;
      } else if (keyHandler.rightPressed) {
        direction = RIGHT_DIRECTION;
      }
      // check va chạm
      collisionOn = false;
      gp.checkCollision.checkTile(this);
      for (Item item : gp.tileManager.getItems()) {
        if (item instanceof Brick) {
          gp.checkCollision.checkBrick(this, item);
          if (collisionOn) {
            break;
          }
        }
      }
      // Nếu va chạm
      if (!collisionOn) {
        switch (direction) {
          case UP_DIRECTION -> // UP
                  worldY -= speed;
          case DOWN_DIRECTION -> // DOWN
                  worldY += speed;
          case LEFT_DIRECTION -> // LEFT
                  worldX -= speed;
          case RIGHT_DIRECTION -> // RIGHT
                  worldX += speed;
        }
      }

      // Dùng sprite để thành animation
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
    if (keyHandler.spacePressed) {
      if (bombs.size() < numOfBomb) {
        bombs.add(new Bomb(this));
      }
    }
    if (!bombs.isEmpty()) {
      for (Bomb bomb : bombs) {
        if (bomb.exploded) {
          bombs.remove(bomb);
          break;
        } else {
          bomb.update();
        }
      }
    }
  }

  /** Render ra màn hình. */
  public void draw(Graphics2D g2) {
    BufferedImage image = null;
    switch (direction) {
      case UP_DIRECTION -> { // UP
        if (spriteNum == 1) {
          image = up1;
        }
        if (spriteNum == 2) {
          image = up2;
        }
        if (spriteNum == 3) {
          image = up3;
        }
      }
      case DOWN_DIRECTION -> { // DOWN
        if (spriteNum == 1) {
          image = down1;
        }
        if (spriteNum == 2) {
          image = down2;
        }
        if (spriteNum == 3) {
          image = down3;
        }
      }
      case LEFT_DIRECTION -> { // LEFT
        if (spriteNum == 1) {
          image = left1;
        }
        if (spriteNum == 2) {
          image = left2;
        }
        if (spriteNum == 3) {
          image = left3;
        }
      }
      case RIGHT_DIRECTION -> { // RIGHT
        if (spriteNum == 1) {
          image = right1;
        }
        if (spriteNum == 2) {
          image = right2;
        }
        if (spriteNum == 3) {
          image = right3;
        }
      }
    }
    g2.drawImage(image, worldX, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
    if (!bombs.isEmpty()) {
      for (Bomb bomb : bombs) {
        bomb.draw(g2);
      }
    }
  }

  public int getNumOfBomb() {
    return numOfBomb;
  }

  public void setNumOfBomb(int numOfBomb) {
    this.numOfBomb = numOfBomb;
  }

  public int getFlameSize() {
    return flameSize;
  }

  public void setFlameSize(int flameSize) {
    this.flameSize = flameSize;
  }
}
