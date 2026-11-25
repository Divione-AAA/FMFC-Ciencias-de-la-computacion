def mostrar_menu() -> str:
    print("\n--- Agenda de contactos ---")
    print("1. Ver contactos")
    print("2. Agregar contacto")
    print("3. Buscar contacto")
    print("4. Salir")
    return input("Selecciona una opción: ").strip()


def solicitar_contacto() -> tuple[str, str]:
    nombre = input("Nombre: ")
    numero = input("Número: ")
    return nombre, numero


def solicitar_busqueda() -> str:
    return input("Escribe un nombre o número para buscar: ")


def mostrar_contactos(lista_contactos: list[dict[str, str]]) -> None:
    if not lista_contactos:
        print("No se encontraron contactos.")
        return

    print("\nContactos:")
    for contacto in lista_contactos:
        print(f"- {contacto['nombre']}: {contacto['numero']}")


def mostrar_mensaje(mensaje: str) -> None:
    print(mensaje)
