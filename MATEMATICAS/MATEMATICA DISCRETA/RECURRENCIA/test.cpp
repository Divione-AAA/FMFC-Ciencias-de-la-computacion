#include<bits/stdc++.h>
using namespace std;

#define sortt(a) sort(a.begin(),a.end())


struct tstring{
    string t;
    bool operator<(tstring &other){
        return t.length()<other.t.length();
    }
};

vector<tstring> cadenas;

int a(int i){
    if(i<0) return 0;
    if(i==0 || i==1) return 1;
    if(i==2) return 2;
    //cout<<i<<endl;
    return 2*a(i-1)*a(i-2);
}

int fibonacci(int i){
    if(i==0) return 0;
    if(i==1 || i==2) return 1;
    return fibonacci(i-1)+fibonacci(i-2);
}

int b(int i){
    if(i==0 || i==1) return 1;
    //cout<<i<<endl;
    return pow(2,fibonacci(i)-1);
}

void binario(tstring i){
    if(i.t.size()==6) return;
    cadenas.push_back(i);
    binario({i.t+'0'});
    binario({i.t+'1'});
}

int main(){
    //for(int i=0;i<20;i++){
        //cout<<pow(2,i)<<endl;
    //}
    //cout<<fibonacci(6)<<endl;
    //cout<<b(6)<<endl;
    binario({""});
    sortt(cadenas);
    for(auto i: cadenas){
        cout<<i.t<<endl;
    }
}