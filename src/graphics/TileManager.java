package graphics;

import entities.Entity;
import entities.enemies.Balloon;
import entities.enemies.Kondoria;
import entities.enemies.Oneal;
import item.Item;
import item.Portal;
import item.powerup.BombItem;
import item.powerup.Coin;
import item.powerup.FlameItem;
import item.powerup.SpeedItem;
import item.wall.Brick;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {
    public Tile[] tile;
    public char mapTile[][];
    private GamePanel gp;
    private ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Entity> entities = new ArrayList<>();

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTile = new char[gp.maxScreenCol][gp.maxScreenRow]; // ma trận tạo map 2D
        getTileImage();
        loadMap();
    }

    /** Đặt từng phần tử tile. */
    public void getTileImage() {
        try {
            tile[0] = new Tile(); // đá
            tile[0].image = ImageIO.read(getClass().getResourceAsStream(Sprite.brick));
            tile[0].collision = true;
            tile[1] = new Tile(); // tường
            tile[1].image = ImageIO.read(getClass().getResourceAsStream(Sprite.wall));
            tile[1].collision = true;
            tile[2] = new Tile(); // cỏ
            tile[2].image = ImageIO.read(getClass().getResourceAsStream(Sprite.grass));
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
                    switch(c) {
                        case Sprite.speedItemChar ->{
                            items.add(new SpeedItem(gp, col, row));
                            items.add(new Brick(gp, col, row));
                        }
                        case Sprite.bombItemChar ->{
                            items.add(new BombItem(gp, col, row));
                            items.add(new Brick(gp, col, row));
                        }
                        case Sprite.flameItemChar ->{
                            items.add(new FlameItem(gp, col, row));
                            items.add(new Brick(gp, col, row));

                        }
                        case Sprite.brickChar -> {
                            items.add(new Brick(gp, col, row));
                        }
                        case Sprite.coinchar ->{
                            items.add(new Coin(gp, col,row));
                            items.add(new Brick(gp, col, row));
                        }

                        case Sprite.portalChar -> {
                            items.add(new Portal(gp, col, row));
                            items.add(new Brick(gp, col, row));
                        }
                        case Sprite.balloonChar -> entities.add(new Balloon(gp, col, row));
                        case Sprite.onealChar -> entities.add(new Oneal(gp, col, row));
                        case Sprite.kondoriaChar -> entities.add(new Kondoria(gp,col,row));
                    }
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

    public boolean checkVictory(){
        int count = 0;
        for (Entity e : entities){
            if(e instanceof Oneal || e instanceof  Balloon || e instanceof  Kondoria)  count ++;
        }
        return count == 0;
    }

    /** Render ra màn hình. */
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = MapConverter.Converter(mapTile[col][row]);
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}