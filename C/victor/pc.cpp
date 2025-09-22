#include <iostream>
#include <cstring> // para strcpy
using namespace std;

class Computadora
{
private:
    char *procesador; // arreglo dinámico
    int precio;
    int disco_duro;
    int ram;

public:
    // Constructor por defecto
    Computadora()
    {
        procesador = new char[20];
        strcpy(procesador, ""); // vacío
        precio = 0;
        disco_duro = 0;
        ram = 0;
    }

    // Constructor parametrizado
    Computadora(const char *proc, int p, int d, int r)
    {
        procesador = new char[20];
        strncpy(procesador, proc, 19);
        procesador[19] = '\0'; // asegurar terminación
        precio = p;
        disco_duro = d;
        ram = r;
    }

    // Método para leer datos
    void leerDatos()
    {
        char temp[20];
        cout << "Procesador: ";
        cin >> temp;
        strncpy(procesador, temp, 19);
        procesador[19] = '\0';

        cout << "Precio: ";
        cin >> precio;
        cout << "Disco duro (GB): ";
        cin >> disco_duro;
        cout << "RAM (GB): ";
        cin >> ram;
    }

    // Método para mostrar datos
    void mostrarDatos() const
    {
        cout << "Procesador: " << procesador << endl;
        cout << "Precio: $" << precio << endl;
        cout << "Disco Duro: " << disco_duro << " GB" << endl;
        cout << "RAM: " << ram << " GB" << endl;
    }

    // Destructor
    ~Computadora()
    {
        delete[] procesador;
        cout << "Destructor llamado, memoria liberada." << endl;
    }
};

int main()
{
    // Objeto estático con constructor por defecto
    Computadora c1;
    c1.leerDatos();
    c1.mostrarDatos();

    cout << endl;

    // Objeto estático con constructor parametrizado
    Computadora c2("Intel-i7", 1200, 512, 16);
    c2.mostrarDatos();

    cout << endl;

    // Objeto dinámico
    Computadora *c3 = new Computadora("Ryzen-5", 900, 1024, 32);
    c3->mostrarDatos();

    // Liberar memoria del objeto dinámico
    delete c3;

    return 0;
}
