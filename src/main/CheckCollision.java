package main;

import entities.Bomber;
import entities.Entity;
import graphics.MapConverter;

import graphics.Sprite;
import item.Item;

import javax.imageio.ImageIO;
import java.io.IOException;

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
            case 1: //LEFT_DIRECTION
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileChar1 = gp.tileManager.mapTile[entityLeftCol][entityTopRow];
                tileChar2 = gp.tileManager.mapTile[entityLeftCol][entityBottomRow];
                tileNum1 = MapConverter.Converter(tileChar1);
                tileNum2 = MapConverter.Converter(tileChar2);
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }

                // kiểm tra có phải item ko
                if (gp.tileManager.tile[tileNum1].isItem && gp.tileManager.tile[tileNum2].isItem ){
                    Bomber.getItem = true;
                    try {
                        gp.tileManager.tile[tileNum1].image = ImageIO.read(getClass().getResourceAsStream(Sprite.grass));
                        gp.tileManager.tile[tileNum1].isItem = false;
                        gp.tileManager.tile[tileNum2].image = ImageIO.read(getClass().getResourceAsStream(Sprite.grass));
                        gp.tileManager.tile[tileNum2].isItem = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Item.listItems.add(tileNum1);
                }
                break;
            case 2: //RIGHT_DIRECTION
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileChar1 = gp.tileManager.mapTile[entityRightCol][entityTopRow];
                tileChar2 = gp.tileManager.mapTile[entityRightCol][entityBottomRow];
                tileNum1 = MapConverter.Converter(tileChar1);
                tileNum2 = MapConverter.Converter(tileChar2);
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                if (gp.tileManager.tile[tileNum1].isItem && gp.tileManager.tile[tileNum2].isItem ){
                    Bomber.getItem = true;
                    try {
                        gp.tileManager.tile[tileNum1].image = ImageIO.read(getClass().getResourceAsStream(Sprite.grass));
                        gp.tileManager.tile[tileNum1].isItem = false;
                        gp.tileManager.tile[tileNum2].image = ImageIO.read(getClass().getResourceAsStream(Sprite.grass));
                        gp.tileManager.tile[tileNum2].isItem = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Item.listItems.add(tileNum1);
                }
                break;
            case 3: //UP_DIRECTION
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileChar1 = gp.tileManager.mapTile[entityLeftCol][entityTopRow];
                tileChar2 = gp.tileManager.mapTile[entityRightCol][entityTopRow];
                tileNum1 = MapConverter.Converter(tileChar1);
                tileNum2 = MapConverter.Converter(tileChar2);

                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                if (gp.tileManager.tile[tileNum1].isItem && gp.tileManager.tile[tileNum2].isItem ){
                    Bomber.getItem = true;
                    try {
                        gp.tileManager.tile[tileNum1].image = ImageIO.read(getClass().getResourceAsStream(Sprite.grass));
                        gp.tileManager.tile[tileNum1].isItem = false;
                        gp.tileManager.tile[tileNum2].image = ImageIO.read(getClass().getResourceAsStream(Sprite.grass));
                        gp.tileManager.tile[tileNum2].isItem = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Item.listItems.add(tileNum1);
                }

                break;
            case 4: //DOWN_DIRECTION
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileChar1 = gp.tileManager.mapTile[entityLeftCol][entityBottomRow];
                tileChar2 = gp.tileManager.mapTile[entityRightCol][entityBottomRow];
                tileNum1 = MapConverter.Converter(tileChar1);
                tileNum2 = MapConverter.Converter(tileChar2);
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                if (gp.tileManager.tile[tileNum1].isItem && gp.tileManager.tile[tileNum2].isItem ){
                    Bomber.getItem = true;
                    try {
                        gp.tileManager.tile[tileNum1].image = ImageIO.read(getClass().getResourceAsStream(Sprite.grass));
                        gp.tileManager.tile[tileNum1].isItem = false;
                        gp.tileManager.tile[tileNum2].image = ImageIO.read(getClass().getResourceAsStream(Sprite.grass));
                        gp.tileManager.tile[tileNum2].isItem = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Item.listItems.add(tileNum1);
                }
                break;
        }
    }
}
