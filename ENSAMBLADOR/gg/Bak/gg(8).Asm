.386
.model flat, stdcall
option casemap:none

include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
include \masm32\include\gdi32.inc
include \masm32\include\masm32.inc

includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
includelib \masm32\lib\gdi32.lib
includelib \masm32\lib\masm32.lib

; Constantes para controles
IDC_EDIT1   equ 1001
IDC_EDIT2   equ 1002
IDC_BUTTON  equ 1003
IDC_EDIT3   equ 1004

.data
ClassName   db "SumadoraClass",0
AppName     db "Sumadora MASM32",0
ButtonText  db "Sumar",0
Edit1Text   db "0",0
Edit2Text   db "0",0
EditClass   db "EDIT",0
ButtonClass db "BUTTON",0

.data?
hInstance  HINSTANCE ?
hwndMain   HWND ?
hwndEdit1  HWND ?
hwndEdit2  HWND ?
hwndButton HWND ?
hwndEdit3  HWND ?
num1       DWORD ?
num2       DWORD ?
result     DWORD ?
buffer     db 32 dup(?)
wc         WNDCLASSEX <?>
msg        MSG <?>

.code
WinMain proc
    ; Obtener instancia
    invoke GetModuleHandle, NULL
    mov hInstance, eax
    
    ; Registrar clase de ventana
    mov wc.cbSize, sizeof WNDCLASSEX
    mov wc.style, CS_HREDRAW or CS_VREDRAW
    mov wc.lpfnWndProc, offset WndProc
    mov wc.cbClsExtra, 0
    mov wc.cbWndExtra, 0
    mov eax, hInstance
    mov wc.hInstance, eax
    mov wc.hbrBackground, COLOR_BTNFACE+1
    mov wc.lpszMenuName, NULL
    mov wc.lpszClassName, offset ClassName
    invoke LoadIcon, NULL, IDI_APPLICATION
    mov wc.hIcon, eax
    mov wc.hIconSm, eax
    invoke LoadCursor, NULL, IDC_ARROW
    mov wc.hCursor, eax
    invoke RegisterClassEx, addr wc
    
    ; Crear ventana principal
    invoke CreateWindowEx, 0, addr ClassName, addr AppName,
           WS_OVERLAPPEDWINDOW xor WS_THICKFRAME xor WS_MAXIMIZEBOX,
           100, 100, 300, 200,
           NULL, NULL, hInstance, NULL
    mov hwndMain, eax
    
    ; Mostrar ventana
    invoke ShowWindow, hwndMain, SW_SHOWNORMAL
    invoke UpdateWindow, hwndMain
    
    ; Bucle de mensajes
    .while TRUE
        invoke GetMessage, addr msg, NULL, 0, 0
        .break .if (!eax)
        invoke TranslateMessage, addr msg
        invoke DispatchMessage, addr msg
    .endw
    
    mov eax, msg.wParam
    ret
WinMain endp

WndProc proc hWnd:HWND, uMsg:UINT, wParam:WPARAM, lParam:LPARAM
    .if uMsg == WM_CREATE
        ; Crear controles
        invoke CreateWindowEx, WS_EX_CLIENTEDGE, addr EditClass, addr Edit1Text, 
               WS_CHILD or WS_VISIBLE or ES_NUMBER or WS_BORDER,
               20, 20, 100, 25, hWnd, IDC_EDIT1, hInstance, NULL
        mov hwndEdit1, eax
        
        invoke CreateWindowEx, WS_EX_CLIENTEDGE, addr EditClass, addr Edit2Text, 
               WS_CHILD or WS_VISIBLE or ES_NUMBER or WS_BORDER,
               20, 60, 100, 25, hWnd, IDC_EDIT2, hInstance, NULL
        mov hwndEdit2, eax
        
        invoke CreateWindowEx, 0, addr ButtonClass, addr ButtonText, 
               WS_CHILD or WS_VISIBLE or BS_DEFPUSHBUTTON,
               140, 40, 80, 25, hWnd, IDC_BUTTON, hInstance, NULL
        mov hwndButton, eax
        
        invoke CreateWindowEx, WS_EX_CLIENTEDGE, addr EditClass, NULL, 
               WS_CHILD or WS_VISIBLE or ES_READONLY or WS_BORDER,
               20, 100, 100, 25, hWnd, IDC_EDIT3, hInstance, NULL
        mov hwndEdit3, eax
        
    .elseif uMsg == WM_COMMAND
        mov eax, wParam
        .if ax == IDC_BUTTON
            ; Obtener y sumar valores
            invoke GetWindowText, hwndEdit1, addr buffer, 32
            invoke atodw, addr buffer
            mov num1, eax
            
            invoke GetWindowText, hwndEdit2, addr buffer, 32
            invoke atodw, addr buffer
            mov num2, eax
            
            mov eax, num1
            add eax, num2
            mov result, eax
            
            invoke dwtoa, result, addr buffer
            invoke SetWindowText, hwndEdit3, addr buffer
        .endif
        
    .elseif uMsg == WM_DESTROY
        invoke PostQuitMessage, 0
        
    .else
        invoke DefWindowProc, hWnd, uMsg, wParam, lParam
        ret
    .endif
    
    xor eax, eax
    ret
WndProc endp

start:
    call WinMain
    invoke ExitProcess, eax
end start