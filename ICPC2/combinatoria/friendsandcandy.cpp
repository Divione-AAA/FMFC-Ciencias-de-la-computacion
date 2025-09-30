#include <bits/stdc++.h>
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

vector<int> a;
int c,n,tmp,s,g,ans;
bool f;

bool equal(){
    int x = a[0];
    over(i,a){
        if(i!=x){
            return false;
        }
        x=i;
    }
    return true;
}

int32_t main(){

    cin>>c;
    while(c--){
        cin>>n;
        tmp=-1.1;
        s=g=0;
        ans=0;
        a.clear();
        a.resize(n);
        loop(i,n){
            cin>>a[i];
            tmp=a[i];
            s+=a[i];
        }

        if(s%n!=0){
            cout<<-1<<endl;
            continue;
        }

        if(equal() || n==1){
            cout<<0<<endl;
            continue;
        }

        sortt(a);
        s=s/n;
        
        for(int i=a.size()-1;i>0;i--){
            
            if(a[i]>s){
                ans++;
            }
        }
        cout<<ans<<endl;
    }

return 0;}