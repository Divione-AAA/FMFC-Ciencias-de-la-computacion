const user = {
    edad: 20,
    nombre: "Juan",
    apellido: "Perez",
    email: "juanperez@gmail.com"
}

const {edad, nombre, apellido, email} = user;
//Esto es destructuracion de un objeto

const mostrarNombre = ({ name }: { name: string }) => {
  console.log(name);
};
//destructuracion en parametros de funciones