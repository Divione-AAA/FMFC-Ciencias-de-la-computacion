#include<bits/stdc++.h>
using namespace std;

class Factura{
    private:
        string pieza,descripcion;
        int cantidad,precio;
    public:
        Factura(string pieza,string descripcion,int cantidad,int precio){
            this->pieza=pieza;
            this->descripcion=descripcion;
            this->cantidad=cantidad > 0 ? cantidad : 0;
            this->precio=precio > 0 ? precio : 0;
        }

        int obtenerMontoFactura(){
            return cantidad*precio > 0 ? cantidad*precio : 0;
        }
};

class Empleado{
    private:
        string nombre,apellido;
        int salario;
    public:
        Empleado(string nombre,string apellido,int salario){
            this->nombre=nombre;
            this->apellido=apellido;
            this->salario=salario>0 ? salario : 0;
        }
        int obtenerSalario(){
            return salario;
        }
        void setSalario(int salario){
            this->salario=salario;
        }
        string obtenerNombre(){
            return nombre;
        }
        void setNombre(string nombre){
            this->nombre=nombre;
        }
        string obtenerApellido(){
            return apellido;
        }
        void setApellido(string apellido){
            this->apellido=apellido;
        }
};

class Time{
    private:
        int hora,minuto,segundos;
    public:
        Time(){
            this->hora=0;
            this->minuto=0;
            this->segundos=0;
        }
        Time(int hora,int minuto,int segundos){
            this->hora=hora;
            this->minuto=minuto;
            this->segundos=segundos;
        }
        void mostrarHora(){
            cout<<hora<<":"<<minuto<<":"<<segundos<<endl;
        }
        friend void mostrarHoras(Time t);
};

void mostrarHoras(Time t){
    cout<<t.hora<<":"<<t.minuto<<":"<<t.segundos<<endl;
}

int main(){

    Factura f1("pieza","descripcion",7,10);
    Factura f2("pieza","descripcion",-4,20);

    cout<<f1.obtenerMontoFactura()<<endl;
    cout<<f2.obtenerMontoFactura()<<endl;

    Empleado e1("Hector","Aguero",300),e2("Victor","Martinez",200);
    
    e1.setSalario(e1.obtenerSalario()*1.10);
    e2.setSalario(e2.obtenerSalario()*1.10);

    //cout<<e1.obtenerSalario()<<endl;
    cout<<e2.obtenerSalario()<<endl;

    mostrarHoras(Time(1,2,3));

return 0;}