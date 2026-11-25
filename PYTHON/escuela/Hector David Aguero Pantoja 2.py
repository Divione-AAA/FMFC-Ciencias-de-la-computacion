
# EJERCICIO 1 - CLASE PERSONA

class Persona:
    # Constructor: permite crear una persona con datos vacíos
    def __init__(self, nombre="", edad=0, ci=""):
        self.__nombre = ""
        self.__edad = 0
        self.__ci = ""

        self.set_nombre(nombre)
        self.set_edad(edad)
        self.set_ci(ci)

    # Getter del nombre
    def get_nombre(self):
        return self.__nombre

    # Setter del nombre
    def set_nombre(self, nombre):
        if isinstance(nombre, str):
            self.__nombre = nombre

    # Getter de la edad
    def get_edad(self):
        return self.__edad

    # Setter de la edad (solo acepta valores positivos)
    def set_edad(self, edad):
        if isinstance(edad, int) and edad >= 0:
            self.__edad = edad

    # Getter del CI
    def get_ci(self):
        return self.__ci

    # Setter del CI
    def set_ci(self, ci):
        if isinstance(ci, str):
            self.__ci = ci

    # Muestra todos los datos de la persona
    def mostrar(self):
        print("=== PERSONA ===")
        print(f"Nombre: {self.__nombre}")
        print(f"Edad: {self.__edad}")
        print(f"CI: {self.__ci}")

    # Devuelve True si es mayor de edad y False en caso contrario
    def esMayorDeEdad(self):
        return self.__edad >= 18


# EJERCICIO 2

class Cuenta:
    def __init__(self, titular=None, cantidad=0.0):
        self.__titular = titular if titular else Persona()
        self.__cantidad = float(cantidad)

    def get_titular(self):
        return self.__titular

    def get_cantidad(self):
        return self.__cantidad

    def set_titular(self, titular):
        if isinstance(titular, Persona):
            self.__titular = titular

    def mostrar(self):
        print("\n=== CUENTA ===")
        self.__titular.mostrar()
        print(f"Cantidad: {self.__cantidad:.2f}")

    def ingresar(self, cantidad):
        if cantidad > 0:
            self.__cantidad += cantidad

    def retirar(self, cantidad):
        if cantidad > 0:
            self.__cantidad -= cantidad

# EJERCICIO 

class CuentaJoven(Cuenta):
    def __init__(self, titular=None, cantidad=0.0, bonificacion=0):
        super().__init__(titular, cantidad)
        self.__bonificacion = bonificacion

    def get_bonificacion(self):
        return self.__bonificacion

    def set_bonificacion(self, bonificacion):
        if bonificacion >= 0:
            self.__bonificacion = bonificacion

    def esTitularValido(self):
        titular = self.get_titular()
        return titular.esMayorDeEdad() and titular.get_edad() < 25

    def retirar(self, cantidad):
        if self.esTitularValido():
            super().retirar(cantidad)
        else:
            print("No se puede retirar dinero. Titular no válido.")

    def mostrar(self):
        print("\n=== CUENTA JOVEN ===")
        print(f"Bonificación: {self.__bonificacion}%")
        print(f"Cantidad: {self.get_cantidad():.2f}")
        self.get_titular().mostrar()