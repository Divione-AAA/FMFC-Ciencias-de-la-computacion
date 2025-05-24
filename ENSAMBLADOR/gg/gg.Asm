.386
.model flat, stdcall
include kernel32.inc
includelib kernel32.lib

.stack
.data
	num word -5
.code

inicio:
	mov AX,num
end inicio