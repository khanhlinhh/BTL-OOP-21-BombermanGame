package item.wall;

import graphics.Sprite;
import item.Item;
import item.bombHandler.Bomb;
import item.bombHandler.CheckBombExplosion;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Brick extends Item {
    private GamePanel gp;
    private BufferedImage brick, brick_exploded1, brick_exploded2, brick_exploded3;

    public Brick(GamePanel gp, int xUnit, int yUnit) {
        this.gp = gp;
        collision = false;
        isPowerUp = false;
        worldX = xUnit * GamePanel.tileSize;
        worldY = yUnit * GamePanel.tileSize;
        checkBombExplosion = new CheckBombExplosion(this.gp);
        getImage();
    }

    @Override
    public void getImage() {
        try {
            brick = ImageIO.read(getClass().getResourceAsStream(Sprite.brick));
            brick_exploded1 = ImageIO.read(getClass().getResourceAsStream(Sprite.brick_exploded));
            brick_exploded2 = ImageIO.read(getClass().getResourceAsStream(Sprite.brick_exploded1));
            brick_exploded3 = ImageIO.read(getClass().getResourceAsStream(Sprite.brick_exploded2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if (collision) {
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    isDisappeared = true;
                }
                spriteCounter = 0;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(brick, worldX, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
        if (collision) {
            BufferedImage image = null;
            if (spriteNum == 1) {
                image = brick_exploded1;
            }
            if (spriteNum == 2) {
                image = brick_exploded2;
            }
            if (spriteNum == 3) {
                image = brick_exploded3;
            }
            g2.drawImage(image, worldX, worldY, GamePanel.tileSize, GamePanel.tileSize, null);
        }
    }
}
