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

int n,c;

int ans(int s){
	if(s<=c || (s+c)%2) return 1;
	//si el valor de s no puede dividirse en 2 caminos cuya diferencia sea c
	//o si no el valor no puede dividirse en 2 caminos que difieran se retorna 1 por el grupo
	int x = (s+c)/2;
	//resultado de un sistema de 2 con 2 en el que x+y=n x-y=d para n el total y d la diferencia
	return ans(x)+ans(n-x);
	//aqui la bifurcacion para x y el valor de y
}

int32_t main(){
	
	fast
	//freopen("file.in", "r", stdin);freopen("file.out", "w", stdout);
    cin>>n>>c;
	cout<<ans(n);

return 0;}