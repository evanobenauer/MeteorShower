import pygame
from pygame import Color

from src.entity.PhysicsEntity import SquarePhysicsEntity
from src.render.base.Interactable import Interactable
from src.scene.Scene import Scene
from src.util.Vector import Vector

class Player(SquarePhysicsEntity, Interactable):

    def __init__(self, scene: Scene, pos: Vector, size: float, color: Color):
        SquarePhysicsEntity.__init__(self, scene, pos, size, color)
        Interactable.__init__(self,scene)

        self.on_ground = False
        self.left = False
        self.right = False
        self.jump = False


    def update_player(self, ground_y):
        #On Ground Update FIRST
        self.update_on_ground(ground_y)

        #Update Forces
        self.update_gravity(15)
        if self.on_ground:
            self.update_friction(1)

        #Update Collisions
        self.update_ground_collision(ground_y)
        self.update_wall_collision()

        #Update Controls
        self.update_controls()
        self.update_speed_cap()


    # ======================

    # FORCE UPDATES

    # ======================

    def update_gravity(self, g):
        self.net_force.y += self.mass * g

    def update_friction(self, coefficient):
        #Static Friction
        if abs(self.velocity.x) <= 1:
            if abs(self.net_force.x < coefficient * abs(self.net_force.y)):
                self.velocity.x = 0
                return #If static friction passes, do not apply any new friction forces

        #Kinetic Friction
        kinetic_force: float = coefficient * abs(self.net_force.y)
        velocity_positive: bool = self.velocity.x > 0
        force_positive: bool = self.velocity.x == 0 and self.net_force.x > 0
        if velocity_positive or force_positive: #Decides the sign of the friction force
            kinetic_force *= -1
        self.net_force.x += kinetic_force

    # ======================

    # CONTROL UPDATES

    # ======================

    def update_controls(self):
        h_force: int = 150
        j_force: int = 300
        if not self.on_ground:
            h_force /= 5

        if self.left:
            self.net_force.x -= h_force

        if self.right:
            self.net_force.x += h_force

        if self.jump and self.on_ground:
            self.net_force.y -= j_force

    def update_speed_cap(self):
        bound: int = 20
        if self.velocity.x > bound:
            self.velocity.x = bound

        if self.velocity.x < -bound:
            self.velocity.x = -bound

    # ======================

    # COLLISION UPDATES

    # ======================

    def update_on_ground(self, ground_y):
        self.on_ground: bool = self.pos.y + self.size.y >= ground_y

    def update_ground_collision(self, ground_y: int):
        if self.on_ground:
            self.pos.y = ground_y - self.size.y
            self.velocity.y = 0
            self.net_force.y -= self.net_force.y

    def update_wall_collision(self):
        if self.pos.x < 0:
            self.velocity.x = 0
            self.pos.x = 0

        if self.pos.x + self.size.x > self.scene.window.size.x:
            self.velocity.x = 0
            self.pos.x = self.scene.window.size.x - self.size.x

    # ======================

    # INTERACTABLE METHODS

    # ======================

    def on_key_press(self, key: int, state: int):
        if key == pygame.K_SPACE or key == pygame.K_w or key == pygame.K_UP:
            self.jump = state == 1

        if key == pygame.K_RIGHT or key == pygame.K_d:
            self.right = state == 1

        if key == pygame.K_LEFT or key == pygame.K_a:
            self.left = state == 1

    def on_mouse_press(self, button: int, state: int, mouse_pos: Vector):
        pass

    def get_mouse_hovered_calculation(self, mouse_pos: Vector):
        x: bool = self.pos.x <= mouse_pos.x <= self.pos.x + self.size.x
        y: bool = self.pos.y <= mouse_pos.y <= self.pos.y + self.size.y
        return x and y