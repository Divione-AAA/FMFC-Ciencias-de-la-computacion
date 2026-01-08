import { View, Text } from "react-native";
import React from "react";

const Screen = () => {
  const [items, setItems] = useState<Item[]>([]);
  {
    /*Se usa para estados locales */
  }
  const mounted = useRef(false);
  {
    /*Guarda valores sin usar prerender */
  }

  useEffect(() => {
    {
      /*Se ejecuta despues del render */
    }
    if (!mounted.current) {
      mounted.current = true;
      fetch("gg.pro");
    }
  }, []);
  {
    /*Memoriza valores */
  }
  const total = useMemo(() => items.length, [items]);

  return <Text>Total: {total}</Text>;
};
