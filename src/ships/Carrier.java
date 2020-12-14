package ships;

import java.util.ArrayList;
import java.util.Arrays;

public class Carrier extends Ship{

    final int length;

    public Carrier(int [] startIndex, int direction){
        super("Carrier",startIndex,direction);
        this.length = 5;
    }
//    Length will never change thus no need for a setter
    public int getLength(){
        return this.length;
    }


//    public static void main(String[] args) {
//        Carrier euCarrier = new Carrier(new int[] {0,0},1);
//        System.out.println(Arrays.toString(euCarrier.allUsedIndices()));
//    }
}
