#include <ctype.h>
#include<stdio.h>
#include<string.h>

char* procesar_cadena(const char* str) {
  //aqui obtenemos el tamano del string enviado a la funcion
  const int len = strlen(str);
  //printf("%d",len);
  //printf("\n");
  char cad[len];
  //copiamos el valor a otra para modificarlo
  strcpy(cad,str);
  //convertimos a mayusculas con toupper
  for (int i = 0; i < len; i++) {
    cad[i]=toupper(cad[i]);
  }
  //printf("%s", cad);
  //printf("\n");
  //la revertimos
  strrev(cad);
  printf("%s", cad);
  printf("\n");

  //declaramos una nueva variable para almacenarlas solo con caracteres
  char cadena[len];
  int t=0;
  for (int i = 0; i < len; i++) {
    //usamos las propiedades del codigo ASCII (no se como se escribe xD)
    if (cad[i]>=65 && cad[i]<=90) {
        //printf("%c", cad[i]);
        //printf("\n");
        cadena[t]=cad[i];
        t++;
    }
  }
  //copiamos a la cadena para retrornar
  char ans[t+1];

  for (int i = 0; i <=t; i++) {
    ans[i]=cadena[i];
  }

  //printf("%s", ans);
  //printf("\n");
  for (int i = 0; i <=t; i++) {
    printf("%c",ans[i]);
  }
  //retornamos
  return ans;
}

int main(){

  char str[10000];
  fgets(str,10000,stdin);
  int tam=strlen(str);
  printf("%d",tam);
  procesar_cadena(str);

return 0;}