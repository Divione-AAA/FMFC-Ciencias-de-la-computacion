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
.code
start:
    ;para su simplicidad se siguen usando las macrodefiniciones
    mov eax,0
    .while eax<5
    inc eax
    .endw
    mov eax,0
    .repeat
    inc eax
    .until eax == 5
    invoke ExitProcess, 0
end start