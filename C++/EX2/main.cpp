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

    int d=0,t=0,c=0,f=0;
    int n,todo=0;

    while(cin>>n){
        
        if(n==2){
            d++;
        }else if(n==3){
            t++;
        }else if(n==4){
            c++;
        }else{
            f++;
        }
        todo++;
    }

    cout<<"el porciento de dos fue de "<<float((100*d)/todo)<<endl
        <<"el porciento de tres fue de "<<float((100*t)/todo)<<endl
        <<"el porciento de cuatro fue de "<<float((100*c)/todo)<<endl
        <<"el porciento de cinco fue de "<<float((100*f)/todo)<<endl;

return 0;}