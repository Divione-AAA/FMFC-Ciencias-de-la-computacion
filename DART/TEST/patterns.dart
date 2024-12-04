
void main() {
  var (a,b,c) = ret();
  print(a);
  print(b); 
  print(c);
  String s = "Juanito Perez";
  var m=ret();
  print(m.$2);
  printName(name: s);
  
  printName(name: s,age: 10);
  print("hello");
}

(int,String,bool) ret(){
  return (1,"let's strike",true);
}

void printName({required String name,int ? age}){
  print(name);
} 