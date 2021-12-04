package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Bomb extends Entity {
    private int level = 1;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void update() {

    }
}
