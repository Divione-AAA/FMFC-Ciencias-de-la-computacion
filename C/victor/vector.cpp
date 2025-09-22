#include <iostream>
using namespace std;

class Vector {
    //aqui el vector es un puntero que va a apuntar al inicio de un array de enteros
    int *vector_;
    //este es el tamano del vector
    int sz;

public:
    // Constructor: crea un vector de tamaño n (inicializa a 0)
    Vector(int n) : sz(n) {
        //si el tamano pasado es cero o menor se crea un nullptr
        if (n <= 0) {
            sz = 0;
            vector_ = nullptr;
        } else {
            //sino inicializamos el vector y le damos valor
            vector_ = new int[sz];
            for (int i = 0; i < sz; ++i) vector_[i] = 0;
        }
    }

    // Constructor copia
    Vector(const Vector& other) : sz(other.sz) {
        if (sz > 0) {
            vector_ = new int[sz];
            for (int i = 0; i < sz; ++i) vector_[i] = other.vector_[i];
        } else vector_ = nullptr;
    }

    // Operador = (asignación)
    //sobrecarga del operador de asignacion para hacer una copia profunda
    Vector& operator=(const Vector& other) {
        //evitamos la reasignacion
        if (this == &other) return *this;
        //liberamos memoria
        delete[] vector_;
        sz = other.sz;
        if (sz > 0) {
            vector_ = new int[sz];
            for (int i = 0; i < sz; ++i) vector_[i] = other.vector_[i];
        } else vector_ = nullptr;
        return *this;
    }

    // Destructor
    ~Vector() {
        //en el destructor liberamos memoria
        delete[] vector_;
    }

    //simplemente retornamos el tamano
    int Size()  { return sz; }

    // IntroduceValor(pos, val) -> pos en base 0
    void IntroduceValor(int pos, int val) {
        //metodo para introducir un valor
        if (pos < 0 || pos >= sz) {
            cout << "Indice fuera de rango: " << pos << '\n';
            return;
        }
        vector_[pos] = val;
    }

    // LeeValor(pos)
    int LeeValor(int pos) {
        if (pos < 0 || pos >= sz) {
            cout << "Indice fuera de rango: " << pos << '\n';
            return 0; // o lanzar excepción en enfoque avanzado
        }
        return vector_[pos];
    }
};

// Ejemplo de uso
int main() {
    Vector v(5); // vector de 5 elementos (0..4)
    v.IntroduceValor(0, 10);
    v.IntroduceValor(4, 20);

    cout << "Size: " << v.Size() << '\n';
    cout << "v[0] = " << v.LeeValor(0) << '\n';
    cout << "v[4] = " << v.LeeValor(4) << '\n';
    // intento fuera de rango
    v.IntroduceValor(5, 7);
    return 0;
}
