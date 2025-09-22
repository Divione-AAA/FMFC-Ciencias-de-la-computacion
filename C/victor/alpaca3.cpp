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

int N, D, K, X ,selfVelociti;

int32_t main() {
    fast

        cin >> N >> D >> K >> X;
        vector<int> velocitis(N);
        int selfVelociti;
        for (int i = 0; i < N; i++) {
            cin >> velocitis[i];
        }
        cin >> selfVelociti;
        sort(velocitis.begin(), velocitis.end());
        int j = 0;
        int i = N - 1;

        while (j < K && i < N) {
            if (velocitis[i] < selfVelociti) {
                i--;
                continue;
            };
            velocitis[i] = velocitis[i] * (100 - X) / 100;
            j++;
            if (j == K && velocitis[i] >= selfVelociti) {
                cout << "NO";
                return 0;
            }
            if (velocitis[i] < selfVelociti)i--;
        }

        if (i == -1 && velocitis[i + 1] < selfVelociti) {
            cout << "YES";
        } else {
            cout << "NO";
        }
        return 0;
    }