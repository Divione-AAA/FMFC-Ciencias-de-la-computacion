#include<stdio.h>

int main(){
    FILE *f;
    f = fopen("file.txt","r");
    if(f == NULL){
        printf("Error");
        return 0;
    }
    char c;
    while((c = fgetc(f)) != EOF){
        printf("%c",c);
    }
    fclose(f);

    //lectura dinamica
    int a,b;
    FILE *stream = fopen("myfile.txt", "r");
    if(stream == NULL){
        perror("error de apertura de archivo");
        return -1;
    }
    fscanf(stream, "%d", &a);
    fscanf(stream, "%d", &b);
    fclose(stream);

    //escritura dinamica
    fprintf(f,"popopo le hiciste una amarre a la pieza %d",&a);
    fprintf(f,"\n");
    
    return 0;
}