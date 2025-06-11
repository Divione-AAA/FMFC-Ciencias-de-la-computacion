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
    buffer dd 100 dup(?)
    arregloSignado sdword 50 dup(?)
.code
start:
;modo de direccionamiento indexado o basado indexado

invoke ExitProcess, 0
end start