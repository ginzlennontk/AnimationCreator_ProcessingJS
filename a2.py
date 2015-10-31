class Amaker:
    def __init__(self, width, height): # width/height are number of slots (18x10)
        self.width = width
        self.height = height
        self.size = []
        for i in range(0,self.height):
            self.size.append([" "]*width)
        
    def add_tile(self, x, y): # add tile to the current frame at (x,y)
        self.size[y][x] = "#"
        
    def remove_tile(self, x, y): # from the current frame
        self.size[y][x] = " "
        
    def move_tile(self, x1, y1, x2, y2):
        self.size[y1][x1] = " "
        self.size[y2][x2] = "#"
    
    def add_frame(self):  # add a frame after current frame
        pass

    def remove_frame(self): # remove current frame (must be the last frame?)
        pass

    def get_total_number_of_frames(self):
        pass

    def display_current_frame(self):  # print out the frame in text-mode
        for i in range(0,len(self.size)):
            for j in range(0,len(self.size[i])):
                print(self.size[i][j],end = "")
            print()
        print("------------------")
    def get_total_number_of_tiles(self):
        pass



def main():
    a = Amaker(18, 10)

    a.add_tile(4, 1)
    a.add_tile(6, 1)
    a.add_tile(10, 1)
    a.add_tile(4, 3)

    a.display_current_frame()
    a.remove_tile(10, 1)

    a.display_current_frame()

    a.add_frame()

    a.move_tile(6, 1, 6, 2)

    a.display_current_frame()

    print('Exit')


if __name__ == "__main__":
    main()
