package ships;

public class Carrier extends Ship{

    final int length;

    public Carrier(int [] startIndex, int direction){
        super("Carrier",startIndex,direction);
        this.length = 5;
    }
//    There is no need for a setter, because the length of a ship will never change
    public int getLength(){
        return this.length;
    }

}
