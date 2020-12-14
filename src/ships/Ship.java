package ships;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Ship {

    public String name;
    public int [] startIndex;
    public int direction;
    public ArrayList<int []> takenIndices;
//    int[] shot = {0,0};

    public Ship(String name,int [] startIndex, int direction){
        this.name = name;
        this.startIndex = startIndex;
        this.direction = direction;
        this.takenIndices = this.allUsedIndices();
    }

    public String getName(){
        return this.name;
    }

    public void setStartIndex(int[] startIndex) {
        this.startIndex = startIndex;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    abstract public int getLength();

    public boolean checkShot(int[] shot){
        for(int[] indices: this.allUsedIndices()){
            if(Arrays.equals(shot, indices)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<int []> allUsedIndices() {
        ArrayList<int[]> allIndices = new ArrayList<>();
        allIndices.add(this.startIndex);
        if (direction == 0) {
            int rowIndex = this.startIndex[0];
            int columnIndex = this.startIndex[1];
            for (int i = 1; i < this.getLength(); i++) {
                columnIndex++;
                allIndices.add(new int[]{rowIndex, columnIndex});
            }
        } else if (direction == 1) {
            int rowIndex = this.startIndex[0];
            int columnIndex = this.startIndex[1];
            for (int i = 1; i < this.getLength(); i++) {
                rowIndex++;
                allIndices.add(new int[]{rowIndex, columnIndex});
            }
        } else if (direction == 2) {
            int rowIndex = this.startIndex[0];
            int columnIndex = this.startIndex[1];
            for (int i = 1; i < this.getLength(); i++) {
                columnIndex--;
                allIndices.add(new int[]{rowIndex, columnIndex});
            }
        } else if (direction == 3) {
            int rowIndex = this.startIndex[0];
            int columnIndex = this.startIndex[1];
            for (int i = 1; i < this.getLength(); i++) {
                rowIndex--;
                allIndices.add(new int[]{rowIndex, columnIndex});
            }
        }
        return allIndices;
    }

}
