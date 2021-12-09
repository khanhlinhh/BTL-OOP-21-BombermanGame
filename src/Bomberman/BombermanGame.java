package Bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import Bomberman.entities.*;
import Bomberman.entities.Enemy.Balloon;
import Bomberman.entities.Enemy.Oneal;
import Bomberman.entities.Map.Brick;
import Bomberman.entities.Map.Flame;
import Bomberman.entities.Map.Grass;
import Bomberman.entities.Map.Wall;
import Bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static int WIDTH = 31;
    public static int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static boolean[][] Map = new boolean[HEIGHT][WIDTH];

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        Bomber bomberman1 = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman1);

        scene.setOnKeyPressed(KeyEvent -> {
            bomberman1.move(KeyEvent);
            if (KeyEvent.getCode() == KeyCode.SPACE) {
                Bomb bomb = bomberman1.dropBomb();
                entities.add(bomb);
            }
        });
    }

    public void createMap() {
        File file = new File("./res/levels/Level1.txt");
        try {
            Scanner sc = new Scanner(file);
            for (int i = 0; i < HEIGHT; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    Entity object;
                    char c = line.charAt(j);
                    switch (c) {
                        case '#':
                            object = new Wall(j, i, Sprite.wall.getFxImage());
                            stillObjects.add(object);
                            Map[i][j] = true;
                            break;
                        case '*':
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);

                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            entities.add(object);
                            Map[i][j] = true;
                            break;
                        case '1':
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);

                            object = new Balloon(j, i, Sprite.balloon_left2.getFxImage());
                            entities.add(object);
                            break;
                        case '2':
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);

                            object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                            entities.add(object);
                            break;
                        default:
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateAfterExploded() {
        Entity flame = null;
        for (Entity e : entities) {
            if (e instanceof Bomb && ((Bomb) e).isExploded()) {
                int xUnit = (int) e.x / 32;
                int yUnit = (int) e.y / 32;
                flame = new Flame(xUnit, yUnit, Sprite.bomb_exploded1.getFxImage());
                entities.add(flame);
                entities.remove(e);
                if (Map[yUnit + 1][xUnit]) {
                    entities.removeIf(br -> br.x == xUnit * 32 && br.y == (yUnit + 1) * 32);
                    Map[yUnit + 1][xUnit] = false;
                }
                if (Map[yUnit - 1][xUnit]) {
                    entities.removeIf(br -> br.x == xUnit * 32 && br.y == (yUnit - 1) * 32);
                    Map[yUnit - 1][xUnit] = false;
                }
                if (Map[yUnit][xUnit + 1]) {
                    entities.removeIf(br -> br.x == (xUnit + 1) * 32 && br.y == yUnit * 32);
                    Map[yUnit][xUnit + 1] = false;
                }
                if (Map[yUnit][xUnit - 1]) {
                    entities.removeIf(br -> br.x == (xUnit - 1) * 32 && br.y == yUnit * 32);
                    Map[yUnit][xUnit - 1] = false;
                }
                break;
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        updateAfterExploded();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
