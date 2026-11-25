def agregar_contacto(
    lista_contactos: list[dict[str, str]], nombre: str, numero: str
) -> None:
    nombre = nombre.strip()
    numero = numero.strip()

    if not nombre or not numero:
        raise ValueError("El nombre y el número son obligatorios.")

    if any(contacto["numero"] == numero for contacto in lista_contactos):
        raise ValueError("Ya existe un contacto con ese número.")

    lista_contactos.append({"nombre": nombre, "numero": numero})


def buscar_contactos(
    lista_contactos: list[dict[str, str]], criterio: str
) -> list[dict[str, str]]:
    criterio = criterio.strip().casefold()
    if not criterio:
        return []

    return [
        contacto
        for contacto in lista_contactos
        if criterio in contacto["nombre"].casefold()
        or criterio in contacto["numero"].casefold()
    ]
