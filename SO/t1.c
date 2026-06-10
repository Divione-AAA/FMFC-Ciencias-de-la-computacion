float min_general = 100.0;
float max_general = -100.0;
float pronostico1 = 0;
float pronostico2 = 0;

#define HISTORICO_SIZE 1000  // Tamaño del histórico local

float lectura_o2(){
    return -50.0 + (rand() % 101);
}

void* sensor_concentracion(void* arg) {
    int id = *(int*)arg;
    // Almacenamiento local
    float cache_local[HISTORICO_SIZE] = {0};
    int indice = 0;  // Índice para el buffer circular
    // El ciclo while de la derecha
    //sigue a continuación de esto    
    while(1) {        
        float lectura = lectura_o2();        
        //Actualizar cache local
        cache_local[indice] = lectura;
        indice = (indice + 1) % HISTORICO_SIZE;        
        if (lectura < min_general) {
            printf("%d: Nuevo MÍNIMO %.1f°C\n", id, lectura);
           min_general = lectura;
           pronostico1 = min_general + fun1();
        }        
        if (lectura > max_general) {
            printf("%d: Nuevo MÁXIMO %.1f°C\n", id, lectura);
            max_general = lectura;
           pronostico2 = max_general + fun2();
        }
        usleep(100000 + rand() % 50000);  // Espera variable
    }
    //return NULL;
}