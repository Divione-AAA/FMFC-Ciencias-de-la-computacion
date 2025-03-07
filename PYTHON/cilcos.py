"""
    Estructuras de control iterativas
"""

#notar que despues del condicional logico se escribe 2 puntos
CANTIDAD = int(input("Cuantas veces me piensas?: "))
while CANTIDAD > 0:
    print("Yo tambien te pienso")
    CANTIDAD-=1

#este es un for each
COLORES=("rojo","verde","azul","amarillo","blanco")
for i in COLORES:
    print(i)

#este es un for clasico, se especifica el rango
for i in range(5,100):
    print(i)

#break y continue
CANTIDAD = int(input("Dime un numero superior al 10: "))
while True:
    if CANTIDAD < 4:
        break    
    if CANTIDAD % 2 == 0: 
        print(CANTIDAD)
    CANTIDAD-=1
    
def main():
    print("Hola")
    