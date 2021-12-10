package entities;

import main.GamePanel;
import main.KeyHandler;
import graphics.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomber extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;

    public Bomber(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        //giới hạn phạm vi va chạm của nhân vật
        solidArea = new Rectangle(5, 18, 18, 25);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize;
        worldY = gp.tileSize;
        speed = 1; // tốc độ di chuyển
        direction = DOWN_DIRECTION;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_up1));
            up2 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_up2));
            up3 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_up3));
            down1 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_down1));
            down2 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_down2));
            down3 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_down3));
            left1 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_left1));
            left2 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_left2));
            left3 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_left3));
            right1 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_right1));
            right2 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_right2));
            right3 = ImageIO.read(getClass().getResourceAsStream(Sprite.bomber_right3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Điểu khiển nhân vật. */
    public void update() {
        if (keyHandler.upPressed
            || keyHandler.downPressed
            || keyHandler.rightPressed
            || keyHandler.leftPressed) {
            if (keyHandler.upPressed) {
                direction = UP_DIRECTION;
            } else if (keyHandler.downPressed) {
                direction = DOWN_DIRECTION;
            } else if (keyHandler.leftPressed) {
                direction = LEFT_DIRECTION;
            } else if (keyHandler.rightPressed) {
                direction = RIGHT_DIRECTION;
            }
             // check va chạm
            collisionOn = false;
            gp.checkCollision.checkTile(this);

            //Nếu va chạm
            if (!collisionOn) {
                switch (direction) {
                    case UP_DIRECTION:
                        worldY -= speed;
                        break;
                    case DOWN_DIRECTION:
                        worldY += speed;
                        break;
                    case LEFT_DIRECTION:
                        worldX -= speed;
                        break;
                    case RIGHT_DIRECTION:
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
    }

    /** Render ra màn hình. */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch(direction) {
            case UP_DIRECTION:
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
            break;

            case DOWN_DIRECTION:
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
            break;

            case LEFT_DIRECTION:
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

            case RIGHT_DIRECTION:
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
        }

        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);

    }
}
