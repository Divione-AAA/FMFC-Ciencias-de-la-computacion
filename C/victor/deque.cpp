#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, k;
    cin >> n >> k;
    vector<int> N(n);
    for (int i = 0; i < n; i++) cin >> N[i];

    deque<int> minQ, maxQ; // guardan índices
    long long cont = 0;
    int l = 0;

    for (int r = 0; r < n; r++) {
        // mantener minQ creciente
        while (!minQ.empty() && N[minQ.back()] >= N[r]) minQ.pop_back();
        minQ.push_back(r);

        // mantener maxQ decreciente
        while (!maxQ.empty() && N[maxQ.back()] <= N[r]) maxQ.pop_back();
        maxQ.push_back(r);

        // ajustar l si no cumple condición
        while (!minQ.empty() && !maxQ.empty() && (N[maxQ.front()] - N[minQ.front()] > k)) {
            if (minQ.front() == l) minQ.pop_front();
            if (maxQ.front() == l) maxQ.pop_front();
            l++;
        }

        // todos los subarreglos [l..r], [l+1..r], ..., [r..r] cumplen
        cont += (r - l + 1);
    }

    cout << cont << "\n";
    return 0;
}
