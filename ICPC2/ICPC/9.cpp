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
const int MOD = 1000000007;

int dp[251][251];

int n,m,k;

int32_t main(){
    
    fast

    dp[0][0] = 1;
    for (int i = 0; i <= 250; ++i) {
        for (int j = 1; j <= 250; ++j) {
            for (int x = 0; x <= i; ++x) {
                dp[i][j] = (dp[i][j] + dp[i - x][j - 1]) % MOD;
            }
        }
    }

    cin >> m;
    while (m--) {
        cin >> n >> k;
        cout << dp[n][k] << endl;
    }

    return 0;
}
