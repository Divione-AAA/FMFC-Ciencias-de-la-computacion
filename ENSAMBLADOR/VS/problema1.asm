.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\masm32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\masm32.lib 
.stack
.data
    arreglo1 dd 1,2,3,4,5
    arreglo2 dd 5 dup(?)
    arreglo3 dd 5 dup(?)
.code
start:
    xor eax,eax ;incializas un registro en 0
    mov ebx, lengthof arreglo1 ;copias a un registro el tamano
    .while eax < lengthof arreglo1 ;mientras el tamano sea menor q cero
        mov ecx, arreglo1[eax*4] ;copio el elemento de la iteracion a un registro temporal
        mov arreglo2[eax*4],ecx ;copio el elemento temporal al inicio
        mov arreglo3[ebx*4],ecx ;copio el temporal al final
        inc eax ;incremento el tamano para que lo recorra
        dec ebx ;decremento en igual medida para que sepa cual es el final en forma decreciente
    .endw
invoke ExitProcess, 0
end start