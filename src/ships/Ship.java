package ships;

import java.util.ArrayList;
import java.util.Arrays;

// An abstract class 'ship' is build to implement the functionality that all the specific ships have in common
public abstract class Ship {

    public String name;
    public int [] startIndex; // The coördinates of the the start of the ship
    public int direction; // Indictes if the ship points up, down, right or left
    public ArrayList<int []> takenIndices; // In taken indices all the coördinates of the tiles that are taken by the ship are stored
    public int damage;

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

    abstract public int getLength(); // The length differs for every specific ship

    public boolean checkShot(int[] shot){
        for(int[] indices: this.allUsedIndices()){
            if(Arrays.equals(shot, indices)){
                this.damage++;
                return true;
            }
        }
        return false;
    }

    public boolean isSunk(){
        if(this.damage == this.getLength()){
            return true;
        }else if (this.damage < this.getLength()){
            return false;
        }else{
            throw new IllegalArgumentException("Damage can't be bigger than the size of the ship!");
        }

    }

    // Derive from the direction and the start coördinates all the coördinates that are occupied by a ship
    public ArrayList<int []> allUsedIndices() {
        ArrayList<int[]> allIndices = new ArrayList<>();
        allIndices.add(this.startIndex);
        if (direction == 0) {
            int rowIndex = this.startIndex[0];
            int columnIndex = this.startIndex[1];
            for (int i = 1; i < this.getLength(); i++) {
                rowIndex++;
                allIndices.add(new int[]{rowIndex, columnIndex});
            }
        } else if (direction == 1) {
            int rowIndex = this.startIndex[0];
            int columnIndex = this.startIndex[1];
            for (int i = 1; i < this.getLength(); i++) {
                columnIndex++;
                allIndices.add(new int[]{rowIndex, columnIndex});
            }
        } else if (direction == 2) {
            int rowIndex = this.startIndex[0];
            int columnIndex = this.startIndex[1];
            for (int i = 1; i < this.getLength(); i++) {
                rowIndex--;
                allIndices.add(new int[]{rowIndex, columnIndex});
            }
        } else if (direction == 3) {
            int rowIndex = this.startIndex[0];
            int columnIndex = this.startIndex[1];
            for (int i = 1; i < this.getLength(); i++) {
                columnIndex--;
                allIndices.add(new int[]{rowIndex, columnIndex});
            }
        }else{
            throw new IllegalArgumentException("Illegal direction used!");
        }
        return allIndices;
    }
}
