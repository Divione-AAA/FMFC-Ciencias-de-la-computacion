#include<cstdio>
using namespace std;

int tam;
int arr[10000]={0};
bool v[10000]={0};

void rotate(int len, const int *array, int k) {
    int arreglo[len];
    for (int i = 1; i <= len; i++) {
        if (((i+k)<=len)) {
           arreglo[(i+k)-1] = array[i-1];
        }else{
            arreglo[((i+k)%len)-1] = array[i-1];
        }
    }

    for (int i = 0; i < len; i++) {
        printf("%d ",arreglo[i]);
    }

    array = arreglo;
}

int main() {
    /*printf("Introduzca el tamaño del arreglo\n");
    scanf("%d",&tam);
    int array[tam]={0};
    for (int i=0;i<tam;i++) {
        scanf("%d",&array[i]);
        arr[array[i]]+=1;
    }

    for (int i=0;i<tam;i++) {
        if (v[array[i]]) {continue;}
        printf("El valor de %d se repite %d\n", array[i], arr[array[i]]);
        v[array[i]]=1;
    }*/
    int a[6]={0,1,2,3,4,5};
    rotate(6,a,2);

    return 0;
}