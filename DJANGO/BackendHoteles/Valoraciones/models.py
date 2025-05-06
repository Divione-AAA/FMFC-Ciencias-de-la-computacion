from django.db import models

# Create your models here.

class Valoracion(models.Model):
    created_at = models.DateTimeField(auto_now_add=True)
    descripcion = models.TextField(verbose_name="Descripcion", null=True, blank=True)
    link_foto = models.URLField(verbose_name="Link foto", null=True, blank=True)

    def __str__(self):
        return str(self.created_at)
