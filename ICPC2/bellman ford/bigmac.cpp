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
    int u,v;
    double w;
};

int n,m,v,a,b,t1,t2;
double t3;
vector<edge> adj;

double bellman(){
    vector<double> d(n+1,INF);
    d[a]=0;
    for (int i=0; i<n-1; i++){
        for(auto e: adj){
            if (d[e.u] != INF && d[e.u] + e.w < d[e.v])
                d[e.v] = d[e.u] + e.w;
        }
    }

    return d[b];
}

int32_t main(){
	
	fast
	//freopen("file.in", "r", stdin);freopen("file.out", "w", stdout)
    
    cin>>n>>m>>v>>a>>b;
    adj.resize(n+1);

    loop(i,m){
        cin>>t1>>t2>>t3;
        adj.PB({t1,t2,t3});
        
    }

    //cout<<"hello"<<endl;
    double ans = bellman();
    if(ans==0){cout<<0; return 0;}
    else cout<<fixed<<setprecision(2)<<(ans*v)<<endl;

return 0;}