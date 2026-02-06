from abc import ABC, abstractmethod

from src.util.Vector import Vector

class Drawable(ABC):

    def __init__(self, scene: 'Scene', pos: Vector):
        self.scene = scene
        self.pos = pos
        self.mouse_over = False
        pass

    @abstractmethod
    def draw(self):
        pass

