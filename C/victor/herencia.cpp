#include <iostream>
#include <vector>
using namespace std;

// Clase abstracta
class Poligono {
public:
    // Método virtual puro → hace que la clase sea abstracta
    virtual double Area() const = 0;  
    virtual void leerDatos() = 0;      // Método abstracto para leer datos
    virtual ~Poligono() {}             // Destructor virtual
};

// ------------------ RECTÁNGULO ------------------
class Rectangulo : public Poligono {
protected:
    double base, altura;
public:
    Rectangulo() : base(0), altura(0) {}

    void leerDatos() override {
        cout << "Ingrese la base del rectangulo: ";
        cin >> base;
        cout << "Ingrese la altura del rectangulo: ";
        cin >> altura;
    }

    double Area() const override {
        return base * altura;
    }
};

// ------------------ CUADRADO ------------------
class Cuadrado : public Rectangulo {
public:
    Cuadrado() : Rectangulo() {}

    void leerDatos() override {
        cout << "Ingrese el lado del cuadrado: ";
        cin >> base;
        altura = base; // el cuadrado tiene lados iguales
    }

    double Area() const override {
        return base * base;
    }
};

// ------------------ TRIÁNGULO ------------------
class Triangulo : public Poligono {
private:
    double base, altura;
public:
    Triangulo() : base(0), altura(0) {}

    void leerDatos() override {
        cout << "Ingrese la base del triangulo: ";
        cin >> base;
        cout << "Ingrese la altura del triangulo: ";
        cin >> altura;
    }

    double Area() const override {
        return (base * altura) / 2.0;
    }
};

// ------------------ MAIN ------------------
int main() {
    vector<Poligono*> figuras;  // Polimorfismo con punteros a Poligono
    int opcion;

    do {
        cout << "\n--- MENU ---\n";
        cout << "1. Rectangulo\n";
        cout << "2. Cuadrado\n";
        cout << "3. Triangulo\n";
        cout << "4. Mostrar todas las areas\n";
        cout << "0. Salir\n";
        cout << "Seleccione una opcion: ";
        cin >> opcion;

        Poligono* p = nullptr;

        switch(opcion) {
            case 1:
                p = new Rectangulo();
                p->leerDatos();
                figuras.push_back(p);
                break;
            case 2:
                p = new Cuadrado();
                p->leerDatos();
                figuras.push_back(p);
                break;
            case 3:
                p = new Triangulo();
                p->leerDatos();
                figuras.push_back(p);
                break;
            case 4:
                cout << "\n--- Áreas calculadas ---\n";
                for(size_t i=0; i<figuras.size(); i++) {
                    cout << "Figura " << i+1 << ": " 
                         << figuras[i]->Area() << endl;
                }
                break;
            case 0:
                cout << "Saliendo...\n";
                break;
            default:
                cout << "Opcion invalida.\n";
        }
    } while(opcion != 0);

    // Liberar memoria
    for(Poligono* f : figuras) {
        delete f;
    }

    return 0;
}
