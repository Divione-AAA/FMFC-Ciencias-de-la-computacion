# Generated by Django 5.1.7 on 2025-03-28 05:16

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Project',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('tittle', models.CharField(max_length=200)),
                ('autor', models.CharField(max_length=100)),
                ('created_ad', models.DateField(auto_now_add=True)),
            ],
        ),
    ]
