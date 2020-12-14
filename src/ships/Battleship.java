package ships;

public class Battleship extends Ship {

    final int length;

    public Battleship(int [] startIndex, int direction){
        super("Battleship",startIndex,direction);
        this.length = 4;
    }
    //    Length will never change thus no need for a setter
    public int getLength(){
        return this.length;
    }

}
