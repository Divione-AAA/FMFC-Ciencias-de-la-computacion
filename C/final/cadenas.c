#include <stdio.h>
#include <string.h>
#include <ctype.h>

// Copia una cadena de origen a destino
void ejemplo_strcpy() {
    char origen[] = "Hola mundo";
    char destino[50];
    strcpy(destino, origen);
    printf("strcpy: %s\n", destino);
}

// Concatena dos cadenas
void ejemplo_strcat() {
    char saludo[50] = "Hola";
    char nombre[] = " Juan";
    strcat(saludo, nombre);
    printf("strcat: %s\n", saludo);
}

// Compara dos cadenas
void ejemplo_strcmp() {
    char a[] = "abc";
    char b[] = "abd";
    int resultado = strcmp(a, b);
    printf("strcmp: %d\n", resultado); // <0 si a<b, 0 si a==b, >0 si a>b
}

// Calcula la longitud de una cadena
void ejemplo_strlen() {
    char texto[] = "cadena";
    printf("strlen: %zu\n", strlen(texto));
}

// Busca un caracter en una cadena
void ejemplo_strchr() {
    char texto[] = "cadena";
    char *ptr = strchr(texto, 'd');
    if (ptr) {
        printf("strchr: '%c' encontrado en posicion %ld\n", *ptr, ptr - texto);
    }
}

// Busca una subcadena en una cadena
void ejemplo_strstr() {
    char texto[] = "Hola mundo";
    char *ptr = strstr(texto, "mun");
    if (ptr) {
        printf("strstr: subcadena encontrada en posicion %ld\n", ptr - texto);
    }
}

// Convierte una cadena a mayúsculas
void ejemplo_strupr() {
    char texto[] = "hola";
    for (int i = 0; texto[i]; i++) {
        texto[i] = toupper(texto[i]);
    }
    printf("strupr: %s\n", texto);
}

// Convierte una cadena a minúsculas
void ejemplo_strlwr() {
    char texto[] = "HOLA";
    for (int i = 0; texto[i]; i++) {
        texto[i] = tolower(texto[i]);
    }
    printf("strlwr: %s\n", texto);
}

// Copia n caracteres de una cadena a otra
void ejemplo_strncpy() {
    char origen[] = "Hola mundo";
    char destino[20];
    strncpy(destino, origen, 4);
    destino[4] = '\0'; // Asegurar terminación
    printf("strncpy: %s\n", destino);
}

// Compara n caracteres de dos cadenas
void ejemplo_strncmp() {
    char a[] = "abcdef";
    char b[] = "abcxyz";
    int resultado = strncmp(a, b, 3);
    printf("strncmp: %d\n", resultado);
}

// Divide una cadena en tokens usando delimitadores
void ejemplo_strtok() {
    char texto[] = "uno,dos,tres";
    char *token = strtok(texto, ",");
    printf("strtok: ");
    while (token != NULL) {
        printf("[%s] ", token);
        token = strtok(NULL, ",");
    }
    printf("\n");
}
