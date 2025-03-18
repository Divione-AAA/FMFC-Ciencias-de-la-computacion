"""
Cruz y cero
"""

import tkinter as tk
from tkinter import messagebox

#debi ponerlo con mayusculas

player = 'X'
game_over = False

def check():
    """
    una funcion para verificar si el juego ya ha terminado
    """
    for i in range(3):
        if button[i][0]['text'] == button[i][1]['text'] == button[i][2]['text'] !='':
            return True
        if button[0][i]['text'] == button[1][i]['text'] == button[2][i]['text'] !='':
            return True
    if button[0][0]['text'] == button[1][1]['text'] == button[2][2]['text'] !='':
        return True
    return False

def button_click(row,col):
    """para manejar los clicks, la propiedad [text]"""
    global player, game_over
    if button[row][col]['text'] == '' and not game_over:
        button[row][col]['text'] = player
        button[row][col]['bg'] = '#37474F' if player == 'X' else '#455A64'
        if check():
            messagebox.showinfo("Cerito",f"jugador {player} gana")
            game_over = True
        elif all(button[row][col]['text'] != '' for row in range(3) for col in range(3)):
            messagebox.showinfo("cerito","empate")
            game_over = True
        else:
            player = '0' if player == 'X' else 'X'

def reset():
    """reseteo"""
    global player, game_over
    player = 'X'
    game_over = False
    for row in range(3):
        for col in range(3):
            button[row][col]['text'] = ''
            button[row][col]['bg'] = '#263238'

root = tk.Tk()
root.title("Cerito")
root.geometry("400x450")
root.configure(bg='#263238')

frame = tk.Frame(root, bg='#263238')
frame.place(relx=0.5,rely=0.5,anchor='center')
#arreglo de botones
button =[[tk.Button(frame,text='',font='normal 20 bold',width=5,height=2,bg='#263238',fg='white',
        command = lambda row = row, col = col: button_click(row,col))
        for col in range(3)] for row in range(3)]

for row in range(3):
    for col in range(3):
        button[row][col].grid(row=row, column=col,padx=10,pady=10)

root.mainloop()
            