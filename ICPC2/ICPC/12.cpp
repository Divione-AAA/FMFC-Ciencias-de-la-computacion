#include <bits/stdc++.h>
using namespace std;
#define fast ios::sync_with_stdio(0);cin.tie(0);

int main() {
    fast

    int n; cin >> n;
    vector<long long> n1(n + 1);
    vector<vector<int>> adj(n + 1);
    vector<long long> lazy(n + 1, 0);

    for (int i = 1; i <= n; i++) cin >> n1[i];

    for (int i = 0; i < n - 1; i++) {
        int u, v; cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    int q; cin >> q;
    while (q--) {
        int type; cin >> type;
        if (type == 1) {
            int t; long long col;
            cin >> t >> col;
            lazy[t] += col;
        } else {
            int t; cin >> t;
            long long ans = n1[t];
            for (int v : adj[t]) ans += lazy[v];
            cout << ans << '\n';
        }
    }
}
