#include<bits/stdc++.h>
using namespace std;

class Persona{
    private:
        int edad;
        string nombre;
    public:
        Persona(int edad, string nombre){
            this->edad = edad;
            this->nombre = nombre;
        }
        void leer(){
            cout<<"estoy leyendo"<<endl;
        }
};

int main(){
    Persona p1(14,"Fulana");
    p1.leer();
return 0;}