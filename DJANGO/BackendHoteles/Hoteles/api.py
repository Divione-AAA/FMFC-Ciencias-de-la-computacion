from rest_framework import viewsets
from .models import Hotel
from .serializers import HotelSerializer
from rest_framework import permissions as permission

class HotelViewSet(viewsets.ModelViewSet):
    permission_classes = [permission.AllowAny]
    queryset = Hotel.objects.all()
    serializer_class = HotelSerializer