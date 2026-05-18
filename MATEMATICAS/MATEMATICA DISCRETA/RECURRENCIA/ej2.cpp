#include<bits/stdc++.h>
using namespace std;

int ans(int s){
    if(s>50) return 0;
    else if(s==50) return 1;
    return ans(s+5) + ans(s+10) + ans(s+15) + ans(s+20);
}

int ans2(int s) {
    if (s < 0) return 0;   // no hay forma si se pasa del límite
    if (s == 0) return 1;  // una forma: secuencia vacía
    return ans2(s - 5) + ans2(s - 10) + ans2(s - 15) + ans2(s - 20);
}

int main(){
    
return 0;}