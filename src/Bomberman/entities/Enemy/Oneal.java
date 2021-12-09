package Bomberman.entities.Enemy;

import javafx.scene.image.Image;
import Bomberman.entities.Entity;
import Bomberman.graphics.Sprite;

public class Oneal extends Entity {
    int move = 1;

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (move == 1) {
            if (!super.moveLeft(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, 1))
                move = (int) (Math.random() * 4 + 1);
        }
        if (move == 2) {
            if (!super.moveRight(Sprite.oneal_right1, Sprite.oneal_right1, Sprite.oneal_right1, 1))
                move = (int) (Math.random() * 4 + 1);
        }

        if (move == 3) {
            if (!super.moveUp(Sprite.oneal_left2, Sprite.oneal_left2, Sprite.oneal_left2, 1))
                move = (int) (Math.random() * 4 + 1);
        }

        if (move == 4) {
            if (!super.moveDown(Sprite.oneal_right2, Sprite.oneal_right2, Sprite.oneal_right2, 1))
                move = (int) (Math.random() * 4 + 1);
        }
    }

}
