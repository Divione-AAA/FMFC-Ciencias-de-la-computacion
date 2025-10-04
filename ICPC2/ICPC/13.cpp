#include <bits/stdc++.h>
using namespace std;

#define loop(i, n) for (int i = 0; i < n; i++)
#define loopi(i, n) for (int i = 0; i <= n; i++)
#define lup(i, x, n) for (int i = x; i <= n; i++)
#define loup(i, x, n) for (int i = x; i < n; i++)
#define fast                      \
    ios_base::sync_with_stdio(0); \
    cin.tie(0);                   \
    cout.tie(0);
#define ll long long
#define int long long
#define double long double
#define endl '\n'
#define over(i, a) for (auto i : a)
#define F first
#define S second
#define MP make_pair
#define PB push_back
#define PF push_front
#define INS insert
#define sortt(a) sort(a.begin(), a.end())

const int INF = 4294967296;
const int _INF = -4294967296;
const double pi = 3.1415926535;

int t1, t2;

int32_t main()
{
    fast
            // freopen("file.in", "r", stdin);freopen("file.out", "w", stdout);

            cin >>
        t1 >> t2;
    int ans1 = -1, ans2 = -1, sum = 0;

    int i = t1;
    int k = t2;
    while (i < k)
    {
        if(ans1!=-1 && ans2!=-1)break;
        if (ans1 == -1)
        {
            sum = 0;
            for (int j = 2; j <= sqrt(i); j++)
            {
                if (i % j == 0)
                {
                    sum += j;
                    sum += (i / j);
                }
            }
            if (sum > i)
            {
                ans1 = i;
            }
            i++;
        }
        if (ans2 == -1)
        {
            sum = 0;
            for (int j = 2; j <= sqrt(k); j++)
            {
                if (k % j == 0)
                {
                    sum += j;
                    sum += (i / j);
                }
            }
            if (sum > k)
            {
                ans2 = k;
            }
            k--;
        }
    }

    cout << ans1 << " " << ans2 << endl;

    return 0;
}