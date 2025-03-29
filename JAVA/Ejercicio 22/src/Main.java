public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        Aritmetica m = new Sucesion3(2);
        System.out.println(m.sucesion());
        System.out.println(m.sucesion());
        m.reset();
        System.out.println(m.sucesion());
    }
}

/*
*
* Sucesion interface aritmetica que permita iniciar la sucesion con un valor dado
* obtener el siguiente elemento
* reiniciar la sucecion con el valor con el que se inicio
* 2 clases sucecion2, sucesion 3 que obtenga el elemento aumentado en 2 y 3
* 
*
* */