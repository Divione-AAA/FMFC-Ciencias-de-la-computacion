"""
Funciones en python
"""

def saludo(nombre, apellido):
    """Las funcioness deben tener docstring"""
    return "Hola " + nombre + " " + apellido

#variables por omision
def saludo2(nombre="puto"):
    """Por omision"""
    return "Hola " + nombre

#parametros arbitrarios
def saludo3(nombre, *args):
    """
    Parametros arbitrarios, se utiliza isinstance para verificar tipo
    es mejor que type porque verifica incluso si es una clase o un objeto q hereda
    """
    for arg in args:
        if isinstance(arg,str):
            return "Hola " + arg
    return "Hola " + nombre

print(saludo3("puto", "tico"))

#paeametros arbitrarios tipo mapa (diccionario)
def saludo4(nombre, **args):
    """
    ** significa que los parametros son de tipo mapa y args.items() es cada elemento
    se recorre de forma clave valor sobre args.iems no sobre args solo
    """
    for clave,valor in args.items():
        print(clave, ": ", valor)
    return "Hola " + nombre

print(saludo4("Lois",apellido="puto"))

#datos de llamada como un arreglo

def importe(saldo, interes):
    """
    Se puede pasar un arreglo como parametro
    """
    return saldo * interes

TPAR = [1000,0.07]
print(importe(*TPAR))

#tmb como mapas
TPAR = {"saldo":1000,"interes":0.08}
print(importe(**TPAR))

#notar como se usan * y **
#Las llamadas dinamicas son aquellas en las q no se sabe si existen las funciones

def llamada_de_retorno(function):
    """Ejemplo de llamada dinamica"""
    return globals()[function]()

#un ejemplo mas obvio
NOMBREFUNCION = "saludo"

print(locals()[NOMBREFUNCION]("Maria","Elena"))

#saber si una funcion existe y puede ser llamada
#para saber si existe
if NOMBREFUNCION in locals():
    #para saber si es llamable
    if callable(locals()[NOMBREFUNCION]):
        print(locals()[NOMBREFUNCION]("Lalisa","Manoban"))

#recursividad

def mcd(a,b):
    """la clasica"""
    #al parecer no existen los ternarios clasicos
    return a if b==0 else mcd(b,a%b)

print(mcd(10,3))
