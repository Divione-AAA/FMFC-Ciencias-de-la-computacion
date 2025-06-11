.386
.model flat, stdcall
option casemap :none
include \masm32\include\masm32.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\masm32.lib
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib

.stack
.data
    num dd 561
.code
start:
    mov eax,num
    .if eax == 561
    .elseif eax == 12
    .else
    .endif
end start