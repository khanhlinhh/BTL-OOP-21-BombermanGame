package graphics;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public char mapTile[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTile = new char [gp.maxScreenCol][gp.maxScreenRow]; // ma trận tạo map 2D
        getTileImage();
        loadMap();
    }

    /** Đặt từng phần tử tile. */
    public void getTileImage() {
        try {
            tile[0] = new Tile(); // heartItem
            tile[0].image = ImageIO.read(getClass().getResourceAsStream(Sprite.heart));
            tile[0].isItem = true;

            tile[1] = new Tile(); // bombItem
            tile[1].image = ImageIO.read(getClass().getResourceAsStream(Sprite.bombItem));
            tile[1].isItem = true;

            tile[2] = new Tile(); // SpeedItem
            tile[2].image = ImageIO.read(getClass().getResourceAsStream(Sprite.speedItem));
            tile[2].isItem = true;

            tile[3] = new Tile(); // flameItem
            tile[3].image = ImageIO.read(getClass().getResourceAsStream(Sprite.flameItem));
            tile[3].isItem = true;

            tile[4] = new Tile(); // coin
            tile[4].image = ImageIO.read(getClass().getResourceAsStream(Sprite.coin));
            tile[4].isItem = true;

            tile[5] = new Tile(); // wall
            tile[5].image = ImageIO.read(getClass().getResourceAsStream(Sprite.wall));
            tile[5].collision = true;

            tile[6] = new Tile(); // brick
            tile[6].image = ImageIO.read(getClass().getResourceAsStream(Sprite.brick));
            tile[6].collision = true;

            tile[7] = new Tile(); // grass
            tile[7].image = ImageIO.read(getClass().getResourceAsStream(Sprite.grass));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** In map. */
    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/levels/Level1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    char c = line.charAt(col);
                    mapTile[col][row] = c;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Render ra màn hình. */
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = MapConverter.Converter(mapTile[col][row]);
            g2. drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

    }
}
