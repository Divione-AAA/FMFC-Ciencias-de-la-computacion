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

// Ejercicio 1: Imprime el triangulo de Pascal hasta la posicion (n, k)
void ejercicio1(int n, int k) {
    vector<vector<int>> p(n + 1, vector<int>(n + 1, 0));
    
    loopi(i, n) {
        loopi(j, min(i, k)) {
            if (j == 0 || j == i) {
                p[i][j] = 1;
            } else {
                p[i][j] = p[i - 1][j - 1] + p[i - 1][j];
            }
            cout << p[i][j] << " ";
        }
        cout << "\n";
    }
}

// Auxiliar para el Ejercicio 2 y 4 (sin repetidos)
void comb1(int idx, int k, vector<int>& act, const vector<int>& A, vector<vector<int>>& res) {
    if (act.size() == k) {
        res.PB(act);
        return;
    }
    loup(i, idx, A.size()) {
        act.PB(A[i]);
        comb1(i + 1, k, act, A, res);
        act.pop_back();
    }
}

// Ejercicio 2: Cantidad de subconjuntos de tamano k (elementos unicos)
void ejercicio2(const vector<int>& A, int k) {
    vector<vector<int>> res;
    vector<int> act;
    comb1(0, k, act, A, res);
    cout << "Respuesta = " << res.size() << "\n";
}

// Auxiliar para el Ejercicio 3 y 4 (con repetidos usando un set para evitar duplicados)
void comb2(int idx, int k, vector<int>& act, const vector<int>& A, set<vector<int>>& res) {
    if (act.size() == k) {
        res.INS(act);
        return;
    }
    loup(i, idx, A.size()) {
        act.PB(A[i]);
        comb2(i + 1, k, act, A, res);
        act.pop_back();
    }
}

// Ejercicio 3: Cantidad de subconjuntos de tamano k (con elementos repetidos)
void ejercicio3(vector<int> A, int k) {
    sortt(A);
    set<vector<int>> res;
    vector<int> act;
    comb2(0, k, act, A, res);
    cout << "Respuesta = " << res.size() << "\n";
}

// Ejercicio 4: Muestra no solo la cantidad sino tambien cuales son los subconjuntos
void ejercicio4(vector<int> A, int k, bool tieneRepetidos) {
    if (!tieneRepetidos) {
        vector<vector<int>> res;
        vector<int> act;
        comb1(0, k, act, A, res);
        cout << "Cantidad: " << res.size() << " => ";
        over(sub, res) {
            cout << "{";
            loop(i, sub.size()) {
                cout << sub[i] << (i + 1 < sub.size() ? "," : "");
            }
            cout << "} ";
        }
    } else {
        sortt(A);
        set<vector<int>> res;
        vector<int> act;
        comb2(0, k, act, A, res);
        cout << "Cantidad: " << res.size() << " => ";
        over(sub, res) {
            cout << "{";
            loop(i, sub.size()) {
                cout << sub[i] << (i + 1 < sub.size() ? "," : "");
            }
            cout << "} ";
        }
    }
    cout << "\n";
}

// Auxiliar recursivo para Fibonacci
int fibRec(int n) {
    if (n == 0 || n == 1) return 1;
    return fibRec(n - 1) + fibRec(n - 2);
}

// Ejercicio 5: N-esimo Fibonacci por ambos enfoques (f0=1, f1=1)
void ejercicio5(int n) {
    int r1 = fibRec(n);
    
    int r2 = 1;
    if (n > 1) {
        int a = 1, b = 1;
        lup(i, 2, n) {
            r2 = a + b;
            a = b;
            b = r2;
        }
    }
    
    cout << "Recursivo: " << r1 << " Iterativo: " << r2 << "\n";
}

// Ejercicio 6: Nuevo arreglo con el duplo de la suma acumulada
void ejercicio6(const vector<int>& A) {
    int n = A.size();
    vector<int> R(n);
    int suma = 0;
    
    cout << "Respuesta = [";
    loop(i, n) {
        suma += A[i];
        R[i] = suma * 2;
        cout << R[i] << (i + 1 < n ? ", " : "");
    }
    cout << "]\n";
}

int32_t main(){
    
    fast
    //freopen("file.in", "r", stdin);freopen("file.out", "w", stdout);

    cout << "Ejercicio 1\n";
    ejercicio1(4, 2);

    cout << "\nEjercicio 2\n";
    vector<int> A2 = {1, 2, 3, 4};
    ejercicio2(A2, 2);

    cout << "\nEjercicio 3\n";
    vector<int> A3 = {1, 1, 3, 4};
    ejercicio3(A3, 2);

    cout << "\nEjercicio 4 (Sin repetidos)\n";
    ejercicio4(A2, 2, false);

    cout << "Ejercicio 4 (Con repetidos)\n";
    ejercicio4(A3, 2, true);

    cout << "\nEjercicio 5\n";
    ejercicio5(5);

    cout << "\nEjercicio 6\n";
    vector<int> A6 = {2, 5, 3, 9};
    ejercicio6(A6);

    return 0;
}
