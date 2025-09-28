#include <bits/stdc++.h>
using namespace std;

#define fast ios_base::sync_with_stdio(0);cin.tie(0);cout.tie(0);
#define int long long

const int MAXN = 10000;
int parent[MAXN], tam[MAXN];

int find(int u) {
    if (parent[u] != u) return parent[u] = find(parent[u]);
    return u;
}

void unite(int u, int v) {
    u = find(u);
    v = find(v);
    if (u != v) {
        parent[v] = u;
        tam[u] += tam[v];
    }
}

int32_t main() {
    fast
    int c, f;
    cin >> c;

    while (c--) {
        cin >> f;

        for (int i = 0; i < MAXN; ++i) {
            parent[i] = i;
            tam[i] = 1;
        }

        map<string, int> nombre_a_id;
        int id = 0;

        while (f--) {
            string a, b;
            cin >> a >> b;

            if (!nombre_a_id.count(a)) nombre_a_id[a] = id++;
            if (!nombre_a_id.count(b)) nombre_a_id[b] = id++;

            unite(nombre_a_id[a], nombre_a_id[b]);
            cout << tam[find(nombre_a_id[a])] << endl;
        }
    }

    return 0;
}
