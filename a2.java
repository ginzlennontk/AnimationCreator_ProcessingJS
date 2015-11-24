Acontroller c;

class Amaker {
  int widthX, heightY;
  int size[][];
  int frame[][][];
  Amaker(int w, int h) {
    widthX = w;
    heightY = h;
    size = new int[heightY][widthX];
    frame = new int[0][heightY][widthX];
  }
  int[][] get_size() {
    return size;
  }
  void set_size(int[][] current) {
    for (int i = 0; i < size.length; i++) {
      for (int j = 0; j < size[i].length; j++) {
        size[i][j] = current[i][j];
      }
    }
  }
  void clearSize() {
    int[][] emptyFrame = new int[heightY][widthX];
    for (int i = 0; i < size.length; i++) {
      for (int j = 0; j < size[i].length; j++) {
        size[i][j] = emptyFrame[i][j];
      }
    }
  }
  int[][][] get_frame() {
    return frame;
  }
  void set_frame(int[][][] current) {
    frame = current;
    for (int i = 0; i < frame.length; i++) {
      for (int j = 0; j < frame[i].length; j++) {
        for (int k = 0; k < frame[i][j].length; k++) {
          frame[i][j][k] = current[i][j][k];
        }
      }
    }
  }
  int totalTile() {
    int count = 0;
    for (int i = 0; i < size.length; i++) {
      for (int j = 0; j < size[i].length; j++) {
        if (size[i][j] == 1) {
          count += 1;
        }
      }
    }
    return count;
  }
  int get_w() {
    return widthX;
  }
  int get_h() {
    return heightY;
  }
}


class Acontroller {
  Amaker m;
  Aviewer v;
  int move = 0;
  int x1;
  int y1;
  int[][] current;
  int run, pause;
  Acontroller(Amaker maker, Aviewer viewer) {
    m = maker;
    v = viewer;
    current = m.get_size();
  }
  void add_tile(int x, int y) { 
    current[y][x] = 1;
    m.set_size(current);
  }
  void remove_tile(int x, int y) { 
    current[y][x] = 0;
    m.set_size(current);
  }
  void move_tile(int x1, int y1, int x2, int y2) {
    current[y2][x2] = 3;
    current[y1][x1] = 0;
    m.set_size(current);
    print("!Moved Frame!");
  }
  void add_frame() {
    int[][] currentF;
    currentF = new int[m.get_h()][m.get_w()];
    for (int i = 0; i < m.get_h(); i++) {
      for (int j = 0; j < m.get_size()[i].length; j++) {
        currentF[i][j] = m.get_size()[i][j];
      }
    }
    int[][][]addedFrame = (int[][][])append(m.get_frame(), currentF);
    m.set_frame(addedFrame);
    println("!Add Frame!");
  }
  void remove_frame() {
    int[][][] totalFrame = m.get_frame();
    totalFrame = (int[][][])shorten(totalFrame);
    m.set_frame(totalFrame);
    if (m.get_frame().length > 0) {
      m.set_size(m.get_frame()[m.get_frame().length-1]);
    } else {
      m.clearSize();
    }
    println("!Remove Frame!");
  }
  void remove_allFrame() {
    int[][][] emptyFrame = new int[0][m.get_h()][m.get_w()];
    m.set_frame(emptyFrame);
    m.clearSize();
    move = 0;
    println("!Remove All Frame!");
  }
  void reset_frame() {
    if (m.get_frame().length > 0) {
      m.set_size(m.get_frame()[m.get_frame().length-1]);
    } else {
      m.clearSize();
    }
    move = 0;
  }
  void leftAll() {
    for (int i = 0; i < m.get_size().length; i++) {
      remove_tile(0, i);
    }
    for (int i = 0; i < m.get_size()[0].length-1; i++) {
      for (int j = 0; j < m.get_size().length; j++) {
        if (m.get_size()[j][i+1] == 1) {
          move_tile(i+1, j, i, j);
        }
      }
    }
  }
  void rightAll() {
    for (int i = 0; i < m.get_size().length; i++) {
      remove_tile(m.get_size()[i].length-1, i);
    }
    for (int i = m.get_size()[0].length-1; i > 0; i--) {
      for (int j = 0; j < m.get_size().length; j++) {
        if (m.get_size()[j][i-1] == 1) {
          move_tile(i-1, j, i, j);
        }
      }
    }
  }
  void upAll() {
    for (int i = 0; i < m.get_size()[0].length; i++) {
      remove_tile(i, 0);
    }
    for (int i = 0; i < m.get_size().length-1; i++) {
      for (int j = 0; j < m.get_size()[i].length; j++) {
        if (m.get_size()[i+1][j] == 1) {
          move_tile(j, i+1, j, i);
        }
      }
    }
  }
  void downAll() {
    for (int i = 0; i < m.get_size()[0].length; i++) {
      remove_tile(i, m.get_size().length-1);
    }
    for (int i = m.get_size().length-1; i > 0; i--) {
      for (int j = 0; j < m.get_size()[i].length; j++) {
        if (m.get_size()[i-1][j] == 1) {
          move_tile(j, i-1, j, i);
        }
      }
    }
  }
  void addAll() {
    for (int i = 0; i < m.get_size().length; i++) {
      for (int j = 0; j < m.get_size()[i].length; j++) {
        current[i][j] = 1;
      }
    }
    m.set_size(current);
  }
  void removeAll() {
    for (int i = 0; i < m.get_size().length; i++) {
      for (int j = 0; j < m.get_size()[i].length; j++) {
        current[i][j] = 0;
      }
    }
    m.set_size(current);
  }
  void tileDisplay() {
    v.display();
    textAlign(LEFT, CENTER);
    fill(0);
    text("Total Tile(s) : "+m.totalTile(), 20, 320);
    text("Total Frame(s) : "+m.get_frame().length, 390, 320);
  }
  void controlDisplay() { 
    controlButton(20, 10, 90, 30, 40, #008800, "Add Frame", 255);
    controlButton(130, 10, 90, 30, 40, #FF8800, "Reset Frame", 255);
    controlButton(240, 10, 100, 30, 40, #FF0000, "Remove Frame", 255);
    controlButton(360, 10, 130, 30, 40, #FF6666, "Remove All Frame !!!", 255);
    controlButton(190, 350, 40, 25, 30, #008800, "Play", 255);
    controlButton(235, 350, 40, 25, 30, #FF8800, "Pause", 255);
    controlButton(280, 350, 40, 25, 30, #FF0000, "Stop", 255);
    controlButton(500, 50, 130, 20, 20, 255, "All Tiles", #66BB11);
    controlButton(500, 80, 60, 30, 15, #FF5522, "Left", 255);
    controlButton(570, 80, 60, 30, 15, #77BB00, "Right", 255);
    controlButton(500, 120, 60, 30, 15, #00AAEE, "Up", 255);
    controlButton(570, 120, 60, 30, 15, #FFBB00, "Down", 255);
    controlButton(500, 160, 130, 30, 15, #223399, "Add All", 255);
    controlButton(500, 200, 130, 30, 15, #BB0055, "Remove All", 255);
    controlButton(500, 250, 130, 20, 20, 255, "Tile Color", #66BB11);
    controlButton(500, 280, 25, 25, 0, 0, "", 0);
    controlButton(535, 280, 25, 25, 0, #FF5500, "", 0);
    controlButton(570, 280, 25, 25, 0, #BBDD00, "", 0);
    controlButton(605, 280, 25, 25, 0, #0099CC, "", 0);
  }
  void playA() {
    v.playDisplay();
    v.setFrame(1);
  }
  int get_run() {
    return run;
  }
  void clicked() {
    for (int i = 0; i<m.get_size().length; i++) {
      for (int j = 0; j<m.get_size()[i].length; j++) {
        if (mouseButton == LEFT && m.get_size()[i][j] == 0 && run != 1 && pause != 1 && move != 1 && buttonPosition((j*26)+v.get_viewX(), (i*26)+v.get_viewY(), 25, 25)) {
          add_tile(j, i);
        } else if (mouseButton == RIGHT && buttonPosition(j*26+v.get_viewX(), i*26+v.get_viewY(), 25, 25)  && run != 1 && pause != 1 ) {
          remove_tile(j, i);
          for (int n = 0; n < m.get_size().length; n++) {
            for (int l = 0; l < m.get_size()[i].length; l++) {
              if (current[n][l] == 2) {
                remove_tile(l, n);
                move = 0;
              }
            }
          }
        } else if (mouseButton == LEFT && move == 0 && run != 1 && pause != 1 && m.get_size()[i][j] == 1 &&  buttonPosition(j*26+v.get_viewX(), i*26+v.get_viewY(), 25, 25)) {
          x1 = j;
          y1 = i;
          if (j-1 >= 0 && current[i][j-1] == 0) {
            current[i][j-1] = 2;
          }
          if (j+1 <= current[0].length-1 && current[i][j+1] == 0) {
            current[i][j+1] = 2;
          }
          if (i-1 >= 0 && current[i-1][j] == 0) {
            current[i-1][j] = 2;
          }
          if (i+1 <= current.length-1 && current[i+1][j] == 0) {
            current[i+1][j] = 2;
          }
          m.set_size(current);
          move = 1;
        } else if (mouseButton == LEFT && move == 1  && run != 1 && pause != 1 && buttonPosition(j*26+v.get_viewX(), i*26+v.get_viewY(), 25, 25) && i == y1 && j == x1) {
          for (int n = 0; n < m.get_size().length; n++) {
            for (int l = 0; l < m.get_size()[i].length; l++) {
              if (current[n][l] == 2) {
                remove_tile(l, n);
                move = 0;
              }
            }
          }
          m.set_size(current);
        } else if (mouseButton == LEFT && move == 1  && run != 1 && pause != 1 && m.get_size()[i][j] == 1 &&  buttonPosition(j*26+v.get_viewX(), i*26+v.get_viewY(), 25, 25)) {
          x1 = j;
          y1 = i;
          for (int n = 0; n < m.get_size().length; n++) {
            for (int l = 0; l < m.get_size()[i].length; l++) {
              if (current[n][l] == 2) {
                remove_tile(l, n);
                move = 0;
              }
            }
          }
          if (j-1 >= 0 && current[i][j-1] == 0) {
            current[i][j-1] = 2;
          }
          if (j+1 <= current[0].length-1 && current[i][j+1] == 0) {
            current[i][j+1] = 2;
          }
          if (i-1 >= 0 && current[i-1][j] == 0) {
            current[i-1][j] = 2;
          }
          if (i+1 <= current.length-1 && current[i+1][j] == 0) {
            current[i+1][j] = 2;
          }
          m.set_size(current);
          move = 1;
        } else if (mouseButton == LEFT && move == 1  && run != 1 && pause != 1 && m.get_size()[i][j] == 2 && buttonPosition(j*26+v.get_viewX(), i*26+v.get_viewY(), 25, 25)) {
          move_tile(x1, y1, j, i);
          for (int n = 0; n < m.get_size().length; n++) {
            for (int l = 0; l < m.get_size()[i].length; l++) {
              if (current[n][l] == 2) {
                remove_tile(l, n);
                move = 0;
              }
            }
          }
        }
      }
    }
    if (buttonPosition(20, 10, 90, 30) && run != 1 && pause != 1) {
      for (int n = 0; n < m.get_size().length; n++) {
        for (int l = 0; l < m.get_size()[n].length; l++) {
          if (current[n][l] == 2) {
            remove_tile(l, n);
            move = 0;
          }
          if (current[n][l] == 3) {
            add_tile(l, n);
          }
        }
      }
      add_frame();
    } else if (buttonPosition(130, 10, 90, 30) && run != 1 && pause != 1) {
      reset_frame();
    } else if (buttonPosition(240, 10, 100, 30) && run != 1  && pause != 1 && m.get_frame().length > 0) {
      remove_frame();
    } else if (buttonPosition(360, 10, 130, 30) && run != 1  && pause != 1 && m.get_frame().length > 0) {
      remove_allFrame();
    } else if (buttonPosition(500, 80, 60, 30) && run != 1  && pause != 1) {
      leftAll();
    } else if (buttonPosition(570, 80, 60, 30) && run != 1  && pause != 1) {
      rightAll();
    } else if (buttonPosition(500, 120, 60, 30) && run != 1  && pause != 1) {
      upAll();
    } else if (buttonPosition(570, 120, 60, 30) && run != 1  && pause != 1) {
      downAll();
    } else if (buttonPosition(500, 160, 130, 30) && run != 1 && pause != 1) {
      addAll();
    } else if (buttonPosition(500, 200, 130, 30) && run != 1 && pause != 1) {
      removeAll();
    } else if (buttonPosition(500, 280, 25, 25)) {
      v.set_colorTile(0);
    } else if (buttonPosition(535, 280, 25, 25)) {
      v.set_colorTile(1);
    } else if (buttonPosition(570, 280, 25, 25)) {
      v.set_colorTile(2);
    } else if (buttonPosition(605, 280, 25, 25)) {
      v.set_colorTile(3);
    } else if (buttonPosition(190, 350, 40, 25) && m.get_frame().length > 0) {
      run = 1;
      pause = 0;
    } else if (run == 1 && buttonPosition(235, 350, 40, 25)) {
      run = 0;
      pause = 1;
    } else if ((run == 1 || pause == 1) && buttonPosition(280, 350, 40, 25)) {
      run = 0;
      pause = 0;
      v.resetFrame();
      m.set_size(m.get_frame()[m.get_frame().length-1]);
    }
  }
}

class Aviewer {
  Amaker m;
  int x, y;
  int frameNum, colorSelect;
  Aviewer(Amaker maker) {
    m = maker;
    x = 20;
    y = 50;
    colorSelect = 0;
  }
  void display() {
    int tileSize = 25;
    int posY = y;
    int tileColor[] = {0, #FF5500, #BBDD00, #0099CC};
    for (int i = 0; i < m.get_size().length; i++) {  
      int posX = x;
      for (int j = 0; j < m.get_size()[i].length; j++) {
        if (m.get_size()[i][j] == 0) {
          fill(#FFFFFF);
        } else if (m.get_size()[i][j] == 1) {
          fill(tileColor[colorSelect]);
        } else if (m.get_size()[i][j] == 2) {
          fill(#33FF33);
        } else if (m.get_size()[i][j] == 3) {
          fill(#FF0000);
        }
        rect(posX, posY, tileSize, tileSize);
        posX += tileSize+1;
      }
      posY += tileSize+1;
    }
  }

  int get_viewX() {
    return x;
  }
  int get_viewY() {
    return y;
  }
  void set_colorTile(int select){
    colorSelect = select;
  }
  void resetFrame() {
    frameNum = 0;
  }
  void setFrame(int value) {
    frameNum += value;
  }
  void playDisplay() {
    if (frameNum >= m.get_frame().length) {
      resetFrame();
    }
    if (frameNum < m.get_frame().length) {
      m.set_size(m.get_frame()[frameNum]);
      display();
    }
    fill(200);
    text("Frame : "+frameNum, 500, 0);
  }
}
/*************************************************** main *******************************************************/
void setup() {
  size(650, 400); 
  Amaker m = new Amaker(18, 10); 
  Aviewer v = new Aviewer(m); 
  c = new Acontroller(m, v);
}
void draw() {
  background(#DDEEFF);
  noStroke();
  c.controlDisplay(); 
  if (c.get_run() == 0) {
    frameRate(30);
    c.tileDisplay();
  } else { 
    frameRate(3);
    c.playA();
  }
}

void mousePressed() {
  c.clicked();
}

void controlButton(int x, int y, int buttonWidth, int buttonHeight, int corner, int buttonColor, String textButton, int textColor) {
  fill(buttonColor);
  rect(x, y, buttonWidth, buttonHeight, corner);
  fill(textColor);
  textAlign(CENTER, CENTER);
  text(textButton, x+(buttonWidth/2), y+(buttonHeight/2.5));
}

boolean buttonPosition(int x, int y, int buttonWidth, int buttonHeight) {
  if (mouseX >= x && mouseX <= x+buttonWidth && mouseY >= y && mouseY <= y+buttonHeight) {
    return true;
  } else {
    return false;
  }
}