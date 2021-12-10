package item;

import graphics.Sprite;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Bomb extends Item {
    public Bomb() {
        name = "Bomb";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(Sprite.bomb));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
