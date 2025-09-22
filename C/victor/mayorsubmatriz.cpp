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


int n , k , a ;


int32_t main() {
    vector<int> N;
    int cont=0 , min ,max;


    cin>>n>>k;
    for (int i =0;i<n;i++) {
        cin>>a;
        N.push_back(a);
    }
    for (int i =0;i<n;i++) {
        min=i,max=i;
        if (N[max]-N[min]<=k)cont++;

        for (int j=i+1;j<n;j++) {
            if (N[max]<N[j])max=j;
            if (N[min]>N[j])min=j;
            if (N[max]-N[min]<=k)cont++;

        }
    }

    cout<<cont;

    return 0;
}