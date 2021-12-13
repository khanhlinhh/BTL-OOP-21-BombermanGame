package item;

import graphics.Sprite;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Bomb extends Item {
    public static int numOfBomb = 1;
    public static int flame = 1;

    public Bomb() {
        name = "Bomb";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(Sprite.bomb));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
