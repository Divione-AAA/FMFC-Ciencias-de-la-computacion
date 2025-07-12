#include<bits/stdc++.h>
using namespace std;

struct vaca{
    string nombre;
    double peso;

    bool operator<(const vaca &other) {
        return peso < other.peso;
    }

    bool operator==(const vaca &other) {
        return nombre == other.nombre && peso == other.peso;
    }
};

int main() {
    FILE *f = fopen("texto.txt","r");
    char c;
    //Lectura
    while ((c = fgetc(f) )!= EOF) {
        printf("%c",c);
    }
    FILE *fw = fopen("texto.txt","w");
    char str[]="Blackpink in your area";
    //escritura
    if (fw!=NULL) {
        if (fputs(str,fw)<0) {
            printf("Error writing to file");
        }
    }

    return 0;
}