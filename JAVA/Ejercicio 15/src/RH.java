import java.util.HashSet;
import java.util.Set;

public class RH {

    private String trabajador[];
    private Set<Integer> set = new HashSet<>();

    public RH(String trabajador[]){
        this.trabajador=trabajador;
    }

    public int[] type(){

        int[] ans =new int[4];

        for (String s : trabajador) {
            if (s.charAt(3) == 'D') {
                ans[0]++;
            } else if (s.charAt(3) == 'O') {
                ans[1]++;
            } else if (s.charAt(3) == 'T') {
                ans[2]++;
            } else {
                ans[3]++;
            }
        }
        return ans;
    }
}
