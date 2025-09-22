#include <iostream>
#include <vector>
#include<bits/stdc++.h>
#include <queue>
#include <cmath>

using namespace std;

#define loop(i,n) for(int i=0;i<n;i++)
#define loopi(i,n) for(int i=0;i<=n;i++)
#define lup(i,x,n) for(int i=x;i<=n;i++)
#define loup(i,x,n) for(int i=x;i<n;i++)
#define fast ios_base::sync_with_stdio(0);cin.tie(0);cout.tie(0);
#define ll long long
// #define int long long
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



int32_t main() {
    vector<array<int,4>> coordenadas;
    int n , x , y , c , ant=0, total;
    queue<array<int,4>> agregados;
    array<int,4> cur;
    array<int,4> cur2;

    cin>>n;
    for (int i =0;i<n;i++) {
        cin>>x>>y>>c;
        coordenadas.push_back({x,y,c,0});
    }

    for (int i=0;i<n;i++) {
        agregados.push(coordenadas[i]);
        total=coordenadas[i][2];
        while (!agregados.empty()) {
            cur=agregados.front();
            agregados.pop();

            for (int j=0;j<n;j++) {
                cur2=coordenadas[j];
                if (cur2[3]==1)continue;

                if (abs(cur[0] - cur2[0]) + abs(cur[1] - cur2[1]) == 1) {
                    if (cur2[2]>=0) {
                        agregados.push(cur2);
                        coordenadas[i][3]=1;
                        total+=cur2[2];
                    }
                }
            }
            if (ant<total) {
                ant=total;
            }
        }
        for (int i =0;i<n;i++) {
            coordenadas[i][3]=0;
        }
    }

    cout<<ant;

    return 0;
}