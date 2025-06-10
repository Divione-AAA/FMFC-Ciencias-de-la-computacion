.386                ; Usar instrucciones 80386 y superiores
.model flat, stdcall ; Modelo de memoria flat, convención de llamada stdcall

option casemap :none ; No distinguir mayúsculas/minúsculas en nombres

include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\masm32.inc   ; <--- Asegúrate de incluir esta línea

includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\masm32.lib

.data               ; Sección de datos inicializados
    szText db "Hola Mundo desde VS Code y MASM!",0

.code               ; Sección de código

start:              ; Punto de entrada del programa

    invoke StdOut, addr szText      ; Imprime en consola
    invoke ExitProcess, 0

end start