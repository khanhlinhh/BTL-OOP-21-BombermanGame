package graphics;

/** Đọc kí tự từ file txt để lấy phần tử từ mảng Tile */
public class MapConverter {
    public static int Converter(char c) {
        int tileNum;
        switch (c) {
            case 'h':
                tileNum = 0; // heartItem
                break;
            case 'b':
                tileNum = 1; // bombItem
                break;
            case 's':
                tileNum = 2; // speedItem
                break;
            case 'f':
                tileNum = 3; // flameItem
                break;
            case 'c':
                tileNum = 4; // coin
                break;
            case '#':
                tileNum = 5; // wall
                break;
            case '*':
                tileNum = 6; // brick
                break;
            default:
                tileNum = 7; // grass
                break;
        }
        return tileNum;
    }
}
