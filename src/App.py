from scene.TitleScene import TitleScene
from Window import Window
from util.Vector import Vector

if __name__ == '__main__':
    window: Window = Window("Meteor Shower",Vector(800,600))
    window.set_scene(TitleScene(window))
    window.start()





