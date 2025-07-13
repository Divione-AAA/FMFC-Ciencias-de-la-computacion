#include <bits/stdc++.h>
using namespace std;

bool a[10000]={true};
int b[100];
void criba() {
    a[0]=a[1]=a[2]=true;
    for (int i=3;i<10000;i++) {
        if (a[i]==true) {
            for (int j=i;j<10000;j+=i) {
                a[j]=false;
            }
        }
    }
}

void escribir() {
    int t=0;
    for (int i=1;i<10000;i++) {
        if (a[i]==true) {
            b[t]=i;
            t++;
        }
        if (t==100) {
            break;
        }
    }
}

int main() {

criba();
escribir();

return 0;}