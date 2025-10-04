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


int32_t main(){
    vector<vector<int>> adyacencia(10000);
    map <int,int> nodos;
    int t , col , fil;

    fast
    //freopen("file.in", "r", stdin);freopen("file.out", "w", stdout);

    int n;cin>>n;
    loop(i,n){
        cin>>t;
        nodos.insert({i,t});
    }
    loop(i,n-1){
        cin>>fil>>col;
        adyacencia[fil].push_back(col);
        adyacencia[col].push_back(fil);
    }

    int a;cin>>a;
    loop(i,a){
        cin>>fil;
        if(fil==1){
            cin>>t>>col;
            cout<<t;
            for(int j = 0 ; j < adyacencia[t].size() ; j++){
                cout<<adyacencia[t][j];
                //nodos.
                cout<<nodos.at(adyacencia[t][j])<<endl;
        }
        }else{
            cin>>t;
            cout<<nodos.at(t)<<endl;
        }
    }

    return 0;
}