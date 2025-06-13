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
matriz dd (2*4) dup(?)
matriz1 dd (2*4) dup(?)
.code

sumar proc uses eax ebx ecx arr:sdword, arr1:sdword, r:sdword, n:dword
xor eax,eax
.repeat
    mov ebx,arr[eax*4]
    add ebx,arr1[eax*4]
    mov r[eax*4],ebx
    inc eax
.until eax == n
ret
sumar endp

start:
invoke ExitProcess, 0
end start