#include<stdio.h>

struct vaca{
    //no perminten en c las sobrecargas de operadores
};

void calculo(struct vaca *v){

}

int main(){
    struct vaca b;
    calculo(&b);
return 0;}