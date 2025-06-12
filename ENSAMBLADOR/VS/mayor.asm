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
    mayor dd ?
    menor dd ?
.code
start:
    xor ebx,ebx
    .repeat
    mov eax, arreglo[ebx*4]
    .if mayor < eax
    mov mayor,eax
    .endif
    .if menor < eax
    mov menor,eax
    .endif
    inc ebx
    .until ebx == lengthof arreglo
invoke ExitProcess, 0
end start