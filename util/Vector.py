from math import sqrt

class Vector:

    @staticmethod
    def null() -> 'Vector':
        return Vector(0, 0, 0)

    def __init__(self, x : float, y : float, z : float = 0):
        self.x = x
        self.y = y
        self.z = z


    def scale(self, vector: 'Vector') -> 'Vector':
        return Vector(self.x * vector.x, self.y * vector.y, self.z * vector.z)

    def normalize(self) -> 'Vector':
        return Vector(self.x / self.magnitude(), self.y / self.magnitude(), self.z / self.magnitude())

    def magnitude(self) -> float:
        return sqrt(pow(self.x, 2) + pow(self.y, 2) + pow(self.z, 2))


        #Look closer into operator overloading, it modifies the main vector sometimes? idk man python...
    def __add__(self, vector : 'Vector') -> 'Vector':
        return Vector(self.x + vector.x, self.y + vector.y, self.z + vector.z)

    def __sub__(self, vector : 'Vector') -> 'Vector':
        return Vector(self.x - vector.x, self.y - vector.y, self.z - vector.z)

    def __mul__(self, mul : float) -> 'Vector':
        return Vector(self.x * mul, self.y * mul, self.z * mul)

    def __truediv__(self, div : float) -> 'Vector':
        return Vector(self.x / div, self.y / div, self.z / div)

    def __eq__(self, other: 'Vector'):
        return self.x == other.x and self.y == other.y and self.z == other.z

    def __str__(self):
        return f'<{self.x}, {self.y}, {self.z}>'


