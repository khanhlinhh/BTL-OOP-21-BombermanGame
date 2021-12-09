package Bomberman.entities.Enemy;

import javafx.scene.image.Image;
import Bomberman.entities.Entity;
import Bomberman.graphics.Sprite;

public class Balloon extends Entity {
    int move = 1;

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void move(){
        if (move == 1) {
            if (!super.moveLeft(Sprite.balloon_left1, Sprite.balloon_left1, Sprite.balloon_left1, 0.5))
                move = (int) (Math.random() * 4 + 1);
        }
        if (move == 2) {
            if (!super.moveRight(Sprite.balloon_right1, Sprite.balloon_right1, Sprite.balloon_right1, 0.5))
                move = (int) (Math.random() * 4 + 1);
        }
        if (move == 3) {
            if (!super.moveUp(Sprite.balloon_left2, Sprite.balloon_left2, Sprite.balloon_left2, 0.5))
                move = (int) (Math.random() * 4 + 1);
        }
        if (move == 4) {
            if (!super.moveDown(Sprite.balloon_right2, Sprite.balloon_right2, Sprite.balloon_right2, 0.5))
                move = (int) (Math.random() * 4 + 1);
        }
    }

    @Override
    public void update() {
        move();
    }
}
