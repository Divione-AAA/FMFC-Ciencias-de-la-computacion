from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
#aqui se crean al parecer el html
def hello(request):
    
    return HttpResponse("<h1>Hello World</h1>")
