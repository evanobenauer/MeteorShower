import math

import pygame
from pygame import Color, Surface
from pygame.font import Font

from util.Vector import Vector
from scene.Scene import Scene

from render.base.Drawable import Drawable


class Text(Drawable):

    def __init__(self, scene: Scene, pos: Vector, text: str, font: str, size: int, color: Color):
        super().__init__(scene, pos)
        self.text = text
        self.size = size
        self.color = color

        self.font: Font = pygame.font.SysFont(font, size)

    def draw(self):
        if self.text == "":
            return
        text_surface: Surface = self.font.render(self.text, True, self.color)
        self.scene.surface.blit(text_surface, (self.pos.x, self.pos.y))

    def get_vector_size(self) -> Vector:
        return Vector(self.font.size(self.text)[0],self.font.size(self.text)[1])

    def update_font(self,font: str, size: int):
        self.font = pygame.font.SysFont(font, size)


class TextBounceHandler:

    def __init__(self, text : Text, pos: Vector):
        self.text = text
        self.pos = pos
        self.angle = 0
        pass


    def update_pos(self):
        start_pos: Vector = Vector(self.pos.x, self.pos.y)
        self.text.pos = start_pos + Vector(0, math.cos(self.angle) * 8)
        self.angle += 0.05
        if self.angle > math.tau:
            self.angle = 0