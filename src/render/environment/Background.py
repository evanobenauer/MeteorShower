import math
import random

from pygame import Color

from src.render.base.Drawable import Drawable
from src.render.Rectangle import Rectangle, RotatedRectangle
from src.scene.Scene import Scene
from src.util.Vector import Vector

class Background(Drawable):

    def __init__(self, scene: Scene, window: 'Window', star_count: int, streak_count: int):
        super().__init__(scene, Vector.null())
        self.star_count = star_count
        self.streak_count = streak_count

        self.stars: list[Rectangle] = []
        self.streaks: list[Rectangle] = []

        #Generate Stars
        rand = random.Random()
        for i in range(star_count):
            pos: Vector = Vector(rand.randint(0,window.size.x),rand.randint(0,window.size.y))
            size: Vector = Vector(1,1)
            star: RotatedRectangle = RotatedRectangle(scene, pos, size, Color(255, 255, 255), 0)
            self.stars.append(star)

        #Generate Streaks
        for i in range(streak_count):
            pos: Vector = Vector(rand.randint(0,window.size.x),rand.randint(0,window.size.y))
            size: Vector = Vector(30,1)
            star: RotatedRectangle = RotatedRectangle(scene, pos, size, Color(255, 255, 255), math.tau / 8)
            self.streaks.append(star)
            self.streaks.append(star)

    def draw(self):
        #Draw Sky
        background: Rectangle = Rectangle(self.scene,Vector(0,0),self.scene.window.size,Color(0,0,0))
        background.draw()

        #Draw Stars
        for star in self.stars:
            star.draw()

        #Draw Streaks
        for streak in self.streaks:
            streak.draw()
