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
    arreglo dd 1,2,3,4,5
    v1 dd 100 dup(?)
    arregloSignado sdword 50 dup(?)
.code
start:
;modo de direccionamiento indexado o basado indexado
    xor ebx,ebx ;pone el registro ebx en cero
    .repeat 
        mov eax, arreglo[ebx*4] ;accede a los elementos del registro
        ;*4 dd *2 dw *1 db 32 16 8 bits => 4 2 1 bytes
        inc ebx; incrementa en uno
    .until ebx == lengthof arreglo;hasta que sea igual al tamano
invoke ExitProcess, 0
end start