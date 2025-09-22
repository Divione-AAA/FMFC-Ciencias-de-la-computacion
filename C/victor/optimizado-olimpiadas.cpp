#include <bits/stdc++.h>
using namespace std;

struct Punto {
    int x, y, c;
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<Punto> coordenadas(n);
    for (int i = 0; i < n; i++) {
        cin >> coordenadas[i].x >> coordenadas[i].y >> coordenadas[i].c;
    }

    int mejor = 0;
    vector<char> visited(n);

    for (int start = 0; start < n; start++) {
        fill(visited.begin(), visited.end(), 0);

        queue<int> q;
        q.push(start);
        visited[start] = 1;

        int total = coordenadas[start].c;

        while (!q.empty()) {
            int u = q.front(); q.pop();
            const auto &cur = coordenadas[u];

            for (int v = 0; v < n; v++) {
                if (visited[v]) continue;
                const auto &[x, y, c] = coordenadas[v];

                if (abs(cur.x - x) + abs(cur.y - y) == 1) {
                    if (c >= 0) {
                        q.push(v);
                        visited[v] = 1;
                        total += c;
                    }
                }
            }
        }
        mejor = max(mejor, total);
    }

    cout << mejor << "\n";
    return 0;
}

