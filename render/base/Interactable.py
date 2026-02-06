from abc import ABC, abstractmethod

from util.Vector import Vector

class Interactable(ABC):

    def __init__(self, scene: 'Scene'):
        super().__init__()
        self.scene = scene
        self.mouse_over: bool = False

    @abstractmethod
    def get_mouse_hovered_calculation(self, mouse_pos: Vector) -> bool:
        pass

    @abstractmethod
    def on_key_press(self, key: int, state: int):
        pass

    @abstractmethod
    def on_mouse_press(self, button: int, state: int, mouse_pos: Vector):
        pass

    #This method must be called every frame in order to get an accurate mouse position
    def update_mouse_over(self, mouse_pos: Vector):
        self.mouse_over = self.get_mouse_hovered_calculation(mouse_pos)