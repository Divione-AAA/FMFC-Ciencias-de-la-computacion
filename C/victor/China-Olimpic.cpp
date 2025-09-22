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



int32_t main() {
    vector<array<int,5>> coordenadas;
    int n , x , y , c , ant=-99999999;
    queue<array<int,5>> q;
    array<int,5> cur;
    array<int,5> cur2;
    vector<array<int,5>> mayorCamino;
    vector<array<int,5>> caminoActual;

    cin>>n;
    for (int i =0;i<n;i++) {
        cin>>x>>y>>c;
        coordenadas.push_back({x,y,c,0,0});
    }

    for (int j =0;j<n;j++) {
        q.push(coordenadas[j]);
        int total = coordenadas[j][2];

        while (!q.empty()) {
            cur=q.front();
            q.pop();
            int x1=cur[0],y1=cur[1],c1=cur[2];
            total+=c1;

            for (int i=0;i<n;i++) {
                if (i==j)continue;
                cur2=coordenadas[i];
                if (cur2[3]==1)continue;

                int x2=cur2[0],y2=cur2[1],c2=cur2[2];
                if (abs(x1 - x2) + abs(y1 - y2) == 1) {
                    if (c2>=0) {
                        q.push(cur2);
                        coordenadas[i][3]=1;
                        total+=c2;
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