package ships;

public class Submarine extends Ship {

    final int length;

    public Submarine(int [] startIndex, int direction){
        super("Submarine",startIndex,direction);
        this.length = 3;
    }
    //    Length will never change thus no need for a setter
    public int getLength(){
        return this.length;
    }
}
