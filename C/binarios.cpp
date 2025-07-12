#include<bits/stdc++.h>
using namespace std;

struct student {
    int id;
    string name;
};

int main() {
    FILE *f = fopen("texto.txt", "wb");
    FILE *fr = fopen("texto.txt", "rb");
    student t = {1, "Fulano"};
    student r;
    //fwrite("Hola",sizeof("Hola"),1,f);
    fwrite(&t,sizeof(t),1,f);
    fread(&r,sizeof(r),1,fr);
    fclose(f);
    return 0;
}