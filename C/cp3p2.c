#include<stdio.h>

int orden(int a,int b,int c){
    return a<=b && b<=c ? 1 : 0;
}

void div(int a,int b){
    int m = a>b ? a : b;
    for (int i=2;i<=m;i++) {
        if (a%i==0 && b%i==0) {
            printf("%d ",i);
            printf("\n");
        }
    }
}

int gcd(int a,int b){
    return b ? gcd(b,a%b) : a;
}

int factorial(int a){
    return a==1 ? 1 : a*factorial(a-1);
}

char mayus(char a){
    return a-32;
}

int main(){
    int a,b,c;
    scanf("%d %d %d",&a,&b,&c);
    printf(orden(a,b,c) ? "esta ordenado" : "no esta ordenado");
    printf("%c",mayus('o'));
}