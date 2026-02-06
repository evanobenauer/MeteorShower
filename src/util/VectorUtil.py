from src.entity.PhysicsEntity import PhysicsEntity
from src.util.Vector import Vector

def get_distance_vec(vec1: Vector, vec2: Vector) -> Vector:
    return Vector(vec1.x - vec2.x, vec1.y - vec2.y)

def get_distance(obj1: PhysicsEntity, obj2: PhysicsEntity) -> float:
    return Vector(obj1.pos.x - obj2.pos.x, obj1.pos.y - obj2.pos.y).magnitude()