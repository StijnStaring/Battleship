package ships;

public class Destroyer extends Ship{

    final int length;

    public Destroyer(int [] startIndex, int direction){
        super("Destroyer",startIndex,direction);
        this.length = 2;
    }
    //    Length will never change thus no need for a setter
    public int getLength(){
        return this.length;
    }
}
