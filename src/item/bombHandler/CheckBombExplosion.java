package item.bombHandler;

import entities.Entity;
import graphics.MapConverter;
import item.Item;
import item.wall.Brick;
import main.GamePanel;

import java.util.ArrayList;

public class CheckBombExplosion {
  private GamePanel gp;
  private ArrayList<Item> items = new ArrayList<>();

  public CheckBombExplosion(GamePanel gp) {
    this.gp = gp;
  }

  public void checkBombExplosionCollision(Entity entity, Bomb bomb) {
    int entityLeft = entity.worldX + entity.solidArea.x;
    int entityRight = entityLeft + entity.solidArea.width;
    int entityTop = entity.worldY + entity.solidArea.y;
    int entityBottom = entityTop + entity.solidArea.height;
    int flameSizeUp = bomb.flameArea[0];
    int flameSizeDown = bomb.flameArea[1];
    int flameSizeLeft = bomb.flameArea[2];
    int flameSizeRight = bomb.flameArea[3];
    int topBomb = bomb.worldY - (GamePanel.tileSize * flameSizeUp);
    int bottomBomb = topBomb + GamePanel.tileSize + (GamePanel.tileSize * flameSizeDown);
    int leftBomb = bomb.worldX - (GamePanel.tileSize * flameSizeLeft);
    int rightBomb = leftBomb + GamePanel.tileSize + (GamePanel.tileSize * flameSizeRight);

    if (entityBottom <= bottomBomb && entityBottom >= topBomb) {
      if (rightBomb >= entityLeft && leftBomb <= entityLeft) {
        entity.collisionDead = true;
      } else if (rightBomb >= entityRight && leftBomb <= entityRight) {
        entity.collisionDead = true;
      }
    }
  }

  public void BombActivated(Bomb other, Bomb bomb) {
    int left = other.worldX;
    int right = left + GamePanel.tileSize;
    int top = other.worldY;
    int bottom = top + GamePanel.tileSize;
    int flameSizeUp = bomb.flameArea[0];
    int flameSizeDown = bomb.flameArea[1];
    int flameSizeLeft = bomb.flameArea[2];
    int flameSizeRight = bomb.flameArea[3];
    int topBomb = bomb.worldY - (GamePanel.tileSize * flameSizeUp);
    int bottomBomb = topBomb + GamePanel.tileSize + (GamePanel.tileSize * flameSizeDown);
    int leftBomb = bomb.worldX - (GamePanel.tileSize * flameSizeLeft);
    int rightBomb = leftBomb + GamePanel.tileSize + (GamePanel.tileSize * flameSizeRight);
    if ((bottom >= topBomb && bottom <= bottomBomb) || (top <= bottomBomb && top >= topBomb)) {
      if (bomb.worldX <= right && right <= bomb.worldX + GamePanel.tileSize) other.countExplode = 0;
      else if (bomb.worldX <= left && left <= bomb.worldX + GamePanel.tileSize)
        other.countExplode = 0;
    } else if ((left >= leftBomb && left <= rightBomb)
        || (right <= rightBomb && right >= leftBomb)) {
      if (bomb.worldY <= bottom && bottom <= bomb.worldY + GamePanel.tileSize)
        other.countExplode = 0;
      else if (bomb.worldY <= top && top <= bomb.worldY + GamePanel.tileSize)
        other.countExplode = 0;
    }
  }

  public void checkBombDestroy(Brick brick, Bomb bomb) {
    int up = bomb.flameArea[0];
    int down = bomb.flameArea[1];
    int left = bomb.flameArea[2];
    int right = bomb.flameArea[3];
    int brickLeft = brick.worldX;
    int brickTop = brick.worldY;
    int brickBottom = brickTop + GamePanel.tileSize;
    int brickRight = brickLeft + GamePanel.tileSize;

    int bombTop = bomb.worldY;
    int bombBottom = bombTop + GamePanel.tileSize;
    int bombLeft = bomb.worldX;
    int bombRight = bombLeft + GamePanel.tileSize;

    // TOP
    for (int i = 0; i < up; i++) {
      int bombTopUp = bombTop - (GamePanel.tileSize * i);
      if (brickBottom == bombTopUp && brickLeft == bombLeft && brickRight == bombRight) {
        brick.collision = true;
        break;
      }
    }
    // DOWN
    for (int i = 0; i < down; i++) {
      int bombBottomDown = bombBottom + (GamePanel.tileSize * i);
      if (brickTop == bombBottomDown && brickLeft == bombLeft && brickRight == bombRight) {
        brick.collision = true;
        break;
      }
    }

    // LEFT
    for (int i = 0; i < left; i++) {
      int bombLeftLeft = bombLeft - (GamePanel.tileSize * i);
      if (brickRight == bombLeftLeft && brickBottom == bombBottom && brickTop == bombTop) {
        brick.collision = true;
        break;
      }
    }

    // RIGHT
    for (int i = 0; i < right; i++) {
      int bombRightRight = bombRight + (GamePanel.tileSize * i);
      if (brickLeft == bombRightRight && brickBottom == bombBottom && brickTop == bombTop) {
        brick.collision = true;
        break;
      }
    }
  }

  public void checkFlameArea(int[] flameArea, Bomb bomb) {
    int flameSize = bomb.bomber.getFlameSize();
    items = gp.tileManager.getItems();

    int bombTop = bomb.worldY;
    int bombBottom = bombTop + GamePanel.tileSize;
    int bombLeft = bomb.worldX;

    // TOP
    for (int i = 0; i < flameSize; i++) {
      int bombTopUp = bombTop - (GamePanel.tileSize * i);
      int col = bombLeft / GamePanel.tileSize;
      int row = (bombTopUp - GamePanel.tileSize) / GamePanel.tileSize;
      char tileChar = gp.tileManager.mapTile[col][row];
      int tileNum = MapConverter.Converter(tileChar);
      if (gp.tileManager.tile[tileNum].collision) {
        if (i < flameArea[0]) {
          flameArea[0] = i;
        }
      }
      if (items != null) {
        for (Item item : items) {
          if (item instanceof Brick) {
            Brick brick = (Brick) item;
            int brickLeft = brick.worldX;
            int brickTop = brick.worldY;
            if (bombLeft == brickLeft && bombTopUp == brickTop) {
              if (i < flameArea[0]) {
                flameArea[0] = i;
              }
            }
          }
        }
      }
    }

    // DOWN
    for (int i = 0; i < flameSize; i++) {
      int bombBottomDown = bombBottom + (GamePanel.tileSize * i);
      int col = bombLeft / GamePanel.tileSize;
      int row = bombBottomDown / GamePanel.tileSize;
      char tileChar = gp.tileManager.mapTile[col][row];
      int tileNum = MapConverter.Converter(tileChar);
      if (gp.tileManager.tile[tileNum].collision) {
        flameArea[1] = i;
        break;
      }
      if (items != null) {
        for (Item item : items) {
          if (item instanceof Brick) {
            Brick brick = (Brick) item;
            int brickLeft = brick.worldX;
            int brickBottom = brick.worldY + GamePanel.tileSize;
            if (bombLeft == brickLeft && bombBottomDown == brickBottom) {
              if (i < flameArea[1]) {
                flameArea[1] = i;
              }
            }
          }
        }
      }
    }

    // LEFT
    for (int i = 0; i < flameSize; i++) {
      int bombLeftLeft = bombLeft - (GamePanel.tileSize * i);
      int col = (bombLeftLeft - GamePanel.tileSize) / GamePanel.tileSize;
      int row = bombTop / GamePanel.tileSize;
      char tileChar = gp.tileManager.mapTile[col][row];
      int tileNum = MapConverter.Converter(tileChar);
      if (gp.tileManager.tile[tileNum].collision) {
        flameArea[2] = i;
        break;
      }
      if (items != null) {
        for (Item item : items) {
          if (item instanceof Brick) {
            Brick brick = (Brick) item;
            int brickLeft = brick.worldX;
            int brickTop = brick.worldY;
            if (bombLeftLeft == brickLeft && bombTop == brickTop) {
              if (i < flameArea[2]) {
                flameArea[2] = i;
              }
            }
          }
        }
      }
    }

    // RIGHT
    for (int i = 0; i < flameSize; i++) {
      int bombRightRight = bombLeft + GamePanel.tileSize + (GamePanel.tileSize * i);
      int col = bombRightRight / GamePanel.tileSize;
      int row = bombTop / GamePanel.tileSize;
      char tileChar = gp.tileManager.mapTile[col][row];
      int tileNum = MapConverter.Converter(tileChar);
      if (gp.tileManager.tile[tileNum].collision) {
        flameArea[3] = i;
        break;
      }
      if (items != null) {
        for (Item item : items) {
          if (item instanceof Brick) {
            Brick brick = (Brick) item;
            int brickRight = brick.worldX + GamePanel.tileSize;
            int brickTop = brick.worldY;
            if (bombRightRight == brickRight && bombTop == brickTop) {
              if (i < flameArea[3]) {
                flameArea[3] = i;
              }
            }
          }
        }
      }
    }
  }
}
