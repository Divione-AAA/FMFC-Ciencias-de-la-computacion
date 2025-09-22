#include <iostream>
#include <map>
#include <string>
#include <cctype>
#include <locale>

using namespace std;

int main() {
    // Configurar locale para manejar correctamente la Ñ
    // locale::global(locale("es_ES.UTF-8"));

    string texto;
    cout << "Ingrese el texto: ";
    getline(cin, texto);

    // Mapa para frecuencias
    map<char, int> frecuencia;

    for (char c : texto) {
        // Pasar a mayúscula
        char upper = toupper(c, locale());

        // Si es A-Z o Ñ
        if (upper >= 'A' && upper <= 'Z') {
            frecuencia[upper]++;
        }
    }

    // Mostrar resultados
    cout << "\nFrecuencia de caracteres:\n";
    for (char c = 'A'; c <= 'Z'; c++) {
        if (frecuencia[c] > 0)
            cout << c << ": " << frecuencia[c] << "\n";
    }


    return 0;
}
