.386
.model flat, stdcall
option casemap:none

; Incluir las bibliotecas necesarias
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
include \masm32\include\gdi32.inc

includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
includelib \masm32\lib\gdi32.lib

; Definición de constantes para los controles
IDC_EDIT1   equ 1001
IDC_EDIT2   equ 1002
IDC_BUTTON  equ 1003
IDC_EDIT3   equ 1004

.data
ClassName   db "SimpleSumWindowClass",0
AppName     db "Sumadora MASM32",0
ButtonText  db "Sumar",0
Edit1Text   db "0",0
Edit2Text   db "0",0
FormatStr   db "%d",0

.data?
hInstance  HINSTANCE ?
hwndEdit1  HWND ?
hwndEdit2  HWND ?
hwndButton HWND ?
hwndEdit3  HWND ?
num1       dd ?
num2       dd ?
result     dd ?
buffer     db 32 dup(?)

.code
start:
    invoke GetModuleHandle, NULL
    mov hInstance, eax
    invoke DialogBoxParam, hInstance, 0, 0, addr WndProc, 0
    invoke ExitProcess, 0

WndProc proc hWnd:HWND, uMsg:UINT, wParam:WPARAM, lParam:LPARAM
    LOCAL ps:PAINTSTRUCT
    LOCAL hdc:HDC
    LOCAL rect:RECT
    
    .if uMsg == WM_INITDIALOG
        ; Crear los controles de edición
        invoke CreateWindowEx, WS_EX_CLIENTEDGE, "EDIT", addr Edit1Text, 
               WS_CHILD or WS_VISIBLE or ES_NUMBER or WS_BORDER,
               20, 20, 100, 25, hWnd, IDC_EDIT1, hInstance, NULL
        mov hwndEdit1, eax
        
        invoke CreateWindowEx, WS_EX_CLIENTEDGE, "EDIT", addr Edit2Text, 
               WS_CHILD or WS_VISIBLE or ES_NUMBER or WS_BORDER,
               20, 60, 100, 25, hWnd, IDC_EDIT2, hInstance, NULL
        mov hwndEdit2, eax
        
        ; Crear el botón
        invoke CreateWindowEx, 0, "BUTTON", addr ButtonText, 
               WS_CHILD or WS_VISIBLE or BS_DEFPUSHBUTTON,
               140, 40, 80, 25, hWnd, IDC_BUTTON, hInstance, NULL
        mov hwndButton, eax
        
        ; Crear el cuadro de resultado (solo lectura)
        invoke CreateWindowEx, WS_EX_CLIENTEDGE, "EDIT", 0, 
               WS_CHILD or WS_VISIBLE or ES_READONLY or WS_BORDER,
               20, 100, 100, 25, hWnd, IDC_EDIT3, hInstance, NULL
        mov hwndEdit3, eax
        
    .elseif uMsg == WM_COMMAND
        mov eax, wParam
        .if ax == IDC_BUTTON
            ; Obtener el texto de los edit controls
            invoke GetWindowText, hwndEdit1, addr buffer, 32
            invoke atodw, addr buffer
            mov num1, eax
            
            invoke GetWindowText, hwndEdit2, addr buffer, 32
            invoke atodw, addr buffer
            mov num2, eax
            
            ; Sumar los números
            mov eax, num1
            add eax, num2
            mov result, eax
            
            ; Mostrar el resultado
            invoke dwtoa, result, addr buffer
            invoke SetWindowText, hwndEdit3, addr buffer
        .endif
        
    .elseif uMsg == WM_CLOSE
        invoke EndDialog, hWnd, 0
        
    .else
        mov eax, FALSE
        ret
    .endif
    
    mov eax, TRUE
    ret
WndProc endp

end start