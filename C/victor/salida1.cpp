#include <iostream>
using namespace std;

//declaracion de una clase
class A {
    //atributos por defecto privado
    int data;
    //los metodos se les pone public
public:
    //constructor sin parametros , ademas tiene una asignacion por defecto
    A():data(0) { cout << "Default Constructor" << endl; }
    //constructor q acepta parametros
    A(int d) { data = d; cout << "Constructor:" << d << endl; }
    //destructor , metodo que se llama cuando se elimina un objeto de la
    // memoria , tiene uno por defecto
    ~A() { cout << "Destructor:" << data << endl; }
    //asi se definen metodos privado aqui no tiene sentido es solo para ejemplo
private:
    int getData(){return data;}
};

void f(A a) { cout << "f" << endl; }

int main() {
    //al crearse dos variables del tipo de la clase se llama dos veces al constructor
    A a1(1), a2(2);
    //aqui como se pasa por valor se crea una copia , lo que
    //hace que se llame al constructor de
    //copia por defecto del compilador y luego al salir se llama
    f(a1); //al destructor
    f(a2);
    A a[5];
    return 0;
}
