# 📚 Estructura de Datos en Java

Este repositorio contiene implementaciones de diversas estructuras de datos y algoritmos en Java, ideales para estudiantes y entusiastas de la informática. Cada clase está diseñada para ilustrar conceptos fundamentales y avanzados de la materia. A continuación se presenta un resumen de cada archivo/clase principal:

## 📦 Clases y Archivos

### 🌳 Árboles
- **ArbolBinario.java**: Implementa un árbol binario clásico, con métodos para inserción, eliminación y recorrido.
- **ArbolDeHuffman.java**: Algoritmo de compresión Huffman, útil para codificación eficiente de datos.
- **ArbolAdyacencia.java, ArbolAL.java, ArbolHijos.java, ArbolLista.java, ArbolMatriz.java, ArbolPair.java**: Variantes de árboles y representaciones de grafos, cada uno con su propia estructura interna y métodos de manipulación.
- **ABB.java**: Árbol Binario de Búsqueda, permite búsquedas, inserciones y eliminaciones eficientes.
- **AVL.java, AVLTree.java**: Árbol AVL, una variante balanceada del ABB para mantener operaciones en tiempo logarítmico.
- **Btree.java**: Implementación de B-Tree, útil para bases de datos y sistemas de archivos.

### 🧮 Estructuras Lineales
- **Lista.java**: Lista enlazada simple, con métodos para agregar, eliminar y buscar elementos.
- **Cola.java, queueLista.java**: Implementaciones de colas (FIFO), tanto con arreglos como con listas.
- **pilaLista.java**: Pila (LIFO) basada en listas enlazadas.

### 🔍 Algoritmos
- **busqueda.java**: Métodos de búsqueda (lineal, binaria, etc.) sobre diferentes estructuras.
- **ordenamiento.java**: Algoritmos de ordenamiento clásicos (burbuja, inserción, selección, quicksort, mergesort).

### 🗃️ Tablas y Hash
- **TablaHash.java**: Tabla hash con manejo de colisiones.
- **DispersionAbierta.java**: Variante de tabla hash con dispersión abierta.
- **Hashable.java**: Interfaz para objetos que pueden ser usados en tablas hash.

### 🧩 Nodos y Utilidades
- **Node.java**: Clase base para nodos en estructuras enlazadas.

## 🗂️ Estructuras Avanzadas
- **Arbol_AVL/**: Carpeta con código fuente y archivos de proyecto para árboles AVL y ABB, incluyendo excepciones personalizadas y ejemplos de uso.

## ⚙️ Cómo ejecutar el proyecto

1. **Requisitos**:
   - Java JDK 8 o superior
   - IDE recomendado: NetBeans, IntelliJ IDEA o VS Code

2. **Compilación**:
   - Desde terminal, navega a la carpeta raíz y ejecuta:
     ```sh
     javac *.java
     ```
   - Para compilar los proyectos en `Arbol_AVL/src`, navega a esa carpeta y ejecuta:
     ```sh
     javac ConjuntoDinamicoABB/*.java ConjuntoDinamicoAVL/*.java excepciones/*.java Exceptions/*.java
     ```

3. **Ejecución**:
   - Ejecuta la clase principal (por ejemplo, `main.java` en las carpetas de ABB o AVL):
     ```sh
     java ConjuntoDinamicoABB.main
     ```

4. **Pruebas**:
   - Puedes crear tus propios archivos de prueba o usar los ejemplos incluidos en las carpetas `test/`.

## 📝 Notas técnicas
- El código está organizado por tipo de estructura y algoritmo.
- Las excepciones personalizadas se encuentran en las carpetas `excepciones/` y `Exceptions/`.
- Se recomienda revisar cada clase para entender sus métodos y atributos.

---

¡Explora, aprende y experimenta con las estructuras de datos! 🚀