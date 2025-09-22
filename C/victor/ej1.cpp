#include <iostream>
using namespace std;
class A
{
    int data;

public:
    A() { cout << "Default Constructor" << endl; };
    A(int d)
    {
        data = d;
        cout << "Constructor:" << d << endl;
    };
    ~A() { cout << "Destructor:" << data << endl; };
};
void f(A a) { cout << "f" << endl; }
int main()
{
    A a1(1), a2(2);
    f(a1);
    f(a2);
    // A *a = new A[5];
    A a[5];
    return 0;
}