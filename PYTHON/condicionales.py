"""
Funcionamiento de las condicionales
en python
"""

SEMAFORO = str(input("Ingrese un valor para el semaforo: "))

#no es else if es elif, como la novela turca
#string es str

if SEMAFORO == "verde":
    print("puedes cruzar")
elif SEMAFORO == "amarillo":
    print("debes esperar")
elif SEMAFORO == "rojo":
    print("no debes cruzar")
else:
    print("error")
