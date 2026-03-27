def precio_accion(inversion_inicial, revalorizacion_pct, años):
    tasa = revalorizacion_pct / 100
    valor_final = inversion_inicial * (1 + tasa) ** años
    return valor_final
# Ejemplo: invierto 1000, se revaloriza 8% anual, en 5 años
print(precio_accion(10000, 20, 3))  # ≈ 1469.33
