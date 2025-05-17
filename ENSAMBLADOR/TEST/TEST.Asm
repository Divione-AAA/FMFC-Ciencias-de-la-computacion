.386
.model flat, stdcall
include kernel32.inc
includelib kernel32.lib

.stack
.data
.code

inicio:

invoke ExitProcess,0

end inicio