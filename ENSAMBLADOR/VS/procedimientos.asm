.386                ; Usar instrucciones 80386 y superiores
.model flat, stdcall ; Modelo de memoria flat, convención de llamada stdcall

option casemap :none ; No distinguir mayúsculas/minúsculas en nombres

; Incluimos las bibliotecas necesarias para consola y definiciones generales de Windows.
; windows.inc es fundamental para NULL y otras definiciones.
include \masm32\include\windows.inc ; AÑADIDO: Necesario para NULL y otras definiciones básicas.
include \masm32\include\kernel32.inc

; Incluimos las librerías necesarias para consola
includelib \masm32\lib\kernel32.lib

.stack 1000h        ; Define un tamaño de pila de 4KB (ajustable según necesidad)

.data               ; Sección de datos inicializados
    msg db "Hola Mundo desde VS Code y MASM!",13,10,0 ; Mensaje con salto de línea (CRLF) y nulo

.data?              ; Sección de datos no inicializados
    hConsoleOutput DD ? ; Variable para guardar el handle de la consola de salida
    dwBytesWritten DD ? ; Variable para guardar el número de bytes escritos (corregido a dwBytesWritten para consistencia)

.code               ; Sección de código

; --- Definición del Procedimiento mostrarMensaje ---
; Corregido: Quitado el punto de '.mostrarMensaje'
mostrarMensaje PROC

    ; Corregido: GetStdHandle (G y S mayúsculas)
    invoke GetStdHandle, STD_OUTPUT_HANDLE
    mov hConsoleOutput, eax ; Guardamos el handle devuelto (en EAX) en nuestra variable

    ; Corregido: WriteConsoleA (A mayúscula, si no se usa WriteConsole genérico)
    ;           NULL (mayúsculas)
    ;           hConsoleOutput y dwBytesWritten ahora están declaradas.
    invoke WriteConsoleA, \
           hConsoleOutput, \
           ADDR msg, \
           LENGTHOF msg, \
           ADDR dwBytesWritten, \
           NULL

    RET ; Retorno del procedimiento
mostrarMensaje ENDP ; Corregido: Quitado el punto de '.mostrarMensaje'

; --- Programa Principal ---
start:              ; Punto de entrada del programa

    ; Corregido: El procedimiento 'mostrarMensaje' ahora está correctamente definido.
    call mostrarMensaje
    invoke ExitProcess, NULL ; Corregido: NULL (mayúsculas)

end start           ; Marca el final del código fuente y el punto de entrada
