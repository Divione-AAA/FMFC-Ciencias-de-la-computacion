#include <iostream>
#include <cstring>  // strncpy
#include <string>
#include <limits>
using namespace std;

class Computadora {
    //los atributos son privados por defecto
    // declaramos un puntero a char para un arreglo dinamico
    char* procesador;               // arreglo dinámico
    //resto de atributos
    static const int PROC_SIZE = 20; // tamaño solicitado
    int precio;
    int disco_duro;
    int ram;

public:
    // Constructor por defecto , simplemente damos valores por defecto e
    // inicializamos el array de caracteres
    Computadora() : precio(0), disco_duro(0), ram(0) {
        procesador = new char[PROC_SIZE + 1]; // +1 para el '\0'
        procesador[0] = '\0'; // cadena vacía
    }

    // Constructor parametrizado
    Computadora(const char* proc, int precio, int disco, int ram)
        : precio(precio), disco_duro(disco), ram(ram) {
        procesador = new char[PROC_SIZE + 1];
        // Copiamos max PROC_SIZE caracteres y garantizamos terminador
        strncpy(procesador, proc, PROC_SIZE);
        procesador[PROC_SIZE] = '\0';
    }

    // Constructor copia (importante para memoria dinámica)

    Computadora(const Computadora& other)
        : precio(other.precio), disco_duro(other.disco_duro), ram(other.ram) {
        procesador = new char[PROC_SIZE + 1];
        strncpy(procesador, other.procesador, PROC_SIZE);
        procesador[PROC_SIZE] = '\0';
    }

    // Operador = (asignación)
    //sobrecarga el operador de asignacion para que se haga una copia del objeto
    //esto hacer que al igualar dos objetos estos apunten a referencias diferentes
    Computadora& operator=(const Computadora& other) {
        //evitamos la duplicacion asignandose a si mismo
        if (this == &other) return *this; // protección self-assignment
        // liberar memoria actual - limpiamos el espacio que tiene procesador
        delete[] procesador;
        // copiar datos
        precio = other.precio;
        disco_duro = other.disco_duro;
        ram = other.ram;
        procesador = new char[PROC_SIZE + 1];
        strncpy(procesador, other.procesador, PROC_SIZE);
        procesador[PROC_SIZE] = '\0';
        return *this;
    }

    // Destructor
    ~Computadora() {
        delete[] procesador;
    }

    // Métodos para leer datos desde teclado
    void leer() {
        cout << "Procesador (una linea, hasta " << PROC_SIZE << " caracteres): ";
        string tmp;
        // consumimos posible '\n' previo y leemos línea
        std::getline(cin, tmp);
        if (tmp.empty()) std::getline(cin, tmp); // por si queda un salto
        std::strncpy(procesador, tmp.c_str(), PROC_SIZE);
        procesador[PROC_SIZE] = '\0';

        cout << "Precio: "; cin >> precio;
        cout << "Disco duro (GB): "; cin >> disco_duro;
        cout << "RAM (GB): "; cin >> ram;
        //esto lo que hace es limpiar el buffer para evitar los saltos de linea
        cin.ignore(numeric_limits<streamsize>::max(), '\n'); // limpiar buffer
    }

    // Mostrar datos
    void mostrar() const {
        cout << "Procesador: " << procesador << '\n';
        cout << "Precio: " << precio << '\n';
        cout << "Disco duro: " << disco_duro << " GB\n";
        cout << "RAM: " << ram << " GB\n";
    }
};

// Ejemplo de uso
int main() {
    // Objeto estático (stack)
    //al crear un objeto asi directo se crea de manera estatica
    Computadora pc1("Intel Core i5", 600, 512, 8);
    cout << "PC1 (estatica):\n";
    pc1.mostrar();
    cout << "----------------\n";

    // Objeto dinámico (heap)
    //se almacena su referencia al heap para acceder a los elementos y
    //mantener un uso de memoria dinamica
    Computadora* pc2 = new Computadora();
    cout << "Ingrese datos para PC2:\n";
    pc2->leer();
    cout << "PC2 (dinamica):\n";
    pc2->mostrar();

    // liberar memoria
    delete pc2;

    return 0;
}
