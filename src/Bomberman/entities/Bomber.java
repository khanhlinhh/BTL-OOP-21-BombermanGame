package Bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import Bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int speed;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        this.speed = 2;
    }

    public void move(KeyEvent event) {
        if (event.getCode() == KeyCode.UP ) {
            super.moveUp(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, speed);
        } else if (event.getCode() == KeyCode.DOWN) {
            super.moveDown(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, speed);
        } else if (event.getCode() == KeyCode.RIGHT ) {
            super.moveRight(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, speed);
        } else if (event.getCode() == KeyCode.LEFT) {
            super.moveLeft(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, speed);
        }
    }

    public Bomb dropBomb() {
        int xUnit = (int) Math.round(x / 32);
        int yUnit = (int) Math.round(y / 32);
        return new Bomb(xUnit, yUnit, Sprite.bomb.getFxImage());
    }

    @Override
    public void update() {

    }
}
