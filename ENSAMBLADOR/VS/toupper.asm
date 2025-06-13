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
    arreglo dd "hola"
    destino dd 10 dup(?)
.code

mayusculas proc uses eax ecx edx arr:dword, tam:dword
    xor eax,eax
    .repeat
    mov ecx,arr[eax*4]
    .if ecx >=97 && ecx <=122
    sub ecx,32
    mov arr[eax*4],ecx
    .endif
    inc eax
    .until eax == tam
    mov edx,arr
    ret
mayusculas endp

CopyCadena  proc arr:dword, arr1:dword
    invoke mayusculas, arr, lengthof arr
    mov arr1,edx
    ret
CopyCadena endp

start:
    invoke CopyCadena, addr arreglo, addr destino
    invoke ExitProcess, 0
end start