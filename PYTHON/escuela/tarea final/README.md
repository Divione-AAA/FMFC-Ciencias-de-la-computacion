# Agenda de contactos

## Problema que resuelve

Permite guardar contactos con nombre y número, consultarlos y buscarlos. Los
contactos se conservan en `contactos.txt` al cerrar la aplicación.

## Módulos

- `main.py`: coordina el menú y las operaciones.
- `contactos.py`: contiene las funciones para agregar y buscar contactos.
- `archivo.py`: carga y guarda los datos en el archivo CSV separado por punto y coma.
- `visual.py`: muestra el menú y solicita o presenta información.

## Ejemplos de uso

Al elegir **Agregar contacto**, se puede ingresar:

```text
Nombre: Ana Pérez
Número: 809-555-1234
```

El contacto se guarda en `contactos.txt`. Después se puede elegir **Ver
contactos** para mostrar la agenda o **Buscar contacto** y escribir parte del
nombre o del número.

## Ejecución

Con Python 3 instalado, abre una terminal en esta carpeta y ejecuta:

```text
python main.py
```

El archivo `contactos.txt` se crea automáticamente si no existe.
