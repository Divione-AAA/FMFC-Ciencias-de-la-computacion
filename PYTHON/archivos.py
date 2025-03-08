"""
Trabajo con archivos
"""

with open("archivo.txt","r",encoding="utf-8") as ARCHIVO:
    #Abre un archivo y lo lee
    print(ARCHIVO.read())
    ARCHIVO.close()
