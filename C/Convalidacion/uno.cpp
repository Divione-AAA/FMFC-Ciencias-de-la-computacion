#include<bits/stdc++.h>
using namespace std;

char l[50];
FILE *m1r = fopen("m1.txt","r");
FILE *m2r = fopen("m2.txt","r");
FILE *m3r = fopen("m3.txt","w");
double m1[10000][10000],m2[10000][10000];
int a,b;

void getmn() {
fgets(l,50,m1r);
sscanf(l,"%f" "%f",&a,&b);
}

void leerm() {
    for(int i=0;i<a;i++) {
        for(int j=0;j<b;j++) {
            if (fscanf(m1r,"%f",&m1[i][j])) {}
            if (fscanf(m2r,"%f",&m2[i][j])) {}
        }
    }
}

void sumar() {
    double mr[a][b];
    for(int i=0;i<a;i++) {
        for(int j=0;j<b;j++) {
            mr[i][j]=m1[i][j]+m2[i][j];
        }
    }
    for(int i=0;i<a;i++) {
        for(int j=0;j<b;j++) {
            fprintf(m3r,"%f",mr[i][j]);
        }
        fprintf(m3r,"\n");
    }
}

int32_t main(){

getmn();
leerm();
sumar();

return 0;}