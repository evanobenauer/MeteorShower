import random

from pygame import Color

from src.entity.PhysicsEntity import SquarePhysicsEntity
from src.entity.Player import Player
from src.scene.Scene import Scene
from src.util.QuickTimer import QuickTimer
from src.util.Vector import Vector


class MeteorSpawner:

    def __init__(self, scene: Scene, pos: Vector, spawn_range: int, spawn_y: int, destroy_y: int):
        self.scene = scene
        self.pos = pos

        self.spawn_range = spawn_range

        self.spawn_y = spawn_y
        self.destroy_y = destroy_y

        self.random = random.Random()
        self.spawn_timer = QuickTimer()

    # ======================

    # Update Functions

    # ======================

    def update(self):
        self.update_meteor_spawns(0, 10, 0.5)
        self.update_meteor_destroys()
        pass

    def update_meteor_spawns(self, min_count: int, max_count: int, seconds: float):
        self.spawn_timer.start()
        if self.spawn_timer.has_time_passed_s(seconds):

            for i in range(random.randint(min_count, max_count + 1)):
                self.spawn_meteor()

            self.spawn_timer.restart()

    def update_meteor_destroys(self):
        for drawable in self.scene.drawables:
            if isinstance(drawable, Meteor) and drawable.pos.y > self.destroy_y:
                self.scene.drawables.remove(drawable)

    # ======================

    # Spawn/Destroy Functions

    # ======================

    def spawn_meteor(self):
        pos: Vector = Vector(random.randint(0, self.spawn_range), self.spawn_y)
        size: int = random.randint(10, 30)

        meteor: Meteor = Meteor(self.scene, pos, size)

        meteor.velocity = Vector(random.randint(-100, 100) / 10, 20)
        meteor.omega = random.randint(-100, 100) / 100

        self.scene.drawables.append(meteor)

    def destroy_meteor(self, meteor: 'Meteor'):
        self.scene.drawables.remove(meteor)


# ======================

# Meteor Entity Class

# ======================

class Meteor(SquarePhysicsEntity):

    def __init__(self, scene: Scene, pos: Vector, size: float):
        super().__init__(scene, pos, size, Color(134, 48, 22, 255))

    def is_colliding(self, player: Player) -> bool:
        is_colliding_x: bool = player.pos.x + player.size.x >= self.pos.x and player.pos.x <= self.pos.x + self.size.x
        is_colliding_y: bool = player.pos.y + player.size.y >= self.pos.y and player.pos.y <= self.pos.y + self.size.y
        return is_colliding_x and is_colliding_y
