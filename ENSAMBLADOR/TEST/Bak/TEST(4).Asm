section .text
    global _start

_start:
    mov rax, 60     ; C�digo de salida del sistema
    mov rdi, 0      ; C�digo de salida 0 (�xito)
    syscall         ; Llamada al sistema para salir
