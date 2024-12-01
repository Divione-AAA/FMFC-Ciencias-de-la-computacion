public class Punto{
    private double x,y;

    public void setPunto(double c,double v){
        this.x=c;
        this.y=v;
    }

    public int determinarCuadrante(){

        if(x>0 && y>0){
            return 1;
        }else if(x<0 && y>0){
            return 2;
        }else if(x<0 && y<0){
            return 3;
        }else if(x>0 && y<0){
            return 4;
        }
        return 0;
    }

    public double determinarDistancia(){
        return Math.sqrt((Math.pow(x,2)+Math.pow(y,2)));
    }
}
