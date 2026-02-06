import pygame
from pygame import Color, Surface

from util.Vector import Vector

class Window:

    def __init__(self, title : str, size : Vector, scene : 'Scene' = None):
        pygame.init()
        pygame.font.init()

        #Define the size and the main PyGame Draw surface
        self.size : Vector = size
        self.base_surface: Surface = pygame.display.set_mode((self.size.x, self.size.y), pygame.SCALED, vsync=1)

        #Set Title
        self.title: str = title
        pygame.display.set_caption(title)

        #Define Running Variable
        self.running: bool = False

        #Define Scene Variable
        self.scene: 'Scene' = scene

    def start(self):
        self.running = True
        self.run_game_loop()

    def run_game_loop(self):
        while self.running:

            #Update Events
            for event in pygame.event.get():
                #Quit if the window is closed
                if event.type == pygame.QUIT:
                    self.running = False

                self.update_mouse(event)
                self.update_keys(event)

            #Draw the scene
            self.scene.surface.fill(Color(0,0,0)) #Draw the background
            self.scene.draw()

            #Update next render frame
            pygame.display.flip()

        pygame.quit()

    def update_keys(self, event):
        if event.type == pygame.KEYDOWN:
            self.scene.on_key_press(event.key, 1)

        if event.type == pygame.KEYUP:
            self.scene.on_key_press(event.key, 0)

    def update_mouse(self, event):
        if event.type == pygame.MOUSEMOTION:
            self.scene.mouse_pos = Vector(event.pos[0], event.pos[1])

        if event.type == pygame.MOUSEBUTTONDOWN:
            self.scene.on_mouse_press(event.button, 1, Vector(event.pos[0], event.pos[1]))

        if event.type == pygame.MOUSEBUTTONUP:
            self.scene.on_mouse_press(event.button, 0, Vector(event.pos[0], event.pos[1]))

    def set_scene(self, scene : 'Scene'):
        self.scene = scene
