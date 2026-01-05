//Ejecucion de promesas en paralelo
const promesasParalelas = Promise.all([
  fetch("https://jsonplaceholder.typicode.com/todos/1"),
  fetch("https://jsonplaceholder.typicode.com/todos/2"),
  fetch("https://jsonplaceholder.typicode.com/todos/3")
]).then(respuestas => Promise.all(respuestas.map(res => res.json())))
//Promesas race, si una se niega, se niega toda la promesa
Promise.race([
  fetch("https://jsonplaceholder.typicode.com/todos/1"),
  fetch("https://jsonplaceholder.typicode.com/todos/2"),
  fetch("https://jsonplaceholder.typicode.com/todos/3")
]).then(res => res.json())