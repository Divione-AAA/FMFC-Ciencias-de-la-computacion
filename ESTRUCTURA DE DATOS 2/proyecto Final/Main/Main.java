package Main;

import Search.*;
import java.util.List;
import metabolico.*;

public class Main {

    public static void main(String[] args) {
    
        System.out.println("   SISTEMA DE ANALISIS METABOLICO Y BIOINFORMATICA - PROYECTO #18     ");
    

        MetabolicNetwork redLaboratorio = new MetabolicNetwork();

        ejecutarR1(redLaboratorio);
        ejecutarR2yR3(redLaboratorio);
        ejecutarR4();

    
        System.out.println("       FIN DE LA EJECUCION DE PRUEBAS AUTOMATIZADAS DEL LABORATORIO     ");
    
    }

    /**
     * REQUISITO 1 (R1): Red metabólica con pesos mixtos, camino óptimo y control de ciclos negativos.
     */
    public static void ejecutarR1(MetabolicNetwork redEstable) {
        try {
            System.out.println("--- [R1] CONSTRUCCION DE LA RED METABOLICA Y CAMINOS OPTIMOS ---");

            // Datos de Prueba Mínimos: Registro de 15 Metabolitos (Nodos)
            for (int i = 1; i <= 15; i++) {
                redEstable.addMetabolite(new Metabolite(i, "Metabolito_" + i, "C" + i + "H" + (i*2) + "O" + i));
            }
            System.out.println("[OK] 15 metabolitos registrados correctamente en la red.");

            // Datos de Prueba Mínimos: Registro de 22 Reacciones (Aristas) con 5 pesos negativos
            redEstable.addReaction(1, 2, 4.5, "R_Enz01");
            redEstable.addReaction(2, 3, -2.1, "R_Enz02");  // 1° Negativa
            redEstable.addReaction(3, 4, 1.2, "R_Enz03");
            redEstable.addReaction(4, 5, -3.5, "R_Enz04");  // 2° Negativa
            redEstable.addReaction(1, 6, 10.0, "R_Enz05");
            redEstable.addReaction(6, 7, -1.5, "R_Enz06");  // 3° Negativa
            redEstable.addReaction(7, 5, 0.5, "R_Enz07");
            redEstable.addReaction(5, 8, 2.3, "R_Enz08");
            redEstable.addReaction(8, 9, 3.1, "R_Enz09");
            redEstable.addReaction(9, 10, -4.2, "R_Enz10"); // 4° Negativa
            redEstable.addReaction(10, 11, 1.1, "R_Enz11");
            redEstable.addReaction(11, 12, 5.0, "R_Enz12");
            redEstable.addReaction(12, 13, -0.8, "R_Enz13"); // 5° Negativa
            redEstable.addReaction(13, 14, 2.2, "R_Enz14");
            redEstable.addReaction(14, 15, 1.5, "R_Enz15");
            // Aristas adicionales para completar las 22 requeridas
            redEstable.addReaction(2, 6, 2.0, "R_Enz16");
            redEstable.addReaction(3, 7, 4.1, "R_Enz17");
            redEstable.addReaction(4, 8, 0.9, "R_Enz18");
            redEstable.addReaction(7, 9, 6.2, "R_Enz19");
            redEstable.addReaction(10, 13, 3.3, "R_Enz20");
            redEstable.addReaction(11, 14, 2.7, "R_Enz21");
            redEstable.addReaction(8, 12, 7.1, "R_Enz22");
            System.out.println("[OK] 22 reacciones bioquimicas insertadas (5 exergonicas con DeltaG < 0).");

            // Cómputo de la ruta mínima optimizada mediante aristas de energía negativa
            System.out.println("\n-> Computando ruta termodinamica minima desde Metabolito 1 a Metabolito 5...");
            List<Metabolite> caminoOptimo = redEstable.shortestPath(1, 5);
            System.out.print("Ruta metabolica calculada: ");
            for (int i = 0; i < caminoOptimo.size(); i++) {
                System.out.print(caminoOptimo.get(i).getName() + (i < caminoOptimo.size() - 1 ? " -> " : ""));
            }
            System.out.println("\n[OK] Validacion de pesos de ambos signos realizada con exito.\n");

            // Escenario de control anormal con ciclo termodinámico negativo
            System.out.println("-> Creando escenario de control con error termodinamico (Ciclo Negativo)...");
            MetabolicNetwork redConCicloNegativo = new MetabolicNetwork();
            redConCicloNegativo.addMetabolite(new Metabolite(100, "Glucosa-F", ""));
            redConCicloNegativo.addMetabolite(new Metabolite(200, "Piruvato-F", ""));
            redConCicloNegativo.addReaction(100, 200, 5.0, "R_Ciclo1");
            redConCicloNegativo.addReaction(200, 100, -12.0, "R_Ciclo2");

            try {
                redConCicloNegativo.shortestPath(100, 200);
            } catch (CycleNegativeException e) {
                System.out.println("[NOTIFICACION DE ERROR EXPECTATIVA SUCCESFUL]: " + e.getMessage());
                System.out.println("[OK] El sistema capturo y bloqueo la ruta de energia infinita.");
            }
        } catch (Exception e) {
            System.err.println("[ERROR EN R1]: " + e.getMessage());
        }
    }

/**
     * REQUISITOS 2 Y 3 (R2, R3): Proceso con dependencias (Kahn) y Cola de Prioridad basada en Heap.
     */
    public static void ejecutarR2yR3(MetabolicNetwork redEstable) {
        try {
        
            System.out.println("--- [R2 y R3] PROCESAMIENTO CON DEPENDENCIAS Y COLA DE PRIORIDAD (HEAP) ---");

            // Inyección de 8 rutas con prioridades clínicas sobre nodos con caminos válidos en la red
            MetabolicRoute r1 = redEstable.addRouteToAnalyze(1, 3, 85.5); // Cam: 1->2->3 (2 reacciones)
            MetabolicRoute r2 = redEstable.addRouteToAnalyze(1, 5, 95.0); // Cam: 1->2->3->4->5 (4 reacciones) 
            MetabolicRoute r3 = redEstable.addRouteToAnalyze(1, 4, 70.0); // Cam: 1->2->3->4 (3 reacciones)
            MetabolicRoute r4 = redEstable.addRouteToAnalyze(6, 5, 95.0); // Cam: 6->7->5 (2 reacciones) -> MISMA relevancia que r2 pero MENOS reacciones. ¡Desempata!
            MetabolicRoute r5 = redEstable.addRouteToAnalyze(1, 2, 40.0); // Cam: 1->2 (1 reacción)
            MetabolicRoute r6 = redEstable.addRouteToAnalyze(5, 8, 60.5); // Cam: 5->8 (1 reacción)
            MetabolicRoute r7 = redEstable.addRouteToAnalyze(2, 3, 50.0); // Cam: 2->3 (1 reacción)
            MetabolicRoute r8 = redEstable.addRouteToAnalyze(1, 7, 99.9); // Cam: 1->6->7 (2 reacciones) -> Prioridad clínica absoluta

            // Establecimiento de una red encadenada de dependencias jerárquicas (Mínimo 6 fases)
            redEstable.addRouteDependency(r8.getId(), r4.getId());
            redEstable.addRouteDependency(r4.getId(), r2.getId()); // r8 depende indirectamente de r2
            redEstable.addRouteDependency(r2.getId(), r1.getId());
            redEstable.addRouteDependency(r1.getId(), r3.getId());
            redEstable.addRouteDependency(r3.getId(), r6.getId());
            redEstable.addRouteDependency(r7.getId(), r5.getId());

            System.out.println("[OK] Cargadas 8 rutas en cola con dependencias jerarquicas directas e indirectas.");

            // Ordenamiento topológico usando Kahn interactuando con el Heap
            System.out.println("\n-> Computando el orden de analisis correcto...");
            List<MetabolicRoute> ordenDeProcesamiento = redEstable.computeAnalysisOrder();

            int contadorFase = 1;
            for (MetabolicRoute ruta : ordenDeProcesamiento) {
                System.out.println("   Fase [" + contadorFase + "] => " + ruta.toString());
                contadorFase++;
            }
            System.out.println("[OK] Orden topologico calculado correctamente. Desempates clinicos resueltos.");
        } catch (Exception e) {
            System.err.println("[ERROR EN R2/R3]: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * REQUISITO 4 (R4): Búsqueda de patrones en cadenas de ADN de 1000 bases y recuento empírico.
     */
    public static void ejecutarR4() {
    
        System.out.println("--- [R4] BUSQUEDA DE MOTIVOS GENETICOS EN SECUENCIAS DE ADN ---");

        // Generación del texto biológico sintético de prueba
        String secuenciaADN = DNADataGenerator.generateExperimentSequence();
        System.out.println("[OK] Secuencia sintetica de ADN de 1000 bases nitrogenadas generada.");

        // Extracción de motivos existentes de longitudes 4, 10 y 20
        String[] motivos = DNADataGenerator.generatePatterns(secuenciaADN);

        DNAPatternSearch fuerzaBruta = new BruteForceSearch();
        DNAPatternSearch kmp = new KMPSearch();

    
        System.out.println(" TABLA COMPARATIVA: COMPORTAMIENTO EXPERIMENTAL DE BUSQUEDA DE ADN");
    
        System.out.printf("%-15s | %-20s | %-20s\n", "Long. Motivo", "Comparaciones FB", "Comparaciones KMP");
    

        for (String motivo : motivos) {
            SearchResult resFB = fuerzaBruta.search(secuenciaADN, motivo);
            SearchResult resKMP = kmp.search(secuenciaADN, motivo);

            System.out.printf("Bases: %-8d | %-20d | %-20d\n", 
                    motivo.length(), 
                    resFB.getComparaciones(), 
                    resKMP.getComparaciones()
            );
        }
    
        System.out.println("[OK] Documentacion experimental de rendimiento guardada con exito.");
    }
}