"""Ejercicios de entrada/salida y manejo de cotizaciones."""

import os


def pedir_numero():
    """Solicita un numero entero comprendido entre 1 y 10.

    Parametros:
        No recibe parametros.

    Devuelve:
        El numero entero introducido por el usuario.
    """
    while True:
        try:
            numero = int(input("Introduce un numero entero entre 1 y 10: "))
            if 1 <= numero <= 10:
                return numero
            print("El numero debe estar entre 1 y 10.")
        except ValueError:
            print("Introduce un numero entero valido.")


def escribir_tabla():
    """Pide un numero y guarda su tabla de multiplicar en un fichero.

    Parametros:
        No recibe parametros.

    Devuelve:
        La ruta del fichero creado.
    """
    numero = pedir_numero()
    ruta = os.path.join(os.path.dirname(os.path.abspath(__file__)),
                        f"tabla-{numero}.txt")

    with open(ruta, "w", encoding="utf-8") as archivo:
        for multiplicador in range(1, 11):
            archivo.write(f"{numero} x {multiplicador} = "
                          f"{numero * multiplicador}\n")

    print(f"Tabla guardada en {ruta}")
    return ruta


def leer_tabla():
    """Pide un numero, lee su tabla y la muestra por pantalla.

    Parametros:
        No recibe parametros.

    Devuelve:
        None si el fichero no existe; en caso contrario, no devuelve un
        valor relevante.
    """
    numero = pedir_numero()
    ruta = os.path.join(os.path.dirname(os.path.abspath(__file__)),
                        f"tabla-{numero}.txt")

    if not os.path.isfile(ruta):
        print(f"No existe el fichero {ruta}.")
        return None

    with open(ruta, "r", encoding="utf-8") as archivo:
        print(archivo.read(), end="")


def filtrar_numero(texto):
    """Convierte un numero en formato del CSV a un numero de Python.

    Parametros:
        - texto: Texto que contiene el numero a convertir.

    Devuelve:
        Un entero o un numero de punto flotante. Devuelve 0 si el texto
        esta vacio y no puede convertirse.
    """
    texto = texto.strip()
    if not texto:
        return 0

    texto = texto.replace(",", ".")
    partes = texto.split(".")
    if len(partes) > 2:
        texto = "".join(partes)
    elif len(partes) == 2 and len(partes[1]) == 3:
        texto = "".join(partes)
    try:
        numero = float(texto)
        if numero.is_integer():
            return int(numero)
        return numero
    except ValueError:
        return 0


def leer_cotizaciones(ruta):
    """Lee un CSV de cotizaciones sin utilizar el modulo csv.

    Parametros:
        - ruta: Ruta del fichero CSV.

    Devuelve:
        Un diccionario cuyas claves son las columnas y cuyos valores son
        listas con los datos.
    """
    with open(ruta, "r", encoding="utf-8") as archivo:
        encabezados = archivo.readline().rstrip("\r\n").split(";")
        datos = {encabezado: [] for encabezado in encabezados}

        for linea in archivo:
            linea = linea.rstrip("\r\n")
            if not linea:
                continue

            valores = linea.split(";")
            for indice, encabezado in enumerate(encabezados):
                valor = valores[indice].strip() if indice < len(valores) else ""
                if encabezado == "Nombre":
                    datos[encabezado].append(valor)
                else:
                    datos[encabezado].append(filtrar_numero(valor))

    return datos


def crear_resumen(datos, ruta_salida):
    """Crea un CSV con el minimo, el maximo y la media de cada columna.

    Parametros:
        - datos: Diccionario obtenido con leer_cotizaciones.
        - ruta_salida: Ruta del fichero CSV que se creara.

    Devuelve:
        La ruta del fichero creado.
    """
    columnas_numericas = [nombre for nombre in datos
                          if nombre != "Nombre"]

    with open(ruta_salida, "w", encoding="utf-8", newline="") as archivo:
        archivo.write("Columna;Minimo;Maximo;Media\n")
        for columna in columnas_numericas:
            valores = datos[columna]
            if valores:
                minimo = min(valores)
                maximo = max(valores)
                media = sum(valores) / len(valores)
                archivo.write(
                    f"{columna};{minimo};{maximo};{media:.2f}\n"
                )

    print(f"Resumen creado en {ruta_salida}")
    return ruta_salida


def main():
    """Ejecuta los ejercicios de entrada/salida."""
    print("Ejercicio 1")
    escribir_tabla()

    print("\nEjercicio 2")
    leer_tabla()

    print("\nEjercicio 3")
    carpeta = os.path.dirname(os.path.abspath(__file__))
    csv_entrada = os.path.join(
        carpeta,
        "CP#4 - Ejercicios de Entrada-Salida en Python. Docstring",
        "cotizacion.csv",
    )
    csv_salida = os.path.join(carpeta, "resumen-cotizaciones.csv")
    datos = leer_cotizaciones(csv_entrada)
    crear_resumen(datos, csv_salida)


if __name__ == "__main__":
    main()
