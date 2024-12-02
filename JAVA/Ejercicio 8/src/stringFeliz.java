public class stringFeliz {
    public static void main(String[] args) {
        System.out.println(feliz(7896532));




    }



    public static boolean feliz(long n){
        String str=""+n;


        while(true){

            long temp=0;

            for(int i=0;i<str.length();i++){
                long w=Long.parseLong(str.charAt(i)+"");
                temp+=w*w;
            }
            if(temp==1){
                return true;
            }else if(temp==n){
                return false;
            }

            str=temp+"";
        }

    }
}
