#include  <iostream>
#include  <string>
using namespace std;

class Empleado {
    string name,lastName;
    int sal;

public:
    Empleado(const string &name, const string &last_name, int sal)
        : name(name),
          lastName(last_name) {
        if (sal<0)sal=0;
        this->sal=sal;
    }

    Empleado():sal(0){};

    ~Empleado() = default;

    Empleado(const Empleado &other)
        : name(other.name),
          lastName(other.lastName),
          sal(other.sal) {
    }

    Empleado & operator=(const Empleado &other) {
        if (this == &other)
            return *this;
        name = other.name;
        lastName = other.lastName;
        sal = other.sal;
        return *this;
    }

    [[nodiscard]] string name1() const {
        return name;
    }

    void set_name(const string &name) {
        this->name = name;
    }

    [[nodiscard]] string last_name() const {
        return lastName;
    }

    void set_last_name(const string &last_name) {
        lastName = last_name;
    }

    [[nodiscard]] int sal1() const {
        return sal;
    }

    void set_sal(int sal) {
        this->sal = sal;
    }
};

int main() {
    Empleado *e1 = new Empleado("Vicotr","Martinez",300);
    Empleado *e2 = new Empleado("BUAJJA","Martinez",200);

    cout<<"Salario anual de e1-> "<<e1->sal1()*12<<endl;
    cout<<"Salario anual de e2-> "<<e2->sal1()*12<<endl;
    cout<<"Aumentando salario en un 10%"<<endl;
    e1->set_sal(e1->sal1()+e1->sal1()/10);
    e2->set_sal(e2->sal1()+e2->sal1()/10);

    cout<<"Salario anual de e1->"<<e1->sal1()*12<<endl;
    cout<<"Salario anual de e2->"<<e2->sal1()*12<<endl;


    return 0;
}

