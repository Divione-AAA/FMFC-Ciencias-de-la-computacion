from rest_framework import serializers
from .models import Valoracion

class ValoracionesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Valoracion
        fields = ('descripcion', 'link_foto')
        read_only_fields = ('created_at',)