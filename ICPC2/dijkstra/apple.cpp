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

int n,m,s,a1,a2,t1,t2,t3;
vector<vector<pair<int,int>>> adj;

int dijkstra(int st,int i){
	vector<int> d;
	d.assign(n,INF);
	d[st]=0;
	set<pair<int,int>> q;
	q.insert({0,st});
	while(!q.empty()){
		int v = q.begin()->second;
        q.erase(q.begin());

        for (auto edge : adj[v]) {
            int u = edge.first;
            int w = edge.second;

            if (d[v] + w < d[u]) {
                q.erase({d[u], u});
                d[u] = d[v] + w;
                q.insert({d[u], u});
            }
        }
	}
	//cout<<d[i]<<endl;
	/*over(i,d){
		cout<<i<<" ";
	}
	cout<<endl;*/
	return d[i];
}

int32_t main(){
	
	fast
	//freopen("file.in", "r", stdin);freopen("file.out", "w", stdout);
    cin>>n>>m>>s>>a1>>a2;
	s--,a1--,a2--;
	adj.resize(m);
	loop(i,n){
		cin>>t1>>t2>>t3;
		t1--;t2--;
		adj[t1].PB({t2,t3});
		adj[t2].PB({t1,t3});
	}
	/*for(int i=0;i<adj.size();i++){
		cout<<i+1<<": ";
		for(int j=0;j<adj[i].size();j++){
			cout<<adj[i][j].first+1<<" ";
		}
		cout<<endl;
	}*/
	int ans1 = dijkstra(s,a1)+dijkstra(a1,a2);
	int ans2 = dijkstra(s,a2)+dijkstra(a2,a1);
	//cout<<ans1<<" "<<ans2<<endl;
	//cout<<(ans1<ans2) ? ans1 : ans2;
	if(ans1<ans2) cout<<ans1;
	else cout<<ans2;

return 0;}