.386
.model flat, stdcall
include kernel32.inc
includelib kernel32.lib

.stack
.data
	x  word  1A5Fh  
	y  word    0100h  
	z  dword  0 
.code

inicio:
	mov AX,-5
end inicio