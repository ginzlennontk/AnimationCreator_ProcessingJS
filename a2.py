class Amaker:
    def __init__(self, width, height): # width/height are number of slots (18x10)
        self.width = " "*width
        self.height = height
        self.size = []
        for i in range(0,self.height):
            self.size.append(self.width)
    def add_tile(self, x, y): # add tile to the current frame at (x,y)
        a = ""
        for i in range(0,len(self.width)):
            if(i == x):
                a += "#"
            else:
                a += self.size[y][i]
        self.size[y] = a
    def remove_tile(self, x, y): # from the current frame
        a = ""
        for i in range(0,len(self.width)):
            if(i == x):
                a += " "
            else:
                a += self.size[y][i]
        self.size[y] = a

    def move_tile(self, x1, y1, x2, y2):
        pass

    def add_frame(self):  # add a frame after current frame
        pass

    def remove_frame(self): # remove current frame (must be the last frame?)
        pass

    def get_total_number_of_frames(self):
        pass

    def display_current_frame(self):  # print out the frame in text-mode
        for i in range(0,len(self.size)):
            print(self.size[i])
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

    #a.move_tile(6, 1, 6, 2)

    #a.display_current_frame()

    print('Exit')


if __name__ == "__main__":
    main()