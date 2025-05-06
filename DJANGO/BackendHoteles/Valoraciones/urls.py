from rest_framework import routers
from .api import ValoracionViewSet

router = routers.DefaultRouter()
router.register('api/valoracion', ValoracionViewSet, 'valoracion')

urlpatterns = router.urls