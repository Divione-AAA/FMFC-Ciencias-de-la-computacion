"""
    Las listas son una forma de agrupar datos que se pueden usar para almacenar
    y manipular conjuntos de datos muatbles.
"""

LISTA = ["Hector", 3, 5.3, -8.3, True,"David"]
print(LISTA)

#metodos de las listas

print("Metodos de las listas")
LISTA.append("ELEONOR")
print(LISTA)
LISTA.insert(4,"QUINDIN")
print(LISTA)
LISTA.remove("David")
print(LISTA)
LISTA.pop()
print(LISTA)
LISTA.reverse()
print(LISTA)
