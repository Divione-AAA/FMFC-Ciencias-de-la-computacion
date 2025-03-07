"""
por lo que veo los diccionarios son como mapas en c++
"""

DICCIONARIO = {"Nombre":"David","Edad":30,"Pais":"Cuba"}
print(DICCIONARIO)
print(DICCIONARIO["Nombre"])
print(DICCIONARIO["Pais"])

#metodos de los diccionarios
print("Metodos de los diccionarios")
DICCIONARIO["Pais"]="Canada"
print(DICCIONARIO)
DICCIONARIO.clear()
print(DICCIONARIO)
