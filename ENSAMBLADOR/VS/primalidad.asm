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
origen dd 1,2,3,4,5,6,7,8,9,10
destino dd  10 dup(?)
.code

esprimo proc uses eax num:dword, arr:dword
    mov ebx,2
    mov eax,num
    .repeat
    div ebx
    .if edx == 0 && ebx !=num
    mov ecx,0
    ret
    .endif
    inc ebx
    .until ebx == num
    mov ecx,1
    ret
esprimo endp

primo proc uses eax tam:dword, arr:dword
    xor esi,esi
    .repeat
    invoke esprimo, arr[esi*4], arr
    mov origen[esi*4],ecx
    inc esi
    .until esi == tam
    ret
primo endp

start:
    invoke primo, lengthof destino, destino
invoke ExitProcess, 0
end start