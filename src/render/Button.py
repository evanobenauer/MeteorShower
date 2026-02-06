from pygame import Color

from src.render.Rectangle import Rectangle
from src.render.Text import Text
from src.render.base.Interactable import Interactable
from src.util.Vector import Vector
from src.scene.Scene import Scene

class Button(Rectangle, Interactable):

    def __init__(self, scene: Scene, pos: Vector, size: Vector, color: Color, text: str, action):
        Rectangle.__init__(self,scene, pos, size, color)
        Interactable.__init__(self,scene)
        self.size = size
        self.color = color
        self.text = text
        self.action = action

        self.pressing: bool = False

    def draw(self):
        super().draw()

        #Draw Title
        text: Text = Text(self.scene, self.pos, self.text,"Arial Black",int(self.size.y * .5), Color(255,255,255))
        text.pos = self.size * 0.5 - (text.get_vector_size() * 0.5) + self.pos
        text.draw()

        #Draw Hover Highlight
        if self.mouse_over:
            highlight: Rectangle = Rectangle(self.scene, self.pos, self.size, Color(255,255,255,50))
            highlight.draw()

    # =========================

    # Interactable Methods

    # =========================

    def get_mouse_hovered_calculation(self, mouse_pos: Vector):
        x: bool = self.pos.x <= mouse_pos.x <= self.pos.x + self.size.x
        y: bool = self.pos.y <= mouse_pos.y <= self.pos.y + self.size.y
        return x and y

    def on_mouse_press(self, button: int, state: int, mouse_pos: Vector):
        if self.mouse_over and button == 1:

            if state == 1:
                self.color = Color(self.color.r + 50,self.color.g + 50,self.color.b)
                self.pressing = True

            if state == 0:
                self.action()

        if state == 0 and self.pressing:
            self.color = Color(self.color.r - 50, self.color.g - 50, self.color.b)
            self.pressing = False

    def on_key_press(self, key: int, state: int):
        pass
