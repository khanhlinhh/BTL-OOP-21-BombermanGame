package entities.enemies;

import entities.Entity;
import graphics.Sprite;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Balloon extends Entity {
    GamePanel gp;

    public Balloon(GamePanel gp, int xUnit, int yUnit) {
        this.gp = gp;
        //giới hạn phạm vi va chạm của vật
        solidArea = new Rectangle(0, 0, 45, 45);

        worldX = xUnit; //Toạ độ xuất hiện (tileSize * thứ tự cột)
        worldY = yUnit; //Toạ độ xuất hiện (tileSize * thứ tự hàng)

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        speed = 1; // tốc độ di chuyển
        direction = "right";
    }

    /** Hàm lấy ảnh */
    public void getPlayerImage() {
        try {
            left1 = ImageIO.read(getClass().getResourceAsStream(Sprite.balloon_left1));
            left2 = ImageIO.read(getClass().getResourceAsStream(Sprite.balloon_left2));
            left3 = ImageIO.read(getClass().getResourceAsStream(Sprite.balloon_left3));
            right1 = ImageIO.read(getClass().getResourceAsStream(Sprite.balloon_right1));
            right2 = ImageIO.read(getClass().getResourceAsStream(Sprite.balloon_right2));
            right3 = ImageIO.read(getClass().getResourceAsStream(Sprite.balloon_right3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Hàm di chuyển. */
    public void update() {
        if (collisionOn && direction == "left") {
            direction = "right";
        }
        if (collisionOn && direction == "right") {
            direction = "left";

        }
        if (collisionOn && direction == "down") {
            direction = "up";

        }
        if (collisionOn && direction == "up") {
            direction = "down";

        }
        // check va chạm
        collisionOn = false;
        gp.checkCollision.checkTile(this);

        //Nếu va chạm
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
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
        switch(direction) {
            case "down", "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                break;

            case "left", "up":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                break;
        }

        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);

    }
}
