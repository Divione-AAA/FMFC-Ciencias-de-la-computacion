#include<bits/stdc++.h>
using namespace std;

class Figura{
    protected:
        vector<double> lados;
    public:
        virtual double area()=0;
        virtual double perimetro()=0;
};

class Circulo : public Figura{
    public:
        Circulo(const vector<double>& l){
            this->lados = l;
        }
        double area(){
            return 3.14*pow(lados[0],2);
        }
        double perimetro(){
            return 2*lados[0]*lados[1];
        }
};

class Base{

    protected:
        int x;

    public:
        Base(int x){
            this->x=x;
            cout<<"Constructor de base"<<endl;
        }
};

class Heredera : public Base{
    public:
        Heredera(int x) : Base(x){
            cout<<"Constructor de heredera"<<endl;
        }
};

int main(){
    Heredera h(10);
    return 0;
}