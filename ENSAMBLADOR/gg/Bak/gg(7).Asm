.386
.model flat, stdcall
option casemap:none

; Incluir las bibliotecas necesarias
include windows.inc
include kernel32.inc
include user32.inc

includelib kernel32.lib
includelib user32.lib

.data
    holaMundo db "Hola Mundo!", 0
    titulo db "Mensaje MASM32", 0

.code
start:
    ; Mostrar un mensaje usando MessageBox
    invoke MessageBox, NULL, addr holaMundo, addr titulo, MB_OK
    
    ; Salir del programa
    invoke ExitProcess, 0
    
end start