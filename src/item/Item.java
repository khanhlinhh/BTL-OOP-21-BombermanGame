package item;

import java.awt.image.BufferedImage;

public class Item {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
