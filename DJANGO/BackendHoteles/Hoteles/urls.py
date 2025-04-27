from rest_framework import routers
from .api import HotelViewSet

router = routers.DefaultRouter()
router.register('api/hoteles', HotelViewSet, 'hoteles')

urlpatterns = router.urls