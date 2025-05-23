from .models import Project
from rest_framework import serializers

class ProjectSerializer(serializers.ModelSerializer):
    class Meta:
        model = Project
        fields = ('tittle', 'description', 'autor')
        read_only_fields = ('created_ad',)