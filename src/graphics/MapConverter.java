package graphics;

/** Đọc kí tự từ file txt để lấy phần tử từ mảng Tile */
public class MapConverter {
  public static int Converter(char c) {
    int tileNum;
    switch (c) {
      case '#':
        tileNum = 1; // wall
        break;
      default:
        tileNum = 2; // grass
        break;
    }
    return tileNum;
  }
}
