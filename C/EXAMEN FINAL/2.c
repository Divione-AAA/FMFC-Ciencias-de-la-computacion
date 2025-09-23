#include<stdio.h>
#include<math.h>

typedef struct {
     double x,y;
}punto;

char c;

double dist(punto other,punto este){
     return sqrt(pow(este.x-other.x,2)+pow(este.y-other.y,2));
}

double max(double este, double otro,char t) {
     if(este<otro) c = t;
     return este > otro ? este : otro;
};

int main(){

     punto p1,p2,p3,p4,p5;
     printf("Introduce las coordenadas de 5 puntos en el plano:");
     double d=0;

     scanf("%lf",&p1.x);
     scanf("%lf",&p1.y);
     scanf("%lf",&p2.x);
     scanf("%lf",&p2.y);
     d=max(d,dist(p1,p2),'2');
     scanf("%lf",&p3.x);
     scanf("%lf",&p3.y);
     d=max(d,dist(p1,p3),'3');
     scanf("%lf",&p4.x);
     scanf("%lf",&p4.y);
     d=max(d,dist(p1,p4),'4');
     scanf("%lf",&p5.x);
     scanf("%lf",&p5.y);
     d=max(d,dist(p1,p5),'5');

     printf("La mayor distancia es de: ");
     printf("%lf\n",d);
     printf("Con el punto numero: ");
     printf("%c",c);
     printf("\n");

     //printf("%lf",dist(p1,p3));

return 0;}
