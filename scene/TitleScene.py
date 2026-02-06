from pygame import Color

from render.Text import Text, TextBounceHandler
from render.environment.Background import Background
from scene.GameScene import GameScene
from util.Vector import Vector
from render.Button import Button
from scene.Scene import Scene

class TitleScene(Scene):

    def __init__(self,window : 'Window'):
        super().__init__(window, "TitleScene")

        #Define Title Text & Bounce Handler
        self.text: Text = Text(self, Vector.null(), "Meteor Shower", "Arial Black", 70, Color(255, 255, 0))
        self.text.pos = (window.size * 0.5) - (self.text.get_vector_size() * 0.5) - Vector(0,50)
        self.title_bounce_handler: TextBounceHandler = TextBounceHandler(self.text, self.text.pos)

        #Define Start Button
        action = lambda: window.set_scene(GameScene(window))
        self.button: Button = Button(self,Vector.null(),Vector(250,100),Color(100,100,100),"Start!",action)
        self.button.pos = window.size * 0.5 - (self.button.size * 0.5) + Vector(0,75)

        #Define Starry Background
        self.background: Background = Background(self,window,50,5)

        #Add Elements
        self.drawables.append(self.background)
        self.drawables.append(self.text)
        self.drawables.append(self.button)

    def draw(self):
        super().draw()
        self.title_bounce_handler.update_pos()
