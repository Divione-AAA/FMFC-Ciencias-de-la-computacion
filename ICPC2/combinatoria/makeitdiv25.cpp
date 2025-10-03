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

string s;
int n,t,ans=INF;

int cc(){
    int r,p1=-1,p2=-1;
    for(int i=s.size()-1;i>=0;i--){
        if(s[i]=='0' && p1==-1){
            p1=i;
        }else if(s[i]=='0' & p1!=-1){
            p2=i;
            break;
        }
    }
    r=((p1-p2-1)+(s.size()-p1-1));
    return p1!=-1 && p2!=-1 ? r : INF;
}

int vc(){
    int r,p1=-1,p2=-1;
    for(int i=s.size()-1;i>=0;i--){
        if(s[i]=='5' && p1==-1){
            p1=i;
        }else if(s[i]=='2' & p1!=-1){
            p2=i;
            break;
        }
    }
    r=((p1-p2-1)+(s.size()-p1-1));
    //cout<<"kk: "<<p2<<" "<<p1<<endl;
    return p1!=-1 && p2!=-1 ? r : INF;
}

int sc(){
    int r,p1=-1,p2=-1;
    for(int i=s.size()-1;i>=0;i--){
        if(s[i]=='5' && p1==-1){
            p1=i;
        }else if(s[i]=='7' & p1!=-1){
            p2=i;
            break;
        }
    }
    r=((p1-p2-1)+(s.size()-p1-1));
    //cout<<"kk: "<<p2<<" "<<p1<<endl;
    return p1!=-1 && p2!=-1 ? r : INF;
}

int co(){
    int r,p1=-1,p2=-1;
    for(int i=s.size()-1;i>=0;i--){
        if(s[i]=='0' && p1==-1){
            p1=i;
        }else if(s[i]=='5' && p1!=-1){
            p2=i;
            break;
        }
    }
    r=((p1-p2-1)+(s.size()-p1-1));
    return p1!=-1 && p2!=-1 ? r : INF;
}

int32_t main(){
    fast
    //freopen("file.in", "r", stdin);freopen("file.out", "w", stdout);

    cin>>n;
    while(n--){
        cin>>s;
        //cout<<cc()<<" "<<vc()<<" "<<sc()<<" "<<co()<<endl;
        ans=min(cc(),vc());
        ans=min(ans,sc());
        ans=min(ans,co());
        cout<<ans<<endl;
    }

    /*int t;
    cout<<t<<endl;*/

    return 0;
}