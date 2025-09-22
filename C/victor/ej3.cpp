#include <iostream>
#include <stdlib.h>
using namespace std;
class myclass
{
public:
    int *p;
    myclass(int i);
    ~myclass() { delete p; }
    friend int getval(myclass a);
};
myclass::myclass(int i)
{
    p = new int;
    if (!p)
    {
        cout << "Error de asignacion\n";
        exit(1);
    }
    *p = i;
}
int getval(myclass a)
{
    return *a.p; // obtencion del valor
}
int main()
{
    myclass c1(1), c2(2);
    cout << getval(c1) << " " << getval(c2) << endl;
    cout << getval(c1) << " " << getval(c2);
    return 0;
}