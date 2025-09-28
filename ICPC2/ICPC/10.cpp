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

int c ,n ;
int solve(int s,int t){
    if(s<0) return 0;
    if(s==0 && t==n*2 && t!=0) return 1;
    if(t>n*2) return 0;
    return solve(s+1,t+1)+solve(s-1,t+1);
}

int32_t main(){
    
    fast
    cin>>c;
    loop(i,c){
        cin>>n;
        cout<<solve(0,0)%1000000007<<endl;
    }


return 0;}