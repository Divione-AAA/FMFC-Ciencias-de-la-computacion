#include<bits/stdc++.h>
using namespace std;

int notas[20],c;

void leer() {
    for(int i=0;i<20;i++) {
        scanf("%d",&notas[i]);
    }
}

void mostrar() {
    for(int i=0;i<20;i++) {
        printf("%d ",notas[i]);
    }
    printf("\n");
}

void media() {
    double media;

    for(int i=0;i<20;i++) {
        media+=notas[i];
    }
    media=media/20;
    printf("Media = %.2f",media);
    printf("\n");
}

void menor() {
    int menor=0;
    for(int i=0;i<20;i++) {
        menor=min(notas[i],menor);
    }
    printf("Menor = %d",menor);
    printf("\n");
}

void mayor() {
    int mayor=0;
    for(int i=0;i<20;i++) {
        mayor=max(notas[i],mayor);
    }
    printf("Mayor = %d",mayor);
    printf("\n");
}

int32_t main() {

printf("Introduzca las notas de 20 alumnos de 2-5 \n");
leer();
bool b=true;
while (b) {
    printf("Seleccione una opcion: \n");
    printf("1. Leer money \n");
    printf("2. Leer money \n");
    printf("3. Leer money \n");
    printf("4. Leer money \n");
    printf("5. Leer money \n");
    scanf("%d",&c);
    switch(c) {
        case 1:
            mostrar();
            break;
        case 2:
            media();
            break;
        case 3:
            menor();
            break;
        case 4:
            mayor();
            break;
        default:
            b=false;
    }
}

return 0;}