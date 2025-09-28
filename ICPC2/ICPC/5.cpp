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
	
	fast
	//freopen("file.in", "r", stdin);freopen("file.out", "w", stdout);

    int n;cin>>n;
    vector<int> A(n),B(n),C(n),D(n);

    for(int i=0;i<n;i++){
        cin>>A[i]>>B[i]>>C[i]>>D[i];
    }

    /*for(int i=0;i<n;i++){
        cout<<a[i][0]<<" "<<a[i][1]<<" "<<a[i][2]<<" "<<a[i][3]<<endl;
    }*/

    unordered_map<int,int> m;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            m[A[i]+B[j]]++;
        }
    }

    int ans=0;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            ans+=m[-(D[i]+C[j])];
        }
    }

    cout<<ans;

return 0;}