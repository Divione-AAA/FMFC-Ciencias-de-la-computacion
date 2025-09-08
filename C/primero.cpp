#include<bits/stdc++.h>

int32_t main() {
    int i;
    scanf("%d",&i);

    for(int j=2;j<i;j++){
        if(!(i%j)){
            printf("no es primo");
            break;
        }
        if(j==i-1){
            printf("Es primo");
            break;
        }
    }

    printf("\n");
    printf(i%2 ? "impar" : "par");
    printf("\n");
    int s=0;

    while(i>0){
        s+=i%10;
        i = i-(i%10);
        i=i/10;
        
    }

    printf("la suma de los digitos es de: ");
    printf("%d",s);

return 0;}