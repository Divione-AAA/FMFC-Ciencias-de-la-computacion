import random
import tkinter as tk
from tkinter import messagebox, ttk


# =====================================================================
# CLASE: Autómata Finito Determinista (AFD)
# =====================================================================
class AFD:

    def __init__(self, estados, alfabeto, transiciones, estado_inicial, estados_aceptacion):
        """
        Define formalmente la 5-tupla del AFD.
        - estados: set de strings (Q)
        - alfabeto: set de strings (Sigma)
        - transiciones: dict anidado {estado_actual: {simbolo: estado_siguiente}} (delta)
        - estado_inicial: string (q0)
        - estados_aceptacion: set de strings (F)
        """
        self.estados = set(estados)
        self.alfabeto = set(alfabeto)
        self.transiciones = transiciones
        self.estado_inicial = estado_inicial
        self.estados_aceptacion = set(estados_aceptacion)

    def procesar_cadena(self, cadena):
        """
        Realiza la computación de una cadena tipo string sobre el autómata.
        Retorna una tupla (booleano, lista_de_historial_de_estados).
        """
        estado_actual = self.estado_inicial
        historial = [estado_actual]

        for simbolo in cadena:
            # Validación de alfabeto
            if simbolo not in self.alfabeto:
                return False, historial  # Símbolo inválido, rechazo inmediato

            # Intenta transicionar de acuerdo a la función delta
            if (
                estado_actual in self.transiciones
                and simbolo in self.transiciones[estado_actual]
            ):
                estado_actual = self.transiciones[estado_actual][simbolo]
                historial.append(estado_actual)
            else:
                return False, historial  # No existe transición, el autómata se bloquea

        # Al terminar la cadena, verifica si el estado final pertenece a F
        es_aceptada = estado_actual in self.estados_aceptacion
        return es_aceptada, historial


# =====================================================================
# CLASE: Gramática Libre de Contexto (GLC)
# =====================================================================
class GLC:

    def __init__(self, no_terminales, terminales, producciones, simbolo_inicial):
        """
        Define formalmente la 4-tupla de la GLC.
        - no_terminales: set de strings (N)
        - terminales: set de strings (Sigma)
        - producciones: dict {NoTerminal: [[simbolo1, simbolo2], [opcion2]]} (P)
        - simbolo_inicial: string (S)
        """
        self.no_terminales = set(no_terminales)
        self.terminales = set(terminales)
        self.producciones = producciones
        self.simbolo_inicial = simbolo_inicial

    def generar_cadena_aleatoria(self, max_intentos=50):
        """
        Lleva la gramática a la acción: genera una cadena válida derivando
        recursivamente desde el símbolo inicial mediante un enfoque Top-Down aleatorio.
        """
        forma_sentencial = [self.simbolo_inicial]
        derivaciones = [" ".join(forma_sentencial)]
        intento = 0

        # El ciclo continúa mientras existan elementos No Terminales en nuestra forma sentencial
        while (
            any(s in self.no_terminales for s in forma_sentencial)
            and intento < max_intentos
        ):
            nueva_forma = []
            for simbolo in forma_sentencial:
                if simbolo in self.no_terminales:
                    # Elige una regla de producción al azar para este No Terminal
                    opciones = self.producciones[simbolo]
                    eleccion = random.choice(opciones)
                    # Si no es épsilon, añade los símbolos derivados
                    if eleccion != ["e"] and eleccion != ["ε"]:
                        nueva_forma.extend(eleccion)
                else:
                    nueva_forma.append(simbolo)
            forma_sentencial = nueva_forma
            derivaciones.append(
                " ".join(forma_sentencial) if forma_sentencial else "ε"
            )
            intento += 1

        cadena_final = "".join(
            [s for s in forma_sentencial if s in self.terminales]
        )
        return cadena_final, derivaciones


# =====================================================================
# COMPONENTE VISUAL: Interfaz Gráfica de Usuario (GUI) con Tkinter
# =====================================================================
class AplicacionVisual:

    def __init__(self, root):
        self.root = root
        self.root.title("Simulador de Modelos Formales: AFD & GLC")
        self.root.geometry("750x600")

        # --- INSTANCIACIÓN DE DATOS DE EJEMPLO DE LA ASIGNATURA ---
        # AFD Ejemplo: Reconoce cadenas sobre {0,1} que terminan en número impar (terminan en 1)
        self.afd_ejemplo = AFD(
            estados={"q0", "q1"},
            alfabeto={"0", "1"},
            transiciones={"q0": {"0": "q0", "1": "q1"}, "q1": {"0": "q0", "1": "q1"}},
            estado_inicial="q0",
            estados_aceptacion={"q1"},
        )

        # GLC Ejemplo: L = {a^n b^n | n >= 0}. Estructurada en bloques unitarios de string
        self.glc_ejemplo = GLC(
            no_terminales={"S"},
            terminales={"a", "b"},
            producciones={
                "S": [["a", "S", "b"], ["ε"]]  # Reglas: S -> aSb | ε
            },
            simbolo_inicial="S",
        )

        self.crear_componentes()

    def crear_componentes(self):
        # Panel de Pestañas
        tab_control = ttk.Notebook(self.root)

        # PESTAÑA 1: Autómata Finito Determinista
        self.tab_afd = ttk.Frame(tab_control)
        tab_control.add(self.tab_afd, text="Módulo AFD")

        # PESTAÑA 2: Gramática Libre de Contexto
        self.tab_glc = ttk.Frame(tab_control)
        tab_control.add(self.tab_glc, text="Módulo Gramáticas GLC")

        tab_control.pack(expand=1, fill="both")

        self.construir_interfaz_afd()
        self.construir_interfaz_glc()

    def construir_interfaz_afd(self):
        # Información del Autómata cargado
        lbl_info = tk.Label(
            self.tab_afd,
            text="AFD Cargado: Números binarios que terminan en IMPAR (Terminan en 1)\nAlphabet: {0, 1} | Estados: {q0, q1} | Inicial: q0 | Aceptación: {q1}",
            fg="blue",
            justify="left",
        )
        lbl_info.pack(pady=15)

        # Campo de Entrada de Cadena
        lbl_entrada = tk.Label(
            self.tab_afd, text="Ingrese una cadena binaria para evaluar (ej. 10101):"
        )
        lbl_entrada.pack(pady=5)

        self.txt_cadena_afd = tk.Entry(self.tab_afd, font=("Consolas", 14), width=30)
        self.txt_cadena_afd.pack(pady=5)
        self.txt_cadena_afd.insert(0, "10101")

        # Botón de Procesamiento
        btn_evaluar = tk.Button(
            self.tab_afd,
            text="Evaluar Cadena en el AFD",
            bg="#4CAF50",
            fg="white",
            command=self.ejecutar_afd,
        )
        btn_evaluar.pack(pady=10)

        # Caja de Resultados Visuales
        self.txt_consola_afd = tk.Text(
            self.tab_afd, height=15, width=80, font=("Consolas", 11), bg="#f0f0f0"
        )
        self.txt_consola_afd.pack(pady=15)

    def construir_interfaz_glc(self):
        # Información de la Gramática cargada
        lbl_info = tk.Label(
            self.tab_glc,
            text="GLC Cargada: Lenguaje de bloques equipotentes L = {a^n b^n | n >= 0}\nReglas de Producción:  S -> aSb  |  S -> ε",
            fg="purple",
            justify="left",
        )
        lbl_info.pack(pady=15)

        # Botón para activar el derivador
        btn_generar = tk.Button(
            self.tab_glc,
            text="Generar Cadena Aleatoria por Derivación",
            bg="#008CBA",
            fg="white",
            command=self.ejecutar_glc,
        )
        btn_generar.pack(pady=10)

        # Caja de Salida para el Árbol/Secuencia de Derivación
        self.txt_consola_glc = tk.Text(
            self.tab_glc, height=18, width=80, font=("Consolas", 11), bg="#f5f5f5"
        )
        self.txt_consola_glc.pack(pady=15)

    def ejecutar_afd(self):
        self.txt_consola_afd.delete("1.0", tk.END)
        cadena = self.txt_cadena_afd.get().strip()

        # Invoca el método matemático lógico implementado
        aceptada, ruta = self.afd_ejemplo.procesar_cadena(cadena)

        # Imprime la traza de ejecución visual en la consola integrada
        self.txt_consola_afd.insert(
            tk.END, f"--- EVALUANDO CADENA: '{cadena}' ---\n\n"
        )
        self.txt_consola_afd.insert(tk.END, "Camino de Transición:\n")

        estado_ant = ruta[0]
        self.txt_consola_afd.insert(tk.END, f"  Estado Inicial: {estado_ant}\n")

        for i, simbolo in enumerate(cadena):
            if i + 1 < len(ruta):
                estado_sig = ruta[i + 1]
                self.txt_consola_afd.insert(
                    tk.END,
                    f"  Leer '{simbolo}': ({estado_ant}) ---{simbolo}---> ({estado_sig})\n",
                )
                estado_ant = estado_sig
            else:
                self.txt_consola_afd.insert(
                    tk.END,
                    f"  ERROR: Símbolo '{simbolo}' rompe el determinismo. No hay transición.\n",
                )
                break

        self.txt_consola_afd.insert(tk.END, "\n-------------------------------------\n")
        if aceptada:
            self.txt_consola_afd.insert(
                tk.END,
                f"RESULTADO: ¡CADENA ACEPTADA!\nEl estado final ({ruta[-1]}) es de Aceptación.",
            )
            messagebox.showinfo("Simulación AFD", "¡Cadena ACEPTADA con éxito!")
        else:
            self.txt_consola_afd.insert(
                tk.END,
                f"RESULTADO: CADENA RECHAZADA.\nEl estado final no pertenece a F o la cadena es inválida.",
            )
            messagebox.showerror("Simulación AFD", "Cadena RECHAZADA.")

    def ejecutar_glc(self):
        self.txt_consola_glc.delete("1.0", tk.END)

        # Invoca el método generativo de la gramática libre de contexto
        cadena_obtenida, pasos = self.glc_ejemplo.generar_cadena_aleatoria()

        self.txt_consola_glc.insert(tk.END, "--- PROCESO DE DERIVACIÓN (S) ---\n\n")
        for i, paso in enumerate(pasos):
            if i == 0:
                self.txt_consola_glc.insert(tk.END, f"   {paso}  (Símbolo Inicial)\n")
            else:
                self.txt_consola_glc.insert(
                    tk.END, f"⇒  {paso}\n"
                )  # Símbolo de implicación lógico de derivación

        self.txt_consola_glc.insert(tk.END, "\n-------------------------------------\n")
        self.txt_consola_glc.insert(
            tk.END,
            f"Cadena Final Terminal Alcanzada: '{cadena_obtenida}'\nLongitud de la cadena: {len(cadena_obtenida)}",
        )


# Lanzamiento de la Aplicación
if __name__ == "__main__":
    root = tk.Tk()
    app = AplicacionVisual(root)
    root.mainloop()