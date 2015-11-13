class Amaker {
  int widthX, heightY;
  String size[][];
  int frameNum;
  String frame[][][];
  Amaker(int w, int h) {
    widthX = w;
    heightY = h;
    size = new String[heightY][widthX];
    frameNum = 0;
    frame = new String[100][heightY][widthX];
  }
  int get_w() {
    return widthX;
  }
  int get_h() {
    return heightY;
  }
  String[][] get_size() {
    return size;
  }
  String[][][] get_frame() {
    return frame;
  }
  int get_frameNum() {
    return frameNum;
  }
  void setFrame(int set) {
    frameNum+=set;
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
  void add_frame() {
    a.setFrame(1);
    for (int i = 0; i<a.get_size().length; i++) {
      for (int j = 0; j<a.get_size()[i].length; j++) {
        a.get_frame()[a.get_frameNum()-1][i][j] = a.get_size()[i][j];
      }
    }
    println("!Add Frame!");
  }
  void remove_frame() {
    a.setFrame(-1); 
    println("!Remove Frame!");
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
    println("------------------");
  }
  void display_all_frame() {
    println("\nDisplay All Frame");
    for (int k = 0; k<a.get_frameNum(); k++) {
      for (int i = 0; i<a.get_size().length; i++) {
        for (int j = 0; j<a.get_size()[i].length; j++) {
          if (a.get_frame()[k][i][j] == null) {
            print(" ");
          } else {
            print(a.get_frame()[k][i][j]);
          }
        }
        println();
      }
      println("------------------");
    }
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
  b.add_frame(); 

  b.remove_tile(10, 1); 
  c.display_current_frame(); 
  b.add_frame(); 

  b.move_tile(6, 1, 6, 2); 
  c.display_current_frame(); 
  b.add_frame(); 
  c.display_all_frame(); 
  b.remove_frame(); 
  c.display_all_frame();
}