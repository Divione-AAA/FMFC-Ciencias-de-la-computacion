#include <iomanip>
#include <iostream>
using namespace std;
class Time {
     int h,m,s;

public:

    Time():h(0),m(0),s(0) {cout<<"Constructor por default"<<endl;}
    Time(int ho ,int  mi ,int se ):  h(0), m(0), s(0) {
        if (0 <= ho && ho <= 23 &&
        0 <= mi && mi <= 59 &&
        0 <= se && se <= 59) {
            h = ho;
            m = mi;
            s = se;
        } else {
            cout << "Valores fuera de rango, inicializando en 0" << endl;
        }
    }

    // Mostrar en formato hh:mm:ss con ceros a la izquierda
    void mostrar() const {
        cout << setw(2) << setfill('0') << h << ':'
             << setw(2) << setfill('0') << m << ':'
             << setw(2) << setfill('0') << s << '\n';
    }

    [[nodiscard]] int h1() const {
        return h;
    }

    [[nodiscard]] int m1() const {
        return m;
    }

    [[nodiscard]] int s1() const {
        return s;
    }

    //todos estos set deberian llevar validacion
    void set_h(const int ho) {
        h = ho;
    }

    void set_m(const int mi) {
        m = mi;
    }

    void set_s(const int se) {
        s = se;
    }
};

int main() {
    const Time t1(6,45,56);
    const Time *t2=new Time(-68,57,43);

    t1.mostrar();
    t2->mostrar();

    delete(t2);

    return 0;
}
