# 📚 Resumen del Proyecto: Práctica de PostgreSQL

## 🏗️ Estructura del Proyecto

- **HECTORDAP.SQL**: Script principal con la creación de tablas, inserciones, consultas, vistas y reglas.
- **Otros archivos .sql**: Scripts adicionales para otras prácticas o ejercicios.

## 📝 ¿Qué se aprendió?

- **Creación de tablas** con claves primarias y foráneas.
- **Inserción de datos** en tablas relacionales.
- **Consultas básicas y manipulaciones**: DELETE, UPDATE, SELECT.
- **Creación y uso de vistas** para consultas personalizadas.
- **Uso de reglas** para automatizar acciones tras operaciones (ejemplo: actualizar existencias tras borrar pedidos).

## 🧩 Estructura del Script Principal

1. **Definición de tablas**: `Producto` y `Pedido`.
2. **Inserción de datos** en ambas tablas.
3. **Consultas y manipulaciones**: eliminación y actualización de registros.
4. **Creación de vistas**: `pedidos_2117` y `total_pedidos_cliente`.
5. **Definición de una regla** para actualizar existencias automáticamente.
6. **Consultas finales** para verificar los cambios.

## 🚀 ¿Cómo ejecutarlo?

1. Abre tu cliente de PostgreSQL (ejemplo: pgAdmin, DBeaver, psql).
2. Crea una base de datos nueva (opcional pero recomendado).
3. Abre el archivo `HECTORDAP.SQL`.
4. Ejecuta todo el script o por bloques, según lo requieras.
5. Observa los resultados de las consultas y vistas creadas.

## 💡 Recomendaciones

- Ejecuta primero la creación de tablas y las inserciones.
- Luego realiza las consultas y manipulaciones para ver los efectos.
- Usa las vistas para obtener resúmenes rápidos de la información.
- Prueba la regla eliminando pedidos y revisando cómo cambian las existencias.

---