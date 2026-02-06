import math

import pygame
from pygame import Color

from src.util.Vector import Vector
from src.scene.Scene import Scene
from src.render.base.Drawable import Drawable

class Rectangle(Drawable):

    def __init__(self, scene: Scene, pos: Vector, size: Vector, color: Color):
        super().__init__(scene, pos)
        self.size = size
        self.color = color

    def draw(self):
        rect_surface = pygame.Surface((self.size.x, self.size.y), pygame.SRCALPHA)
        rect_surface.fill(self.color)
        self.scene.surface.blit(rect_surface, (self.pos.x, self.pos.y))



class RotatedRectangle(Rectangle):

    def __init__(self, scene: Scene, pos: Vector, size: Vector, color: Color, angle: float):
        super().__init__(scene,pos,size,color)
        self.angle = angle

    def draw(self):
        points: (float,float) = []

        radius: float = math.sqrt((self.size.y / 2) ** 2 + (self.size.x / 2) ** 2)
        angle: float = math.atan2(self.size.y / 2, self.size.x / 2)
        angles: list[float] = [angle, -angle + math.pi, angle + math.pi, -angle]

        # Calculate the coordinates of each point.
        for angle in angles:
            y_offset: float = -radius * math.sin(angle + self.angle)
            x_offset: float = radius * math.cos(angle + self.angle)
            points.append((self.pos.x + x_offset + self.size.x / 2, self.pos.y + y_offset + self.size.y / 2))

        pygame.draw.polygon(self.scene.surface, self.color, points)