from django.db import models

# Create your models here.

class Hotel(models.Model):
    created_at = models.DateTimeField(auto_now_add=True)
    nombre = models.CharField(max_length=100, verbose_name="Nombre del Hotel")
    lugar = models.CharField(max_length=100, verbose_name="Ubicación")
    precio_por_noche = models.DecimalField(max_digits=10, decimal_places=2, verbose_name="Precio por Noche")
    estrellas = models.PositiveIntegerField(verbose_name="Estrellas", choices=[(i, f"{i} Estrella{'s' if i > 1 else ''}") for i in range(1, 6)])
    link_foto = models.URLField(max_length=500, verbose_name="Enlace de la Foto", blank=True, null=True)
    descripcion = models.TextField(verbose_name="Descripción", blank=True, null=True)

    def __str__(self):
        return self.nombre