import csv
from pathlib import Path


COLUMNAS = ("nombre", "numero")


def cargar_contactos(ruta: Path) -> list[dict[str, str]]:
    if not ruta.exists():
        guardar_contactos([], ruta)
        return []

    with ruta.open("r", newline="", encoding="utf-8") as fichero:
        lector = csv.DictReader(fichero, delimiter=";")
        if lector.fieldnames != list(COLUMNAS):
            raise ValueError("El archivo debe tener las columnas nombre;numero.")

        lista_contactos = []
        for numero_fila, fila in enumerate(lector, start=2):
            nombre = (fila.get("nombre") or "").strip()
            numero = (fila.get("numero") or "").strip()
            if not nombre or not numero or None in fila:
                raise ValueError(f"El contacto de la fila {numero_fila} está incompleto.")
            lista_contactos.append({"nombre": nombre, "numero": numero})

    return lista_contactos


def guardar_contactos(
    lista_contactos: list[dict[str, str]], ruta: Path
) -> None:
    with ruta.open("w", newline="", encoding="utf-8") as fichero:
        escritor = csv.DictWriter(fichero, fieldnames=COLUMNAS, delimiter=";")
        escritor.writeheader()
        escritor.writerows(lista_contactos)
