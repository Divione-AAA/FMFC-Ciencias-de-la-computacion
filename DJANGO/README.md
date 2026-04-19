# Django

Este README resume los proyectos dentro de la carpeta `DJANGO` y lo aprendido en cada uno.

## Estructura general

- `BackendHoteles/`
  - Proyecto Django con administrador, base de datos SQLite y entornos de desarrollo.
  - Incluye dos aplicaciones principales:
    - `Hoteles/`
    - `Valoraciones/`
  - Contiene archivos típicos de Django: `manage.py`, `.idea/`, `.venv/`, `db.sqlite3`.

- `biblioteca/`
  - Proyecto Django orientado a una biblioteca o gestión de libros.
  - Incluye aplicaciones `main/` y `library/`.
  - Contiene `manage.py` y `db.sqlite3`.

- `mi_proyecto/`
  - Proyecto Django con una aplicación `blog/`.
  - Incluye la carpeta del proyecto `mi_proyecto/` con archivos de configuración (`settings.py`, `urls.py`, `wsgi.py`, `asgi.py`).
  - Incluye `manage.py` y `db.sqlite3`.

- `tareas api/`
  - Proyecto Django para crear una API de tareas.
  - Incluye una aplicación `taskapi/` y otra carpeta `tasks/`.
  - Contiene `manage.py`, `db.sqlite3`, `staticfiles/` y `venv/`.

## Resumen por proyecto

### BackendHoteles

Este proyecto contiene un backend de hoteles con dos apps que probablemente manejan:
- hoteles y datos relacionados a hoteles.
- valoraciones o reseñas de usuarios.

Archivos clave:
- `Hoteles/` y `Valoraciones/`: apps con `models.py`, `views.py`, `serializers.py`, `urls.py`, `admin.py`.
- `manage.py`: comando para ejecutar servidor, migraciones, shell.
- `db.sqlite3`: base de datos local usada para pruebas.

Lo aprendido:
- cómo estructurar un proyecto Django con varias apps.
- uso de modelos y migraciones para definir datos.
- creación de serializers y vistas para exponer datos como API.
- configuración de rutas con `urls.py`.
- uso del admin de Django para gestionar datos.

### biblioteca

Este proyecto es una aplicación de biblioteca con una app principal `main/` y otra `library/`.

Lo aprendido:
- creación de apps Django y organización de carpetas.
- manejo de base de datos SQLite para una app simple.
- diferencias entre proyecto (`manage.py`, configuraciones) y aplicaciones Django.
- probable uso de vistas y modelos para representar libros o préstamos.

### mi_proyecto

Proyecto tipo blog con la app `blog/`.

Archivos clave:
- `blog/models.py`: definición de las entidades del blog.
- `blog/views.py`: lógica para mostrar entradas.
- `mi_proyecto/settings.py`: configuración del proyecto.
- `mi_proyecto/urls.py`: rutas principales.

Lo aprendido:
- estructura básica de un proyecto Django de blog.
- integración de una app dentro del proyecto.
- rutas y configuración de `settings.py`.
- creación de modelos y administración de contenido.

### tareas api

Proyecto orientado a una API REST para tareas.

Archivos clave:
- `taskapi/serializers.py`: convierte modelos Django a JSON y viceversa.
- `taskapi/api.py`: lógica de la API.
- `taskapi/views.py`, `taskapi/urls.py`: rutas y controladores.

Lo aprendido:
- cómo construir una API con Django.
- uso de serializadores para transferir datos.
- estructura de un proyecto que expone endpoints para CRUD.
- integración de Django con servicios REST.

## Conceptos comunes aprendidos en Django

- Diferencia entre proyecto y aplicación en Django.
- `manage.py` como herramienta de administración del proyecto.
- `settings.py` para configurar bases de datos, apps instaladas y middleware.
- `urls.py` para enrutar solicitudes HTTP a vistas.
- creación y uso de `models.py` para definir datos.
- migraciones: `makemigrations` y `migrate`.
- administración de datos con `admin.py`.
- serializadores y APIs REST en Django.
- uso de SQLite para desarrollo local.

## Siguientes pasos recomendados

- explorar el contenido de cada `models.py`, `views.py` y `urls.py` para conocer detalles concretos.
- ejecutar `python manage.py runserver` en cada proyecto para ver su comportamiento.
- revisar `serializers.py` en `BackendHoteles` y `tareas api` para entender la API REST.
- usar el admin de Django para practicar datos reales.
