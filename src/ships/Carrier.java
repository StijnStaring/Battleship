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
//        Carrier euCarrier = new Carrier(new int[] {10,8},0);
////        System.out.println(Arrays.toString());
//        ArrayList<int []> allEUCarrier = euCarrier.allUsedIndices();
//        for(int i = 0;i<euCarrier.length;i++){
//            System.out.println(Arrays.toString(allEUCarrier.get(i)));
//        }
//        System.out.println(euCarrier.damage);
//        int[] tryShot = {12,8};
//        euCarrier.checkShot(tryShot);
//        System.out.println(euCarrier.damage);
//        System.out.println(euCarrier.isSunk());
//    }
}
