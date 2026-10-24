#Ejercicio 1
nombre = input("Introduce tu nombre: ")
print(f"¡Hola {nombre}!")

#Ejercicio 2
n = int(input("Introduce el primer número entero: "))
m = int(input("Introduce el segundo número entero: "))
c = n // m
r = n % m
print(f"{n} entre {m} da un cociente {c} y un resto {r}")

#Ejercicio 3
renta = float(input("Introduce tu renta anual en euros: "))

if renta < 10000:
    impuesto = 5
elif renta < 20000:
    impuesto = 15
elif renta < 35000:
    impuesto = 20
elif renta <= 60000:
    impuesto = 30
else:
    impuesto = 45

print(f"El impuesto que te corresponde es del {impuesto}%")

#Ejercicio 4
frase = input("Introduce una frase: ")
invertida = frase[::-1]
print(invertida)

#Ejercicio 5
n = int(input("Introduce un número entero positivo: "))
impares = []
for numero in range(1, n + 1, 2):
    impares.append(str(numero))
print(", ".join(impares))
