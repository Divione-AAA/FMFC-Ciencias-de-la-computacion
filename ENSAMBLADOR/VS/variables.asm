.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\masm32.inc  
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\masm32.lib
.stack
.data?
    k dw ?
    i dw ?
    j dw ?
    x dd ?
    y dd ?
    z dd ?
    s db ?
    v db ?
    w dw ?
    p dd ?
.code
start:
    movsx eax, k ;esto es por tener distinto tamano y no tener signo
    add j,ax ;al ser de diferentes tamanos no se puede sumar por lo que usamos solo el inicio del operando
    ;al ser de 2 bits no usamos la e del inicio porq es para 32
    mov bx,j
    sub k,bx
    mov cx,k
    mov i,cx
    ;multiplicacion de dos numeros de 32 bits
    movsx eax,i
    mul j
    movsx edx,j
    mov x,edx
    ;dividir dos numeros de 32 bits
    mov ax,k
    cwd
    idiv j
    mov i,ax
    invoke ExitProcess, 0
end start
