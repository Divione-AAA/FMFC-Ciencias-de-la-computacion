function sumar(a: number, b: number): number {
  return a + b;
}
//Esta es la forma basica de declarar una funcion en TypeScript

const restar = (a: number, b: number): number => {
  return a - b;
};

//Esta es la forma ES6 de declarar una funcion

const multiplicar = (a: number, b: number): number => a * b;

//Esta es la forma corta de declarar una funcion

const arrow = (): string => "Hola Mundo";

//Esta es la forma de una funcion sin parametros

//Las funciones no crean su propio this por lo que se debe hacer de la siguiente manera

interface IPersona{
  edad: number;
}//declaramos su interfaaz

function Persona(this: IPersona) {//Le enviamos su this
  this.edad = 20;

  setTimeout(() => {
    console.log(this.edad); // 20
  }, 1000);
}
