import csv
from pathlib import Path

import archivo
import contactos
import visual


RUTA_CONTACTOS = Path(__file__).with_name("contactos.txt")


def main() -> None:
    try:
        lista_contactos = archivo.cargar_contactos(RUTA_CONTACTOS)
    except (OSError, ValueError, csv.Error) as error:
        visual.mostrar_mensaje(f"No se pudieron cargar los contactos: {error}")
        return

    while True:
        opcion = visual.mostrar_menu()

        if opcion == "1":
            visual.mostrar_contactos(lista_contactos)
        elif opcion == "2":
            nombre, numero = visual.solicitar_contacto()
            contactos_actualizados = lista_contactos.copy()
            try:
                contactos.agregar_contacto(contactos_actualizados, nombre, numero)
                archivo.guardar_contactos(contactos_actualizados, RUTA_CONTACTOS)
            except ValueError as error:
                visual.mostrar_mensaje(str(error))
            except OSError as error:
                visual.mostrar_mensaje(f"No se pudo guardar el contacto: {error}")
            else:
                lista_contactos = contactos_actualizados
                visual.mostrar_mensaje("Contacto agregado correctamente.")
        elif opcion == "3":
            criterio = visual.solicitar_busqueda()
            resultados = contactos.buscar_contactos(lista_contactos, criterio)
            visual.mostrar_contactos(resultados)
        elif opcion == "4":
            visual.mostrar_mensaje("Hasta luego.")
            break
        else:
            visual.mostrar_mensaje("Opción no válida. Elige un número del 1 al 4.")


if __name__ == "__main__":
    main()
