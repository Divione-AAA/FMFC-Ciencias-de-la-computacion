void main() async{
  countDown().listen((val) {
    print(val);
  }, onDone: (){
    print("done");
  });
}

Stream<int> countDown() async* {
  for (int i = 10; i >= 0; i--) {
    yield i;
    //await Future.delayed(Duration(seconds: 1));
  }
}