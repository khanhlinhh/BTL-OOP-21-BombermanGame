package item;

import entities.Bomber;
import graphics.Sprite;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomb extends Item {
    private BufferedImage bomb1, bomb2, bomb3;
    private boolean exploded = false;
    Bomber bomber;
    GamePanel gp;

    public Bomb(GamePanel gp, Bomber bomber) {
        this.gp = gp;
        this.bomber = bomber;
        worldX = bomber.worldX;
        worldY = bomber.worldY;
        getBombImage();
    }

    public void getBombImage() {
        try {
            bomb1 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomb));
            bomb2 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomb1));
            bomb3 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomb2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
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

    public void draw(Graphics2D g2) {
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
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
