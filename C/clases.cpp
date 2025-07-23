#include<bits/stdc++.h>

using namespace std;

class Perro:string{
public:
    Perro(string _nombre, int _edad) {
        this->nombre = _nombre;
        this->edad = _edad;
    }

    void ladrar() {
       cout<<"juau"<<endl;
    }

    int getEdad() {
        return edad;
    }

    bool operator<(const Perro &other) {
        return edad < other.edad;
    }
    ~Perro(){}

private:
    string nombre;
    int edad;
};

int32_t main() {
    Perro Laika("Laika",20);
    Laika.ladrar();
    Perro Juan("Juan", 5);
    Juan.ladrar();
return 0;}
