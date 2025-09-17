#include <bits/stdc++.h>
using namespace  std;

int main() {
    int N, D , K , X ;
    cin>>N>>D>>K>>X;
    int velocitis[N];
    int selfVelociti;
    for (int i = 0;i<N;i++) {
        cin>>velocitis[i];
    }
    cin>>selfVelociti;
    int j =0;
    int i =0;
    int lastPosition=0;
    while (j<K && i<N) {
        if (velocitis[i]<selfVelociti)continue;
        velocitis[i]=velocitis[i]*(100-X)/100;
        j++;
        lastPosition=i;
        if (velocitis[i]<selfVelociti)i++;
        else if (j==K ) {
        cout<<"NO";
            return 0;
        }
    }

    if (lastPosition==N-1 && velocitis[lastPosition]<selfVelociti) {
        cout<<"YES";
    }else {
        cout<<"NO";
    }



    return 0;
}