"""
App simple
"""

import tkinter as tk
from tkinter import messagebox
from openpyxl import Workbook

wb = Workbook()
ws = wb.active
ws.append(["nombre","edad","mail","telefono","direccion"])

def guardad_datos():
    """guardar datos"""
    nombre = entry_nombre.get()
    edad = entry_edad.get()
    email = entry_email.get()
    telefono = entry_telefono.get()
    direccion = entry_direccion.get()

    if not nombre or not edad or not email or not telefono or not direccion:
        messagebox.showwarning("Advertencia", "todos los campos deben estar llenos")
        return
    try:
        edad = int(edad)
        telefono = int(telefono)
    except ValueError:
        messagebox.showwarning("Advertencia", "los campos telefono y edad deben ser numeros")
        return
    ws.append([nombre,edad,email,telefono,direccion])
    wb.save("dato.xlsx")



root = tk.Tk()
root.title("Formulario")

label_nombre = tk.Label(root, text="nombre")
label_nombre.grid(row=0,column=0,padx=10,pady=5)
entry_nombre = tk.Entry(root)
entry_nombre.grid(row=0,column=1,padx=10,pady=5)

label_edad = tk.Label(root, text="edad")
label_edad.grid(row=1,column=0,padx=10,pady=5)
entry_edad = tk.Entry(root)
entry_edad.grid(row=1,column=1,padx=10,pady=5)

label_email = tk.Label(root, text="email")
label_email.grid(row=2,column=0,padx=10,pady=5)
entry_email = tk.Entry(root)
entry_email.grid(row=2,column=1,padx=10,pady=5)

label_telefono = tk.Label(root, text="telefono")
label_telefono.grid(row=3,column=0,padx=10,pady=5)
entry_telefono = tk.Entry(root)
entry_telefono.grid(row=3,column=1,padx=10,pady=5)

label_direccion = tk.Label(root, text="direccion")
label_direccion.grid(row=4,column=0,padx=10,pady=5)
entry_direccion = tk.Entry(root)
entry_direccion.grid(row=4,column=1,padx=10,pady=5)

boton_guardar = tk.Button(root,text="Guardar",command=guardad_datos)
boton_guardar.grid(row=5,column=0, columnspan=2, padx = 10, pady = 10)

root.mainloop()
