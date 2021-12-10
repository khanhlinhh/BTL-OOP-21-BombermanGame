package main;

import entities.Entity;
import graphics.MapConverter;

public class CheckCollision {
    GamePanel gp;

    public CheckCollision(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.y + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY =entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        char tileChar1, tileChar2;
        int tileNum1, tileNum2;
        switch (entity.direction) {
            case 1: //LEFT_DIRECTION
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileChar1 = gp.tileManager.mapTile[entityLeftCol][entityTopRow];
                tileChar2 = gp.tileManager.mapTile[entityLeftCol][entityBottomRow];
                tileNum1 = MapConverter.Converter(tileChar1);
                tileNum2 = MapConverter.Converter(tileChar2);
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case 2: //RIGHT_DIRECTION
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileChar1 = gp.tileManager.mapTile[entityRightCol][entityTopRow];
                tileChar2 = gp.tileManager.mapTile[entityRightCol][entityBottomRow];
                tileNum1 = MapConverter.Converter(tileChar1);
                tileNum2 = MapConverter.Converter(tileChar2);
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case 3: //UP_DIRECTION
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileChar1 = gp.tileManager.mapTile[entityLeftCol][entityTopRow];
                tileChar2 = gp.tileManager.mapTile[entityRightCol][entityTopRow];
                tileNum1 = MapConverter.Converter(tileChar1);
                tileNum2 = MapConverter.Converter(tileChar2);

                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case 4: //DOWN_DIRECTION
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileChar1 = gp.tileManager.mapTile[entityLeftCol][entityBottomRow];
                tileChar2 = gp.tileManager.mapTile[entityRightCol][entityBottomRow];
                tileNum1 = MapConverter.Converter(tileChar1);
                tileNum2 = MapConverter.Converter(tileChar2);
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;


        }
    }
}
