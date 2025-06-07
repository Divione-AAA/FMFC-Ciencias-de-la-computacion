.386                ; Usar instrucciones 80386 y superiores
.model flat, stdcall ; Modelo de memoria flat, convención de llamada stdcall

option casemap :none ; No distinguir mayúsculas/minúsculas en nombres

include \masm32\include\windows.inc
include \masm32\include\user32.inc
include \masm32\include\kernel32.inc

includelib \masm32\lib\user32.lib
includelib \masm32\lib\kernel32.lib

.data               ; Sección de datos inicializados
    szCaption db "MASM 32-bit",0  ; Título de la ventana
    szText    db "Hola Mundo desde VS Code y MASM!",0 ; Mensaje

.code               ; Sección de código

start:              ; Punto de entrada del programa

    invoke MessageBox, NULL, addr szText, addr szCaption, MB_OK
    invoke ExitProcess, NULL

end start