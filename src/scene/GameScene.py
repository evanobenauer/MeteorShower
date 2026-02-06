from pygame import Color

from src.Window import Window
from src.entity.MeteorSpawner import MeteorSpawner, Meteor
from src.entity.Player import Player
from src.render.Button import Button
from src.render.Text import Text
from src.render.environment.Background import Background
from src.render.environment.Ground import Ground
from src.scene.Scene import Scene
from src.util.QuickTimer import QuickTimer
from src.util.Vector import Vector

class GameScene(Scene):

    def __init__(self, window : Window):
        super().__init__(window, "GameScene")

        #Game Starters
        self.is_started: bool = False
        self.countdown: int = 3
        self.countdown_timer: QuickTimer = QuickTimer()

        #Game Score
        self.score: int = 0
        self.score_timer: QuickTimer = QuickTimer()

        #Scene Drawables
        self.background: Background = Background(self,window,60,10)
        self.ground: Ground = Ground(self,75,30)

        #Entities
        self.meteor_spawner: MeteorSpawner = MeteorSpawner(self, Vector.null(), int(window.size.x), -20, int(window.size.y))
        self.player: Player = Player(self,Vector(window.size.x * .5 - 5,self.ground.pos.y - 30),10,Color(0,255,0))

        #Add All Drawables
        self.drawables.append(self.background)
        self.drawables.append(self.ground)
        self.drawables.append(self.player)

    def draw(self):
        self.update_countdown_start()

        if self.is_started:
            self.update_game()

        super().draw()

        self.draw_score_text()
        self.draw_countdown()

    # ======================

    # GAME UPDATE FUNCTIONS

    # ======================

    def update_game(self):
        self.meteor_spawner.update()
        self.player.update_player(self.ground.pos.y)

        #Update Meteor Collisions
        for meteor in self.drawables:
            if isinstance(meteor,Meteor):
                if meteor.is_colliding(self.player):
                    self.end_game()
                    self.drawables.remove(self.player)

        if self.is_started:
            self.score_timer.start()
            if self.score_timer.has_time_passed_s(1):
                self.score += 1
                self.score_timer.restart()


    def end_game(self):
        self.is_started = False
        action = lambda : self.window.set_scene(GameScene(self.window))
        retry_button: Button = Button(self,Vector.null(),Vector(250,100),Color(100,100,100),"Retry!",action)
        retry_button.pos = self.window.size * 0.5 - (retry_button.size * 0.5)
        self.drawables.append(retry_button)


    # ======================

    # COUNTDOWN & SCORE FUNCTIONS

    # ======================

    def draw_score_text(self):
        score_text: Text = Text(self,Vector(2,2),f"Score: {self.score}","Arial Black",20,Color(255,255,25))
        score_text.draw()

    def draw_countdown(self):
        if not self.is_started:
            text: Text = Text(self,Vector.null(),str(self.countdown),"Arial Black",50,Color(255,255,255))
            text.pos = (self.window.size * .5) - text.get_vector_size() * .5
            text.draw()

    def update_countdown_start(self):
        self.countdown_timer.start()
        if self.countdown_timer.has_time_passed_s(1) and self.countdown != "" and self.countdown >= 0:
            if self.countdown == 0:
                self.is_started = True
                self.countdown = "" #Converts the countdown to an empty string as to not draw it during src end
            else:
                self.countdown -= 1
            self.countdown_timer.restart()