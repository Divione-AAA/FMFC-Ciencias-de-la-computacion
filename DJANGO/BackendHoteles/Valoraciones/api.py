from rest_framework import viewsets
from .models import Valoracion
from .serializers import ValoracionesSerializer
from rest_framework import permissions as permission

class ValoracionViewSet(viewsets.ModelViewSet):
    permission_classes = [permission.AllowAny]
    queryset = Valoracion.objects.all()
    serializer_class = ValoracionesSerializer