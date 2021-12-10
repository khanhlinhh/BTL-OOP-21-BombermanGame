package item;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

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
