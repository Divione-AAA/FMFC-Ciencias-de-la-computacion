#include<bits/stdc++.h>
using namespace std;

struct vaca{
    string nombre;
    double peso{};

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
    while ((c = fgetc(f) )!= EOF) {
        printf("%c",c);
    }
    return 0;
}