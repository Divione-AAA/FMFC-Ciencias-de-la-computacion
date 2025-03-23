from django.db import models

# Create your models here.
class Task(models.Model):
    tittle = models.CharField(max_length=200)
    description = models.CharField(max_length=200)
    done = models.BooleanField(default=False)
    created_at = models.DateTimeField(auto_now_add=True)