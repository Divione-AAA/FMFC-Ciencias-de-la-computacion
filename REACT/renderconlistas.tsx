items.map(item => (
  <Text key={item.id}>{item.name}</Text>
))
//esto es un mapeo

//Las flatList son mas eficientes en mobile
<FlatList
  data={restaurants}
  keyExtractor={item => item.id.toString()}
  renderItem={({ item }) => (
    <Component namevar={item} />
  )}
/>
