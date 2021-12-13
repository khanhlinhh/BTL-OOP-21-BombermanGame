package item;

import entities.Bomber;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Item {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public static List<Integer> listItems = new ArrayList<>();

    public static final int HEART_ITEM = 0;     // Thêm mạng cho người chơi (T ko biết đặt tên gì :)))
    public static final int BOMB_ITEM = 1;      // Thêm số bom được đặt
    public static final int SPEED_ITEM = 2;     // Tăng tốc độ
    public static final int FLAME_ITEM = 3;     // Tăng phạm vi ảnh hưởng của bom
    public static final int COIN = 4;


    public static void update() {
        while (!listItems.isEmpty()) {
            switch (listItems.remove(0)) {
                case HEART_ITEM:
                    Bomber.heart ++;
                case BOMB_ITEM:
                    Bomb.numOfBomb++;
                    break;
                case SPEED_ITEM:
                    Bomber.speed *= 2;
                    break;
                case FLAME_ITEM:
                    Bomb.flame++;
                    break;
                case COIN:
                    Bomber.coin ++;
            }
        }
        Bomber.getItem = false;
    }

//    public void draw(Graphics2D g2, GamePanel gp) {
//        int screenX = worldX - gp.bomber.worldX + gp.bomber.screenX;
//        int screenY = worldY - gp.bomber.worldY + gp.bomber.screenY;
//        if (worldX + gp.tileSize > gp.bomber.worldX - gp.bomber.screenX &&
//                worldX - gp.tileSize < gp.bomber.worldX + gp.bomber.screenX &&
//                worldY + gp.tileSize > gp.bomber.worldY - gp.bomber.screenY &&
//                worldY - gp.tileSize < gp.bomber.worldY + gp.bomber.screenY) {
//            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//        }
//    }
}
