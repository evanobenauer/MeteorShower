from abc import ABC

import pygame
from pygame import Surface

from render.base.Interactable import Interactable
from util.Vector import Vector
from Window import Window
from render.base.Drawable import Drawable

#A scene is a container for a PyGame surface. Items can be drawn to said surface
class Scene(ABC):

    def __init__(self, window: Window, name: str):
        super().__init__()
        self.window = window
        self.name = name

        self.surface: Surface = window.base_surface

        self.drawables: list[Drawable] = []
        self.mouse_pos: Vector = Vector(-1, -1)

    def draw(self):
        for element in self.drawables:
            if isinstance(element, Interactable):
                element.update_mouse_over(self.mouse_pos)
            element.draw()

    def on_key_press(self, key: int, state: int):
        for element in self.drawables:
            if isinstance(element, Interactable):
                element.on_key_press(key, state)

    def on_mouse_press(self, button: int, state: int, mouse_pos: Vector):
        for element in self.drawables:
            if isinstance(element, Interactable):
                element.on_mouse_press(button, state, mouse_pos)