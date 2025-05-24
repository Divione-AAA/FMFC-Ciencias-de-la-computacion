.386
.model flat, stdcall
option casemap:none

include \masm32\include\windows.inc
include \masm32\include\user32.inc
include \masm32\include\kernel32.inc

includelib \masm32\lib\user32.lib
includelib \masm32\lib\kernel32.lib

.data
    AppName db "Sumadora MASM32 (Sin Consola)",0

.code
WinMain proc
    invoke MessageBox, 0, addr AppName, addr AppName, MB_OK
    invoke ExitProcess, 0
WinMain endp

start:
    call WinMain
end start