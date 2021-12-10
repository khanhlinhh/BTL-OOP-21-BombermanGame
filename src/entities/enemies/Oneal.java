package entities.enemies;

import entities.Entity;
import graphics.Sprite;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Oneal extends Entity {
    GamePanel gp;

    public Oneal(GamePanel gp, int xUnit, int yUnit) {
        this.gp = gp;
        //giới hạn phạm vi va chạm của vật
        solidArea = new Rectangle(0, 0, 45, 45);

        worldX = xUnit; //Toạ độ xuất hiện (tileSize * thứ tự cột)
        worldY = yUnit; //Toạ độ xuất hiện (tileSize * thứ tự hàng)

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        speed = 2; // tốc độ di chuyển
        direction = RIGHT_DIRECTION;
    }

    /** Hàm lấy ảnh */
    public void getPlayerImage() {
        try {
            left1 = ImageIO.read(getClass().getResourceAsStream(Sprite.oneal_left1));
            left2 = ImageIO.read(getClass().getResourceAsStream(Sprite.oneal_left2));
            left3 = ImageIO.read(getClass().getResourceAsStream(Sprite.oneal_left3));
            right1 = ImageIO.read(getClass().getResourceAsStream(Sprite.oneal_right1));
            right2 = ImageIO.read(getClass().getResourceAsStream(Sprite.oneal_right2));
            right3 = ImageIO.read(getClass().getResourceAsStream(Sprite.oneal_right3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNewDiretion() {
        return (int) (Math.random() * 4 + 1);
    }

    /** Hàm di chuyển. */
    public void update() {
        if (collisionOn) {
            direction = getNewDiretion();
        }

        // check va chạm
        collisionOn = false;
        gp.checkCollision.checkTile(this);

        //Nếu va chạm
        if (!collisionOn) {
            switch (direction) {
                case UP_DIRECTION -> worldY -= speed;
                case DOWN_DIRECTION -> worldY += speed;
                case LEFT_DIRECTION -> worldX -= speed;
                case RIGHT_DIRECTION -> worldX += speed;
            }
        }

        // Dùng sprite để thành animation
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 3;
            }
            else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    /** Hàm render ra màn hình. */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case DOWN_DIRECTION, RIGHT_DIRECTION -> {
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
            case LEFT_DIRECTION, UP_DIRECTION -> {
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
        }

        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);

    }
}