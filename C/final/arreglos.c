#include <stdio.h>
#include <stdlib.h>

// Declaración de un array global con tamaño variable usando punteros
int *arrayGlobal = NULL;
int tamArrayGlobal = 0;

// Función para inicializar el array global
void inicializarArrayGlobal(int tam) {
    arrayGlobal = (int*)malloc(tam * sizeof(int));
    tamArrayGlobal = tam;
    // Inicializar con ceros (opcional)
    for (int i = 0; i < tam; i++) {
        arrayGlobal[i] = 0;
    }
}

// Función para liberar el array global
void liberarArrayGlobal() {
    free(arrayGlobal);
    arrayGlobal = NULL;
    tamArrayGlobal = 0;
}

// Función para imprimir un array estático
void imprimirArray(int arr[], int tam) {
    printf("Array estatico: ");
    for (int i = 0; i < tam; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

// Métodos para arrays dinámicos
typedef struct {
    int *datos;
    int tam;
    int capacidad;
} ArrayDinamico;

// Inicializa el array dinámico
void inicializar(ArrayDinamico *a, int capacidad) {
    a->datos = (int*)malloc(capacidad * sizeof(int));
    a->tam = 0;
    a->capacidad = capacidad;
}

// Agrega un elemento al final
void agregar(ArrayDinamico *a, int valor) {
    if (a->tam == a->capacidad) {
        a->capacidad *= 2;
        a->datos = (int*)realloc(a->datos, a->capacidad * sizeof(int));
    }
    a->datos[a->tam++] = valor;
}

// Elimina el último elemento
void eliminarUltimo(ArrayDinamico *a) {
    if (a->tam > 0) {
        a->tam--;
    }
}

// Imprime el array dinámico
void imprimirDinamico(ArrayDinamico *a) {
    printf("Array dinamico: ");
    for (int i = 0; i < a->tam; i++) {
        printf("%d ", a->datos[i]);
    }
    printf("\n");
}

// Libera la memoria
void liberar(ArrayDinamico *a) {
    free(a->datos);
    a->datos = NULL;
    a->tam = 0;
    a->capacidad = 0;
}

int main() {
    // Inicializar el array global con tamaño variable
    inicializarArrayGlobal(7);
    printf("Array global: ");
    for (int i = 0; i < tamArrayGlobal; i++) {
        printf("%d ", arrayGlobal[i]);
    }
    printf("\n");

    // Array estático
    int arr[5] = {1, 2, 3, 4, 5};
    imprimirArray(arr, 5);

    // Array dinámico
    ArrayDinamico ad;
    inicializar(&ad, 2);
    agregar(&ad, 10);
    agregar(&ad, 20);
    agregar(&ad, 30);
    imprimirDinamico(&ad);

    eliminarUltimo(&ad);
    imprimirDinamico(&ad);

    liberar(&ad);

    // Ejemplo de calloc
    int *arrayCalloc = (int*)calloc(5, sizeof(int));
    printf("Array con calloc (inicializado a cero): ");
    for (int i = 0; i < 5; i++) {
        printf("%d ", arrayCalloc[i]);
    }
    printf("\n");
    free(arrayCalloc);

    // Ejemplo de realloc
    int *arrayRealloc = (int*)malloc(3 * sizeof(int));
    for (int i = 0; i < 3; i++) arrayRealloc[i] = i + 1;
    printf("Array antes de realloc: ");
    for (int i = 0; i < 3; i++) printf("%d ", arrayRealloc[i]);
    printf("\n");
    arrayRealloc = (int*)realloc(arrayRealloc, 6 * sizeof(int));
    for (int i = 3; i < 6; i++) arrayRealloc[i] = (i + 1) * 10;
    printf("Array despues de realloc: ");
    for (int i = 0; i < 6; i++) printf("%d ", arrayRealloc[i]);
    printf("\n");
    free(arrayRealloc);

    // Array multidimensional
    int matriz[2][3] = {{1,2,3},{4,5,6}};
    printf("Array multidimensional:\n");
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 3; j++) {
            printf("%d ", matriz[i][j]);
        }
        printf("\n");
    }

    liberarArrayGlobal();
    return 0;
}