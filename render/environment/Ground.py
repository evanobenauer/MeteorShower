import math
import random

from pygame import Color

from render.Rectangle import RotatedRectangle, Rectangle
from scene.Scene import Scene
from util.Vector import Vector

class Ground(Rectangle):

    def __init__(self, scene: Scene, y: int, rock_count: int):
        super().__init__(scene, Vector(0,scene.window.size.y - y),Vector(scene.window.size.x,y),Color(76, 36, 1, 255))
        self.rock_count = rock_count

        self.rocks: list[RotatedRectangle] = []

        #Generate Rocks
        rand = random.Random()
        for i in range(rock_count):
            size: Vector = Vector(5,5)
            pos: Vector = Vector(rand.randint(0,self.scene.window.size.x),rand.randint(int(self.pos.y + size.y),self.scene.window.size.y))
            rock: RotatedRectangle = RotatedRectangle(scene, pos, size, Color(58, 26, 0,255), math.tau/8)
            self.rocks.append(rock)

    def draw(self):
        super().draw()
        for rock in self.rocks:
            rock.draw()
