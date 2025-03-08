"""
Intro al POO
"""

class Animal(object):
    """Clase animal, cuando no se hereda de nada, se debe heredar de object"""
    color="blanco"
    def __init__(self,color):
        """Esto es un constructor, utiliza self para hacer referencia a la clase"""
        self.color=color
    def hacer_sonido(self):
        """El sonido de un animal"""
        print("Sono un animal")

PERRO = Animal("amarillo")
print(PERRO.color)

#herencia
class Perro(Animal):
    """Clase Perro, hereda de Animal"""
    def hacer_sonido(self):
        """El sonido de un perro"""
        print("Ladra(jau jau)")

YACO = Perro("amarillo")
YACO.hacer_sonido()

#.publico
#._protegido
#.__privado
