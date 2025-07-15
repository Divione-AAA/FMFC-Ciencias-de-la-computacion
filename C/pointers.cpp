#include <bits/stdc++.h>
using namespace std;

void mifun(int *c) {

}
void rellena(int *a,int n, int r) {
    for(int i=0;i<n;i++) {
        a[i]=rand()%+r-1;
    }
}

int main() {
    int a = 15;
    int *pa = &a;
    printf("%x ",&pa);//%x es para la direccion de memoria
    printf("%x ",*pa);//es para el valor al que apunta
    mifun(&a);//esto se hace solamente en c, en c++ sirve solo con el ampersand
    //gestion de memoria
    int *arr = (int *)malloc(sizeof(int));//crea un puntero de tipo entero que reserva 32bits continuos de memoria
    int *arr1 =(int *)calloc(5,sizeof(int));//crea 5 elementos inicializados en cero continuos en espacio de memoria
    arr = (int *)realloc(arr,10*sizeof(int));//restablece el tamano de memoria
    free(arr1);//libera la memoria
    arr1 = NULL;//por si las moscas
    //arreglos dinamicos
    int n;//tamano
    scanf("%d",&n);//se lee
    int *v = (int *)malloc(n*sizeof(int));//arreglo de tamano n

return 0;}