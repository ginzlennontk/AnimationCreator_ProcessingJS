Amaker a = new Amaker(18, 10);
int x1;
int y1;
int move = 0;
class Amaker {
  int widthX, heightY;
  int size[][];
  int frameNum;
  int frame[][][];
  Amaker(int w, int h) {
    widthX = w;
    heightY = h;
    size = new int[heightY][widthX];
    frameNum = 0;
    frame = new int[100][heightY][widthX];
  }
  int[][] get_size() {
    return size;
  }
  int[][][] get_frame() {
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
    a.get_size()[y][x] = 1;
  }
  void remove_tile(int x, int y) {
    a.get_size()[y][x] = 0;
  }
  void move_tile(int x1, int y1, int x2, int y2) {
    a.get_size()[y1][x1] = 0;
    a.get_size()[y2][x2] = 1;
    print("!Moved Frame!");
  }
  void add_frame() {
    a.setFrame(1);
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
    int y = 0;
    for (int i = 0; i<a.get_size().length; i++) {
      int x = 0;
      for (int j = 0; j<a.get_size()[i].length; j++) {
        if (a.get_size()[i][j] == 0) {
          fill(#FFFFFF);
        } else {
          fill(#FF0000);
        }
        rect(x, y, 30, 30);
        x+=31;
      }
      y+=31;
    }
  }
  void display_all_frame() {
    println("\nDisplay All Frame");
    for (int k = 0; k<a.get_frameNum(); k++) {
      for (int i = 0; i<a.get_size().length; i++) {
        for (int j = 0; j<a.get_size()[i].length; j++) {
          print(a.get_frame()[k][i][j]);
        }
        println();
      }
      println("------------------");
    }
  }
}
void setup() {
  size(558, 310);
}
void draw() {
  Aviewer c = new Aviewer(a);
  c.display_current_frame();
}
void mouseClicked() {
  Acontroller b = new Acontroller(a);
  
  for (int i = 0; i<a.get_size().length; i++) {
    for (int j = 0; j<a.get_size()[i].length; j++) {
      if (mouseButton == LEFT && a.get_size()[i][j] != 1 && move != 1 && buttonPosition(j*31, i*31, 30, 30)) {
        b.add_tile(j, i);
      } else if (mouseButton == RIGHT && buttonPosition(j*31, i*31, 30, 30)) {
        b.remove_tile(j, i);
        move = 0;
      } else if (mouseButton == LEFT && move == 0 && a.get_size()[i][j] == 1 &&  buttonPosition(j*31, i*31, 30, 30)) {
        move = 1;
        x1 = j;
        y1 = i;
      } else if (mouseButton == LEFT && move == 1 && buttonPosition(j*31, i*31, 30, 30)) {
        b.move_tile(x1, y1, j, i);
        move = 0;
      }
    }
  }
}
boolean buttonPosition(int x, int y, int buttonWidth, int buttonHeight) {
  if (mouseX >= x && mouseX <= x+buttonWidth && mouseY >= y && mouseY <= y+buttonHeight) {
    return true;
  } else {
    return false;
  }
}
