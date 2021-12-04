package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {
    List<Bomb> bombList = new ArrayList<>();
    int cur = 1;


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }



    public void move(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            boolean canMove = true;
            img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2,
                    cur++).getFxImage();
            if (cur == 4) {
                cur = 1;
            }
//            System.out.println(x + " " + y);
            int xUnit = (int) Math.round(x / 32.0);
            int yUnit = (int) Math.round(y / 32.0);
//            System.out.println(xUnit + " " + yUnit);
            if (BombermanGame.Map[yUnit - 1][xUnit] == '#' || BombermanGame.Map[yUnit - 1][xUnit] == '*') {
                canMove = false;
            }
            if (canMove) {
                y -= 5;
            }
        } else if (event.getCode() == KeyCode.DOWN) {
            boolean canMove = true;
            img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2,
                    cur++).getFxImage();
            if (cur == 4) {
                cur = 1;
            }
            int xUnit = (int) Math.round(x / 32.0);
            int yUnit = (int) Math.round(y / 32.0);
            if (BombermanGame.Map[yUnit + 1][xUnit] == '#' || BombermanGame.Map[yUnit + 1][xUnit] == '*') {
                canMove = false;
            }
            if (canMove) {
                y += 5;
            }

        } else if (event.getCode() == KeyCode.RIGHT) {
            boolean canMove = true;
            img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2,
                    cur++).getFxImage();
            if (cur == 4) {
                cur = 1;
            }
            int xUnit = (int) Math.round(x / 32.0);
            int yUnit = (int) Math.round(y / 32.0);
            if (BombermanGame.Map[yUnit][xUnit + 1] == '#' || BombermanGame.Map[yUnit][xUnit + 1] == '*') {
                canMove = false;
            }
            if (canMove) {
                x += 5;
            }
        } else if (event.getCode() == KeyCode.LEFT) {
            boolean canMove = true;
            img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2,
                    cur++).getFxImage();
            if (cur == 4) {
                cur = 1;
            }
            int xUnit = (int) Math.round(x / 32.0);
            int yUnit = (int) Math.round(y / 32.0);
            if (BombermanGame.Map[yUnit][xUnit - 1] == '#' || BombermanGame.Map[yUnit][xUnit - 1] == '*') {
                canMove = false;
            }
            if (canMove) {
                x -= 3;
            }
        }
    }

    public Bomb dropBomb() {
        Bomb bom = null;
//        if (event.getCode() == KeyCode.SPACE) {

        bom = new Bomb(this.x, this.y, Sprite.bomb.getFxImage());
        bombList.add(bom);
//        }
        return bom;
    }

    @Override
    public void update() {

    }
}
