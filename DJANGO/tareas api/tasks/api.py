from rest_framework import viewsets, permissions
from .serializer import TaskSerializer
from .models import Task

class TaskViewSet(viewsets.ModelViewSet):
    queryset = Task.objects.all()
    permissions_classes = [permissions.AllowAny]
    serializer_class = TaskSerializer