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

struct edge{
    int u,v,w;
    bool operator<(edge other){
        return w < other.w;
    }
};

int n,m;
vector<int> parent,_rank;
vector<edge> adj,result;

void _union(int u){
    parent[u] = u;
}

int _find(int u){
    if(u == parent[u]) return parent[u];
    else return parent[u] = _find(parent[u]);
}

void _union_sets(int u, int v){
    u = _find(u);
    v = _find(v);
    if(u!=v) parent[u] = v;
}

void kurskal(){
    int cost = 0;
    parent.resize(n);
    _rank.resize(n);

    for(int i=0;i<n;i++){
        _union(i);
    }

    sortt(adj);

    over(i,adj){
        if(_find(i.u) != _find(i.v)){
            cost+=i.w;
            result.PB(i);
            _union_sets(i.u,i.v);
        }
    }
}

int32_t main(){
	
	fast
	//freopen("file.in", "r", stdin);freopen("file.out", "w", stdout);
    cin>>n>>m;
    int u,v,w;
    adj.resize(n);
    loop(i,m){
        cin>>u>>v>>w;
        adj.PB({u,v,w});
        adj.PB({u,v,w});
    }

    kurskal();
    
    int ans = 0;
    over(i,result){
        //cout<<i.w<<" ";
        ans+=i.w;
    }

    cout<<ans;

return 0;}