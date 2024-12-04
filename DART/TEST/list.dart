class Student{

  final String name;
  int age =18;
  Student(this.name);

  List students=[
    Student("Marcos"),
    Student("Edgar"),
    Student("Keyla"),
    "Strings",
  ];
}

class Classroom{
  
  List<Student> list=[
    Student("Marcos"),
    Student("Edgar"),
    Student("Keyla")
  ];

}

void main(){
  Student juan=Student("Juan");
  List list=[juan];
  list.insert(1, juan);
  print(list[1].age);
  print(list[0].age+2);
}