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
origen  dd 1,2,3,4,5,6,7,8,9,10
destino dd 10 dup(?)
.code

esprimo proc uses eax ebx ecx num:dword
    mov eax, num
    cmp eax, 2
    jl  no_primo      ; Menor que 2 no es primo
    mov ecx, 2
    mov ebx, eax
    dec ebx           ; ebx = num-1
check_loop:
    cmp ecx, ebx
    jg  es_primo
    mov edx, 0
    mov eax, num
    div ecx
    cmp edx, 0
    je  no_primo
    inc ecx
    jmp check_loop
es_primo:
    mov eax, 1
    ret
no_primo:
    mov eax, 0
    ret
esprimo endp

primo proc uses esi edi
    mov esi, 0
next:
    cmp esi, 10
    jge fin
    push origen[esi*4]
    call esprimo
    mov destino[esi*4], eax
    inc esi
    jmp next
fin:
    ret
primo endp

start:
    call primo
    invoke ExitProcess, 0
end start