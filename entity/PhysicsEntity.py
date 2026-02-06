from abc import ABC

from pygame import Color

from render.base.Drawable import Drawable
from render.Rectangle import RotatedRectangle
from util.Vector import Vector
from scene.Scene import Scene

class PhysicsEntity(Drawable, ABC):

    def __init__(self, scene: Scene, pos : Vector):
        super().__init__(scene, pos)
        self.deltaT: float = .1

        # Scalar Attributes
        self.mass: float = 1
        self.charge: float = 0
        self.moment_of_inertia = 1

        # Linear Motion Attributes
        self.velocity: Vector = Vector.null()
        self.acceleration: Vector = Vector.null()
        self.net_force: Vector = Vector.null()

        # Rotational Motion Attributes
        self.theta: float = 0
        self.omega: float = 0
        self.alpha: float = 0
        self.net_torque: float = 0

    def draw(self):
        self.update()
        pass

    def update(self):
        self.update_acc_from_force()
        self.update_alpha_from_torque()

        self.update_kinematics()
        self.update_rotational_kinematics()

        self.net_force = Vector.null()
        self.net_torque = 0



    def update_kinematics(self):
        self.velocity += self.acceleration * self.deltaT
        self.pos += self.velocity * self.deltaT

    def update_acc_from_force(self):
        self.acceleration = self.net_force / self.mass


    def update_rotational_kinematics(self):
        self.omega += self.alpha * self.deltaT
        self.theta += self.omega * self.deltaT

    def update_alpha_from_torque(self):
        self.alpha /= self.moment_of_inertia


    def reset_movement(self):
        self.net_force = Vector.null()
        self.acceleration = Vector.null()
        self.velocity = Vector.null()

        self.net_torque = 0
        self.omega = 0
        self.alpha = 0


class SquarePhysicsEntity(RotatedRectangle, PhysicsEntity):

    def __init__(self, scene: Scene, pos: Vector, size: float, color: Color):
        PhysicsEntity.__init__(self, scene, pos)
        RotatedRectangle.__init__(self, scene, pos, Vector(size,size), color,0)

    def draw(self):
        PhysicsEntity.draw(self)
        self.angle = self.theta
        RotatedRectangle.draw(self)