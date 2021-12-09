package Bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Bomberman.graphics.Sprite;
import Bomberman.BombermanGame;

public abstract class Entity {
    int cur = 0;
    //Tọa độ X tính từ góc trái trên trong Canvas
    public double x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    public double y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public boolean moveUp(Sprite s1, Sprite s2, Sprite s3, double speed) {
        boolean canMove = true;
        img = Sprite.movingSprite(s1, s2, s3, cur++).getFxImage();
        if (cur == 4) {
            cur = 1;
        }
        int xUnit = (int) (x / 32);
        int yUnit = (int) (y / 32);
        if (y % 32 == 0 && BombermanGame.Map[yUnit - 1][xUnit]) {
            return false;
        }
        y -= speed;
        return true;
    }

    public boolean moveDown(Sprite s1, Sprite s2, Sprite s3, double speed) {
        boolean canMove = true;
        img = Sprite.movingSprite(s1, s2, s3, cur++).getFxImage();
        if (cur == 4) {
            cur = 1;
        }
        int xUnit = (int) (x / 32);
        int yUnit1 = (int) (y / 32);
        if (y % 32 == 0 && BombermanGame.Map[yUnit1 + 1][xUnit]) {
            return false;
        }
        y += speed;
        return true;
    }


    public boolean moveRight(Sprite s1, Sprite s2, Sprite s3, double speed) {
        img = Sprite.movingSprite(s1, s2, s3, cur++).getFxImage();
        if (cur == 4) {
            cur = 1;
        }
        int xUnit = (int) (x / 32);
        int yUnit = (int) (y / 32);
        if (x % 32 == 0 && BombermanGame.Map[yUnit][xUnit + 1]) {
            return false;
        }
        x += speed;
        return true;
    }

    public boolean moveLeft(Sprite s1, Sprite s2, Sprite s3, double speed) {
        img = Sprite.movingSprite(s1, s2, s3, cur++).getFxImage();
        if (cur == 4) {
            cur = 1;
        }
        int xUnit = (int) (x / 32);
        int yUnit = (int) (y / 32);
        if (x % 32 == 0 && BombermanGame.Map[yUnit][xUnit - 1]) {
            return false;
        }
        x -= speed;
        return true;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

}
