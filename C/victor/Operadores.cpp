#include <iostream>
#include <string>
using namespace std;

class Numero {
    int valor;

public:
    Numero(int v = 0) : valor(v) {}

    // =============================
    // 1. OPERADORES ARITMÉTICOS
    // =============================

    // + binario
    Numero operator+(const Numero& otro) const {
        return Numero(valor + otro.valor);
    }

    // - binario
    Numero operator-(const Numero& otro) const {
        return Numero(valor - otro.valor);
    }

    // * multiplicación
    Numero operator*(const Numero& otro) const {
        return Numero(valor * otro.valor);
    }

    // / división
    Numero operator/(const Numero& otro) const {
        return Numero(valor / otro.valor);
    }

    // % módulo
    Numero operator%(const Numero& otro) const {
        return Numero(valor % otro.valor);
    }

    // + unario
    Numero operator+() const {
        return Numero(+valor);
    }

    // - unario
    Numero operator-() const {
        return Numero(-valor);
    }

    // ++ prefijo
    Numero& operator++() {
        ++valor;
        return *this;
    }

    // ++ sufijo
    Numero operator++(int) {
        Numero temp = *this;
        valor++;
        return temp;
    }

    // -- prefijo
    Numero& operator--() {
        --valor;
        return *this;
    }

    // -- sufijo
    Numero operator--(int) {
        Numero temp = *this;
        valor--;
        return temp;
    }

    // =============================
    // 2. OPERADORES DE RELACIÓN
    // =============================

    bool operator==(const Numero& otro) const { return valor == otro.valor; }
    bool operator!=(const Numero& otro) const { return valor != otro.valor; }
    bool operator<(const Numero& otro)  const { return valor <  otro.valor; }
    bool operator>(const Numero& otro)  const { return valor >  otro.valor; }
    bool operator<=(const Numero& otro) const { return valor <= otro.valor; }
    bool operator>=(const Numero& otro) const { return valor >= otro.valor; }

    // =============================
    // 3. LÓGICOS Y BIT A BIT
    // =============================

    bool operator!() const { return !valor; }
    bool operator&&(const Numero& otro) const { return valor && otro.valor; }
    bool operator||(const Numero& otro) const { return valor || otro.valor; }

    Numero operator~() const { return Numero(~valor); }
    Numero operator&(const Numero& otro) const { return Numero(valor & otro.valor); }
    Numero operator|(const Numero& otro) const { return Numero(valor | otro.valor); }
    Numero operator^(const Numero& otro) const { return Numero(valor ^ otro.valor); }
    Numero operator<<(int n) const { return Numero(valor << n); }
    Numero operator>>(int n) const { return Numero(valor >> n); }

    // =============================
    // 4. ASIGNACIÓN (COMBINADOS)
    // =============================

    Numero& operator=(const Numero& otro) {
        valor = otro.valor;
        return *this;
    }

    Numero& operator+=(const Numero& otro) { valor += otro.valor; return *this; }
    Numero& operator-=(const Numero& otro) { valor -= otro.valor; return *this; }
    Numero& operator*=(const Numero& otro) { valor *= otro.valor; return *this; }
    Numero& operator/=(const Numero& otro) { valor /= otro.valor; return *this; }
    Numero& operator%=(const Numero& otro) { valor %= otro.valor; return *this; }
    Numero& operator&=(const Numero& otro) { valor &= otro.valor; return *this; }
    Numero& operator|=(const Numero& otro) { valor |= otro.valor; return *this; }
    Numero& operator^=(const Numero& otro) { valor ^= otro.valor; return *this; }
    Numero& operator<<=(int n) { valor <<= n; return *this; }
    Numero& operator>>=(int n) { valor >>= n; return *this; }

    // =============================
    // 5. ACCESO Y MEMORIA
    // =============================

    int operator[](int index) const {
        // Ejemplo: devuelve valor * index
        return valor * index;
    }

    int operator()(int x, int y) const {
        // Ejemplo: uso como "función"
        return valor + x + y;
    }

    Numero* operator->() {
        return this;
    }

    int operator*() const {
        // como si desreferenciara
        return valor;
    }

    Numero& operator&() {
        // retorna referencia (ejemplo conceptual)
        return *this;
    }

    // =============================
    // 6. ENTRADA/SALIDA (globales)
    // =============================

    friend ostream& operator<<(ostream& os, const Numero& n) {
        os << n.valor;
        return os;
    }

    friend istream& operator>>(istream& is, Numero& n) {
        is >> n.valor;
        return is;
    }

    // =============================
    // 7. GESTIÓN DE MEMORIA
    // =============================

    void* operator new(size_t size) {
        cout << "Usando new sobrecargado\n";
        return ::operator new(size);
    }

    void operator delete(void* ptr) {
        cout << "Usando delete sobrecargado\n";
        ::operator delete(ptr);
    }

    void* operator new[](size_t size) {
        cout << "Usando new[] sobrecargado\n";
        return ::operator new[](size);
    }

    void operator delete[](void* ptr) {
        cout << "Usando delete[] sobrecargado\n";
        ::operator delete[](ptr);
    }

    // =============================
    // 8. OTROS
    // =============================

    Numero operator,(const Numero& otro) const {
        // operador coma → devuelve el segundo
        return otro;
    }
};

int main() {
    Numero a(5), b(3);

    cout << "a+b = " << (a+b) << endl;        // aritmético
    cout << "a==b? " << (a==b) << endl;      // comparación
    cout << "a&b = " << (a&b) << endl;       // bitwise AND
    cout << "++a = " << ++a << endl;         // prefijo
    cout << "a++ = " << a++ << endl;         // sufijo
    cout << "a[2] = " << a[2] << endl;       // acceso con []
    cout << "a(1,2) = " << a(1,2) << endl;   // operador ()
    cout << "*a = " << *a << endl;           // desreferencia
    cout << "Operador coma: " << (a, b) << endl;

    Numero* p = new Numero(10); // usa new sobrecargado
    delete p;                   // usa delete sobrecargado
}
