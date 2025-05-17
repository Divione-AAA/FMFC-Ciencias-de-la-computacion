.386
.model flat, stdcall
option casemap:none

; Incluir las bibliotecas necesarias
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc

includelib \masm32\lib\kernel32.lib

.data
    holaMundo db "Hola Mundo!", 13, 10  ; Texto con retorno de carro y nueva línea
    holaLen dd $-holaMundo              ; Longitud del texto
    stdout dd ?                          ; Handle para salida estándar

.code
start:
    ; Obtener el handle de salida estándar
    invoke GetStdHandle, STD_OUTPUT_HANDLE
    mov stdout, eax
    
    ; Escribir en la consola
    invoke WriteConsole, stdout, addr holaMundo, holaLen, 0, 0
    invoke Sleep,11000
    
    ; Salir del programa
    invoke ExitProcess, 0
    
end start