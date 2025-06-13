.586
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\masm32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\masm32.lib 
.stack
.data
    arreglo dd "hola mundo",0
    destino dd 10 dup(?)
.code

Mayusculas uses eax ebx, arr:dword
    xor eax,eax
    mov ebx,lengthof arreglo
    .repeat
    mov ecx,arr[eax*4]
    .if ecx >=97 && ecx <=122
    inc eax
    .until eax == lengthof arr

ret
Mayusculas endp

CopyCadena  arr:dword, arr1:dword

ret
end CopyCadena

start:

invoke ExitProcess, 0
end start