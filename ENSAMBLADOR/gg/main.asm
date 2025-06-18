option casemap:none     ; Desactiva la conversión automática de mayúsculas/minúsculas

extern ExitProcess:proc
extern printf:proc

.data
    msg db "Hola, mundo!", 0

.code
main PROC
    sub rsp, 28h    ; Ajusta el stack según convención Windows x64
    lea rcx, msg    ; Pasa el mensaje como argumento a printf
    call printf     ; Llama a printf
    call ExitProcess ; Finaliza el programa
main ENDP

END main
