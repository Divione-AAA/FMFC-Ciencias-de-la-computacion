# 🚀 FMFC Ciencias de la Computación - JAVA

¡Bienvenido! Este repositorio contiene una amplia colección de ejercicios, prácticas y proyectos en Java, desarrollados a lo largo de tu aprendizaje en Ciencias de la Computación.

## 📂 Estructura del repositorio

- **Ejercicios 1-39**: Problemas prácticos de programación orientada a objetos, manejo de archivos, estructuras de datos y lógica.
- **Proyectos Maven**: Carpetas como `demo`, `Visual`, `Botonventana`, etc., con proyectos configurados para usar Maven y JavaFX.
- **Prácticas y Laboratorios**: Ejercicios de manejo de excepciones, interfaces gráficas, archivos binarios y más.
- **Archivos de configuración**: Incluye varios `pom.xml` para gestión de dependencias y compilación con Maven.

## 🛠️ Habilidades desarrolladas

- Programación orientada a objetos (POO) 👩‍💻
- Manejo de archivos (texto y binarios) 📄
- Uso de colecciones y estructuras de datos (Listas, Vectores, etc.) 📚
- Desarrollo de interfaces gráficas con JavaFX 🖼️
- Gestión de proyectos con Maven ⚙️
- Pruebas unitarias con JUnit 🧪
- Manejo de excepciones y buenas prácticas de codificación 🛡️

## 📘 Detalle de ejercicios y proyectos

### Ejercicios

- **Ejercicio 1**: Cálculo de edad a partir de fecha de nacimiento usando clases y vectores.
- **Ejercicio 10**: Gestión de notas de estudiantes, cálculo de promedio y validación de aprobación.
- **Ejercicio 11 y 12**: Ejercicios introductorios de impresión y estructura básica de un programa Java.
- **Ejercicios 2-9, 13-39**: Diversos problemas de lógica, estructuras de datos, POO, manejo de archivos, excepciones y más (ver carpetas para detalles específicos).

### Proyectos destacados

- **demo/**: Proyecto Maven con JavaFX. Incluye interfaz gráfica básica (`HelloApplication.java`).
- **Visual/**, **Botonventana/**: Proyectos con interfaces gráficas y manejo avanzado de eventos.
- **TRABAJO_CON ARCHIVOS BINARIOS/**: Ejemplo de manejo de archivos binarios y serialización de objetos.
- **MANEJO DE EXCEPCIONES/**: Prácticas sobre el uso y control de excepciones en Java.
- **PRINCIPIOS EN JAVA/**: Ejercicios de fundamentos y buenas prácticas de programación.

## ▶️ ¿Cómo ejecutar los proyectos Java?

### 1. Proyectos simples (Ejercicios)

1. Entra a la carpeta del ejercicio, por ejemplo:
   ```
   cd "Ejercicio 1/src"
   ```
2. Compila el archivo principal:
   ```
   javac Main.java
   ```
3. Ejecuta el programa:
   ```
   java Main
   ```

### 2. Proyectos Maven (con `pom.xml`)

1. Entra a la carpeta del proyecto Maven, por ejemplo:
   ```
   cd demo
   ```
2. Compila y ejecuta con Maven:
   ```
   mvn clean compile
   mvn exec:java -Dexec.mainClass="com.example.demo.HelloApplication"
   ```
   > Cambia el nombre de la clase principal según el proyecto.

3. Para proyectos JavaFX:
   ```
   mvn clean javafx:run
   ```

