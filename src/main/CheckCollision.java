package main;

import entities.Bomber;
import entities.Entity;
import graphics.MapConverter;
import item.Item;
import item.bombHandler.Bomb;

public class CheckCollision {
  GamePanel gp;

  public CheckCollision(GamePanel gp) {
    this.gp = gp;
  }

  public void checkTile(Entity entity) {
    int entityLeftWorldX = entity.worldX + entity.solidArea.x;
    int entityRightWorldX = entity.worldX + entity.solidArea.y + entity.solidArea.width;
    int entityTopWorldY = entity.worldY + entity.solidArea.y;
    int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
    int entityLeftCol = entityLeftWorldX / gp.tileSize;
    int entityRightCol = entityRightWorldX / gp.tileSize;
    int entityTopRow = entityTopWorldY / gp.tileSize;
    int entityBottomRow = entityBottomWorldY / gp.tileSize;

    char tileChar1, tileChar2;
    int tileNum1, tileNum2;
    switch (entity.direction) {
      case Entity.UP_DIRECTION:
        entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
        tileChar1 = gp.tileManager.mapTile[entityLeftCol][entityTopRow];
        tileChar2 = gp.tileManager.mapTile[entityRightCol][entityTopRow];
        tileNum1 = MapConverter.Converter(tileChar1);
        tileNum2 = MapConverter.Converter(tileChar2);

        if (gp.tileManager.tile[tileNum1].collision
            || gp.tileManager.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
      case Entity.DOWN_DIRECTION:
        entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
        tileChar1 = gp.tileManager.mapTile[entityLeftCol][entityBottomRow];
        tileChar2 = gp.tileManager.mapTile[entityRightCol][entityBottomRow];
        tileNum1 = MapConverter.Converter(tileChar1);
        tileNum2 = MapConverter.Converter(tileChar2);
        if (gp.tileManager.tile[tileNum1].collision
            || gp.tileManager.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
      case Entity.LEFT_DIRECTION:
        entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
        tileChar1 = gp.tileManager.mapTile[entityLeftCol][entityTopRow];
        tileChar2 = gp.tileManager.mapTile[entityLeftCol][entityBottomRow];
        tileNum1 = MapConverter.Converter(tileChar1);
        tileNum2 = MapConverter.Converter(tileChar2);
        if (gp.tileManager.tile[tileNum1].collision
            || gp.tileManager.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
      case Entity.RIGHT_DIRECTION:
        entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
        tileChar1 = gp.tileManager.mapTile[entityRightCol][entityTopRow];
        tileChar2 = gp.tileManager.mapTile[entityRightCol][entityBottomRow];
        tileNum1 = MapConverter.Converter(tileChar1);
        tileNum2 = MapConverter.Converter(tileChar2);
        if (gp.tileManager.tile[tileNum1].collision
            || gp.tileManager.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
    }
  }

  public void checkPowerUp(Entity entity, Item item) {
    int entityLeft = entity.worldX + entity.solidArea.x;
    int entityRight = entityLeft + entity.solidArea.width;
    int entityTop = entity.worldY + entity.solidArea.y;
    int entityBottom = entityTop + entity.solidArea.height;
    if (entityBottom <= item.worldY + GamePanel.tileSize && entityBottom >= item.worldY) {
      if (item.worldX + GamePanel.tileSize >= entityLeft && item.worldX <= entityLeft) {
        item.collision = true; // LEFT
      } else if (item.worldX + GamePanel.tileSize >= entityRight && item.worldX <= entityRight) {
        item.collision = true;
      }
    }
  }

  public void checkBrickandBomb(Entity entity, Item item) {
    int entityLeft = entity.worldX + entity.solidArea.x;
    int entityRight = entity.worldX + entity.solidArea.y + entity.solidArea.width;
    int entityTop = entity.worldY + entity.solidArea.y;
    int entityBottom = entity.worldY + entity.solidArea.y + entity.solidArea.height;

    int speed = 2;
    if (entity instanceof Bomber) {
      speed = gp.bomber.speed;
      entityLeft -= 2;
      entityRight += 3;
      entityTop -= 1;
      entityBottom += 3;
    }
    if (item instanceof Bomb) {
      entityLeft = entity.worldX - speed;
      entityRight = entityLeft + GamePanel.tileSize + speed;
      entityTop = entity.worldY - speed;
      entityBottom = entityTop + GamePanel.tileSize + speed;
    }

    switch (entity.direction) {
      case Entity.UP_DIRECTION:
        if (entityTop < item.worldY + GamePanel.tileSize && entityTop > item.worldY) {
          if (item.worldX + GamePanel.tileSize - speed > entityLeft && item.worldX + speed < entityLeft) {
            entity.collisionOn = true; // LEFT
          } else if (item.worldX + GamePanel.tileSize - speed > entityRight && item.worldX + speed < entityRight) {
            entity.collisionOn = true;
          }
        }
        break;
      case Entity.DOWN_DIRECTION:
        if (entityBottom < item.worldY + GamePanel.tileSize && entityBottom > item.worldY) {
          if (item.worldX + GamePanel.tileSize - speed > entityLeft && item.worldX + speed < entityLeft) {
            entity.collisionOn = true; // LEFT
          } else if (item.worldX + GamePanel.tileSize - speed > entityRight && item.worldX + speed < entityRight) {
            entity.collisionOn = true;
          }
        }
        break;
      case Entity.LEFT_DIRECTION:
        if (entityLeft < item.worldX + GamePanel.tileSize && entityLeft > item.worldX) {
          if (item.worldY + GamePanel.tileSize - speed >= entityTop && item.worldY + speed < entityTop) {
            entity.collisionOn = true; // LEFT
          } else if (item.worldY + GamePanel.tileSize - speed > entityBottom && item.worldY + speed < entityBottom) {
            entity.collisionOn = true;
          }
        }
        break;
      case Entity.RIGHT_DIRECTION:
        if (entityRight < item.worldX + GamePanel.tileSize && entityRight > item.worldX) {
          if (item.worldY + GamePanel.tileSize - speed > entityTop && item.worldY + speed < entityTop) {
            entity.collisionOn = true;
          } else if (item.worldY + GamePanel.tileSize - speed > entityBottom && item.worldY + speed < entityBottom) {
            entity.collisionOn = true;
          }
        }
        break;
    }
  }
}

