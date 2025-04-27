from rest_framework import serializers
from .models import Hotel

class HotelSerializer(serializers.ModelSerializer):
    class Meta:
        model = Hotel
        fields = ( 'nombre', 'lugar', 'precio_por_noche', 'estrellas', 'link_foto', 'descripcion')
        read_only_fields = ('created_at','id')