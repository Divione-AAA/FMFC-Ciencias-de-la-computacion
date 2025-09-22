#include <iostream>
#include <string>
using namespace std;

class Factura {
private:
    string numeroDePieza;
    string descripcion;
    int cantidad;
    int precioPorArticulo;

public:
    // Constructor
    Factura(const string& num, const string& desc, int cant, int precio)
        : numeroDePieza(num), descripcion(desc) {
        cantidad = (cant > 0) ? cant : 0;
        precioPorArticulo = (precio > 0) ? precio : 0;
    }

    // Setters
    void setNumeroDePieza(const string& num) { numeroDePieza = num; }
    void setDescripcion(const string& desc) { descripcion = desc; }
    void setCantidad(int cant) { cantidad = (cant > 0) ? cant : 0; }
    void setPrecioPorArticulo(int precio) { precioPorArticulo = (precio > 0) ? precio : 0; }

    // Getters
    string getNumeroDePieza() const { return numeroDePieza; }
    string getDescripcion() const { return descripcion; }
    int getCantidad() const { return cantidad; }
    int getPrecioPorArticulo() const { return precioPorArticulo; }

    // Obtener monto de factura
    int obtenerMontoFactura() const {
        return cantidad * precioPorArticulo;
    }
};

// Programa de prueba
int main() {
    Factura f("A123", "Tornillo 10mm", 10, 2);
    cout << "Factura: " << f.getNumeroDePieza() << " - " << f.getDescripcion() << '\n';
    cout << "Cantidad: " << f.getCantidad() << ", Precio unidad: " << f.getPrecioPorArticulo() << '\n';
    cout << "Monto total: " << f.obtenerMontoFactura() << '\n';

    // probar valores negativos
    Factura f2("B456", "Tuerca", -5, -10);
    cout << "\nFactura 2 monto (con valores invalidos) = " << f2.obtenerMontoFactura() << '\n'; // 0
    return 0;
}
