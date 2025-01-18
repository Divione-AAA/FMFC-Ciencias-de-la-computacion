public class Facultad{

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public int longitud(){
        char c;
        int ans=0;
        for(int i=0;i<url.length();i++){
            c=url.charAt(i);
            if(c=='.'){
                ans++;
            }
        }
        return ans+1;
    }

    public String domGen(){
        char c;
        int a=0;
        for(int i=url.length()-1;i>=0;i--){
            c=url.charAt(i);
            if(c=='.'){
                a=i;
                return url.substring(a);
            }
        }
        return "";
    }
}
