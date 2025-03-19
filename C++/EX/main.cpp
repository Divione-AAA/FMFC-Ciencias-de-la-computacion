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

int ans=0;

int32_t main(){

    fast

    int a;
    cin>>a;

    while(a>=100){

        int d1=(a/10)%10;
        int d2=(a/100);
        int d3=a%10;

        if(pow(d1,3)+pow(d2,3)+pow(d3,3)==a){
            ans++;
        }
        
        cin>>a;
    }

    cout<<ans<<endl;

return 0;}