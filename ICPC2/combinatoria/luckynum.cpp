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

int n;

int ans(){
    int ans=0;
    lup(i,1,n){
        ans+=pow(2,i);
    }
    return ans;
}

int32_t main(){
    fast
    //freopen("file.in", "r", stdin);freopen("file.out", "w", stdout);

    cin>>n;

    //la logica es usar principio multiplicativo y de potencias
    //longitud k para 2 posibles usos, 7 u 8

    cout<<ans()<<endl;

    return 0;
}