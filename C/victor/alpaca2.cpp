#include <iostream>
#include <vector>
#include<bits/stdc++.h>
using namespace std;

#define loop(i,n) for(int i=0;i<n;i++)
#define loopi(i,n) for(int i=0;i<=n;i++)
#define lup(i,x,n) for(int i=x;i<=n;i++)
#define loup(i,x,n) for(int i=x;i<n;i++)
#define fast ios_base::sync_with_stdio(0);cin.tie(0);cout.tie(0);
#define ll long long
#define int long long
#define double long double
#define endl '\n'
#define over(i,a) for(auto i: a)
#define F first
#define S second
#define MP make_pair
#define PB push_back
#define PF push_front
#define INS insert
#define sortt(a) sort(a.begin(),a.end())

const int INF=4294967296;
const int _INF=-4294967296;
const double pi=3.1415926535;


using namespace std;
int N, D, K, X ,selfVelociti;

 int32_t main() {
    fast
    cin >> N >> D >> K >> X;
    vector<int> velocitis(N);
    for (int i = 0; i < N; i++) {
        cin >> velocitis[i];
    }
    cin >> selfVelociti;

    int j = 0;
    for (int i=0;i<N && j< K ;i++) {
        while (velocitis[i] >= selfVelociti) {
            velocitis[i] = (velocitis[i] * (100 - X)) / 100;
             j++;
        }
    }
   for ( int i=0;i<N;i++) {
       if (velocitis[i]>=selfVelociti) {
           cout<<"NO"<<endl;
           return 0;
       }
   }
    cout<<"YES"<<endl;

    return 0;
}