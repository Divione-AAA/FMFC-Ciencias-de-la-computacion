from rest_framework import routers
from library.api import ProjectViewSet

routers = routers.DefaultRouter()
routers.register('api/project', ProjectViewSet,'project')

urlpatterns = routers.urls