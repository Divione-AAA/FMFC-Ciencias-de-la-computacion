"""
Las tuplas son una forma de agrupar datos que se pueden usar para almacenar
y manipular conjuntos de datos inmutables.

"""

TUPLE = ("Hector", 3, 5.3, -8.3, True,"David")
print(TUPLE)
print(type(TUPLE))
print(TUPLE[TUPLE[1]])

#metodos de las tuplas
print("Metodos de las tuplas")
NUMERO = (1,2,3,4,5,6,7,8,9,10)
print(len(NUMERO))
print(min(NUMERO))
print(max(NUMERO))
print(NUMERO[0:5])
print(sum(NUMERO))
print((NUMERO.count(5)))
print(NUMERO.index(5))

# el metodo count devuelve el numero de veces que el valor se repite en el conjunto
# el metodo index devuelve el indice del valor en el conjunto
# al ser inmutables no tienen modificadores
# el imprimir 0:5 imprime desde 0 hasta 5-1, en este caso el elemento en la posicion 4
