#include <cstdio>
using namespace std;

void ordenar(int *a,int l) {
    for (int i = 0; i < l; i++) {
        for (int j = i; j < l; j++) {
            if (a[i] > a[j]) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
    }
}

void juntar(int *a,int *b,int l) {
    int c[l*2];
    int t=0;
    for (int i = 0; i < l; i++) {
        c[t]=a[i];
        c[t+1]=b[i];
        t+=2;
    }
}

int main() {
    return 0;
}
