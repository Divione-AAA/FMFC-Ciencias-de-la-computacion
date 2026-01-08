//Son datos que un componente recibe desde fuera
{/* */}

interface GreetingProps {//Es necesario definir el tipo de dato que se espera recibir, no es js.
  name?: string;
}

const Greeting = ({ name }: GreetingProps) => {
  return <Text>Hola {name}</Text>;
};