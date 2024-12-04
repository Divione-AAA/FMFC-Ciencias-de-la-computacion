void main(){
  try{
    print(5);
    print(5~/0);
  }catch(e){
    print(e);
  }finally{
    print("finally block");
  }
}