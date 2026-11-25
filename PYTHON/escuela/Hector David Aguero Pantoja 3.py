# Ejercicio 1
asignaturas = ["Matemáticas", "Física", "Química", "Historia", "Lengua"]
for asignatura in asignaturas[:]:
    while True:
        try:
            nota = float(input(f"¿Qué nota has sacado en {asignatura}? "))
            if not (0 <= nota <= 10):
                print("La nota debe estar entre 0 y 10.")
                continue
            break
        except ValueError:
            print("Introduce un número válido.")
    if nota >= 5:
        asignaturas.remove(asignatura)

print("Asignaturas que tienes que repetir:")
for asignatura in asignaturas:
    print(asignatura)


# Ejercicio 2 (Matrices corregidas según la imagen)
A = ((1, 2, 3),
     (4, 5, 6))
B = ((-1, 0),
     (0, 1),
     (1, 1))

try:
    if len(A[0]) != len(B):
        raise ValueError("Las dimensiones no son compatibles.")
    resultado = []
    for i in range(len(A)):
        fila = []
        for j in range(len(B[0])):
            suma = 0
            for k in range(len(B)):
                suma += A[i][k] * B[k][j]
            fila.append(suma)
        resultado.append(tuple(fila))
    resultado = tuple(resultado)
    for fila in resultado:
        print(fila)
except ValueError as e:
    print(e)


# Ejercicio 3 (Formato ajustado a la tabla de la imagen)
cesta = {}
while True:
    articulo = input("Artículo (o 'fin' para terminar): ").strip()
    if articulo.lower() == "fin":
        break
    if not articulo:
        print("El artículo no puede estar vacío.")
        continue
    while True:
        try:
            precio = float(input(f"Precio de {articulo}: "))
            if precio < 0:
                print("El precio no puede ser negativo.")
                continue
            break
        except ValueError:
            print("Introduce un número válido.")
    cesta[articulo] = precio

print("\nLista de la compra")
print("-" * 30)
total = 0
for articulo, precio in cesta.items():
    # Alineación a la izquierda para el artículo y a la derecha para el precio
    print(f"{articulo:<20} {precio:>8.2f}")
    total += precio
print("-" * 30)
print(f"{'Total':<20} {total:>8.2f}")


# Ejercicio 4
entrada = input("Pares <palabra>:<traducción> separados por comas: ")
diccionario = {}
try:
    for par in entrada.split(","):
        par = par.strip()
        if not par:
            continue
        if ":" not in par:
            raise ValueError(f"Formato incorrecto en '{par}'.")
        espanol, ingles = par.split(":", 1)
        diccionario[espanol.strip().lower()] = ingles.strip()
except ValueError as e:
    print(e)
else:
    frase = input("Frase en español: ")
    traduccion = []
    for palabra in frase.split():
        clave = palabra.lower().strip(".,;:!?¡¿")
        traduccion.append(diccionario.get(clave, palabra))
    print(" ".join(traduccion))