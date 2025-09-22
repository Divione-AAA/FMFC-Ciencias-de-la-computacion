#include <iostream>
#include <stdlib.h>
using namespace std;
// Este programa tiene un error.
class dyna
{
    int *p;

public:
    dyna(int i);
    ~dyna()
    {
        delete p;
        cout << "Destructor liberando dir->" << p << endl;
    }
    dyna(const dyna &ob){
        p = new int;
        if (!p)
        {
            cout << "Error de asignacion\n";
            exit(1);
        }
        *p = *ob.p; // Copia el valor apuntado por ob.p
        cout << "Constructor copia con p apuntando a-> " << p << endl;
    }
    int get() { return *p; }
};
dyna::dyna(int i)
{
    p = new int;
    if (!p)
    {
        cout << "Error de asignacion\n";
        exit(1);
    }
    *p = i;
    cout << "Constructor con p apuntando a-> " << p << endl;
}
// Devuelve el valor negativo de *ob.p
int neg(dyna ob) { return -ob.get(); }
int main()
{
    dyna a(-10);
    cout << a.get() << "\n";
    cout << neg(a) << "\n";
    
    dyna a2(20);
    cout << a2.get() << "\n";
    cout << neg(a2) << "\n";
    return 0;
}
// a.Diga cuando se llama al constructor y destructor de la clase ? b.Existe algún error en el programa ?