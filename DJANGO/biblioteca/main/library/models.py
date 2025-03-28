from django.db import models

# Create your models here.

class Project(models.Model):
    tittle = models.CharField(max_length=200)
    description = models.TextField(default='')
    autor = models.CharField(max_length=100)
    created_ad = models.DateField(auto_now_add=True)