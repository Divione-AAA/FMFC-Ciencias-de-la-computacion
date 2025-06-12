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
    flag BYTE 0
    arreglo dd 1,2,3,4,5
    target dd 5
.code
start:
    xor eax,eax
    xor ecx,ecx
    mov edx, lengthof arreglo
    .while ecx < edx
    mov ebx, ecx
    add ebx, edx
    shr ebx, 1
    mov ax,arreglo[ebx*4]
    .if ax==target
    mov flag,1
    .elseif ax<target
    mov ecx,ebx
    inc ecx
    .else
    mov edx,ebx
    .endif
    .endw
invoke ExitProcess, 0
end start