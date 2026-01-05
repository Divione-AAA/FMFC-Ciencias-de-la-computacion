//Ejemplo de promesa
const getData = () => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve("Datos recibidos");
    }, 2000);
  });
};
//Como usarla y capturar errores
getData().then(data => {console.log(data)}).catch(error => {console.log(error)});

//Ejemplo con async/await
const fetchData = async () => {//declaramos la funcion como async
  try {//try/catch para manejo de errores
    const response = await fetch("ejemplo.com");//enviamos la peticion y usamos await para esperar la respuesta
    const data = await response.json();//lo convertimos en json
    return data;
  } catch (error) {//manejo de errores muy importante
    console.error("Error:", error);
  }
};

fetchData();
