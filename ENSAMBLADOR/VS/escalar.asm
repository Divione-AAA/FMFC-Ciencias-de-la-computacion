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
v1 dd 1,2,3,4,5
v2 dd 1,2,3,4,5
v3 dd 5 dup(?)
.code

suma proc uses eax ebx vs:sdword, vs1:sdword ,vr:sdword, n:dword
xor eax,eax
.repeat
    mov ebx,vs[eax*4]
    add ebx,vs1[eax*4]
    mov vr[eax*4],ebx
    inc eax
.until eax == n
ret
suma endp

escalar proc uses eax ebx vs:sdword, n:sdword, des:sdword, tam:sdword
    xor ebx,ebx
    mov eax,n
    .repeat
    mov ecx,vs[ebx*4]
    mul ecx
    mov des[ebx*4],ecx
    inc ebx
    .until ebx == tam
    ret
escalar endp

start:

invoke escalar, v1, -7, v1, lengthof v1
invoke suma, v1,v2,v3, lengthof v3

invoke ExitProcess, 0
end start