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

int n,m,s,a1,a2,u,v,w;
vector<vector<int>> adj;

void bellamnFord(){
    
    int src=0;cin>>src;
    vector<int> dist(n,INF);
    dist[src]=0;

    for (int i=0; i<n-1; i++){
        for(auto e: adj){
            int u=e[0];
            int v=e[1];
            int w=e[2];
            dist[v]=min(dist[v],dist[u] + w);
        }
    }
}

int32_t main(){
	
	fast
	//freopen("file.in", "r", stdin);freopen("file.out", "w", stdout);
    cin>>n>>m>>s>>a1>>a2;
	s--,a1--,a2--;
	adj.resize(m);
	loop(i,n){
		cin>>u>>v>>w;
		u--;v--;
		adj.push_back({u,v,w});
        adj.push_back({v,u,w});
	}

return 0;}