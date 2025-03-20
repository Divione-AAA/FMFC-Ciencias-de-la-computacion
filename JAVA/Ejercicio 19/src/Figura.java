public class Figura {

    double volumen;

    public Figura(double a) {
        this.volumen = a;
    }
}
/*
* Se desea hacer un programa que ayude a un diseñador a construir una plaza que está formada por piezas que pueden
* ser cuadrados, rectángulos ó triángulos. Cada pieza tiene una denominación y las medidas de sus respectivos lados.
El sistema debe ser capaz de permitir incorporar una pieza, quitar una pieza y calcular en cualquier momento el área
* total de la misma.
Este ejercicio se realizó en una CP anterior, pero la plaza estaba constituida solo por rectángulos.
*  Lo nuevo en este caso es la necesidad de tener piezas de varios tipos y de nuevo se plantea calcular el área de la plaza,
*  para lo que hay que calcular el área de cada pieza, por lo que es necesario incorporar un método que calcule la misma en
* cada una de las piezas.

* */