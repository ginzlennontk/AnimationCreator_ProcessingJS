class Amaker {
  int widthX, heightY;
  String size[][];
  Amaker(int w, int h) {
    widthX = w;
    heightY = h;
    size = new String[heightY][widthX];
  }
  String[][] get_size() {
    return size;
  }
}
class Acontroller {
  Amaker a;
  Acontroller(Amaker b) {
    a = b;
  }
  void add_tile(int x, int y) {
    a.get_size()[y][x] = "#";
  }
  void remove_tile(int x, int y) {
    a.get_size()[y][x] = " ";
  }
  void move_tile(int x1, int y1, int x2, int y2) {
    a.get_size()[y1][x1] = " ";
    a.get_size()[y2][x2] = "#";
  }
}
class Aviewer {
  Amaker a;
  Aviewer(Amaker b) {
    a = b;
  }
  void display_current_frame() {
    for (int i = 0; i<a.get_size().length; i++) {
      for (int j = 0; j<a.get_size()[i].length; j++) {
        if (a.get_size()[i][j] == null) {
          print(" ");
        } else {
          print(a.get_size()[i][j]);
        }
      }
      println();
    }
    print("------------------");
  }
}
void setup() {
  Amaker a = new Amaker(18, 10);
  Acontroller b = new Acontroller(a);
  Aviewer c = new Aviewer(a);
  
  b.add_tile(4, 1);
  b.add_tile(6, 1);
  b.add_tile(10, 1);
  b.add_tile(4, 3);
  c.display_current_frame();
  //a.add_frame()

  b.remove_tile(10, 1);
  c.display_current_frame();
  //a.add_frame();

  b.move_tile(6, 1, 6, 2);
  c.display_current_frame();
  //a.add_frame();

  //a.remove_frame();
}