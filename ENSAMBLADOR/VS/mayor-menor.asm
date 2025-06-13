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
array dd 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15
r1 db ?
r2 db ?
r3 dd ?
.code

menor proc uses edi eax, arr:dword, tam:dword

xor eax,eax
.repeat
    .if edi < arr[eax*4]
        mov edi,eax
    .endif
    inc eax
.until eax == tam
ret
menor endp

mayor proc uses edi eax arr:dword, tam:dword
xor eax,eax
.repeat
    .if edi > arr[eax*4]
        mov edi,eax
    .endif
    inc eax
.until eax == tam
ret
mayor endp

start:
    invoke menor, addr array, lengthof array
    mov r3, edi
    mov eax,r3
    mov r1, al
    invoke StdOut, addr r1
    invoke mayor, addr array, lengthof array
    mov r3, edi
    mov eax,r3
    mov r2, al
    invoke StdOut, addr r2
invoke ExitProcess, 0
end start