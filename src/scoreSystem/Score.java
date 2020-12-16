package scoreSystem;

import ships.Ship;

import java.util.ArrayList;

public class Score {

    public int currentScore;
    public int scoreCarrier;
    public int scoreBattleship;
    public int scoreSubmarine;
    public int scoreDestroyer;

//  The score used for the 'Equal score system'
    public Score(){
           this.currentScore = 0;
           this.scoreCarrier = 10;
           this.scoreBattleship = 15;
           this.scoreSubmarine = 20;
           this.scoreDestroyer = 25;
    }

//  Overload the constructor when the 'Adjusted score system' is used
    public Score(int scoreCarrier, int scoreBattleship, int scoreSubmarine, int scoreDestroyer){
        this.currentScore = 0;
        this.scoreCarrier = scoreCarrier;
        this.scoreBattleship = scoreBattleship;
        this.scoreSubmarine = scoreSubmarine;
        this.scoreDestroyer = scoreDestroyer;
    }

//  Tried shot are the coördinates of the tile that was clicked and shipsOnBoard specifies the ships on the board and their placement.
//  There is checked if the ship is being hit and the score is updated accordingly.
//  A distinction is made if this shot made the ship sink or not.
    public String updateScore(int[] triedShot, ArrayList<Ship> shipsOnBoard) {
        for (Ship testShip : shipsOnBoard) {
            if (testShip.checkShot(triedShot)) {
                String nameShip = testShip.getName();
                switch (nameShip) {
                    case "Carrier" -> {
                        if (testShip.isSunk()){
                            this.currentScore += 2*this.scoreCarrier;
                        }else {
                            this.currentScore += this.scoreCarrier;
                        }
                        return "Carrier";
                    }
                    case "Battleship" -> {
                        if (testShip.isSunk()){
                            this.currentScore += 2*this.scoreBattleship;
                        }else {
                            this.currentScore += this.scoreBattleship;
                        }
                        return "Battleship";
                    }
                    case "Submarine" -> {
                        if (testShip.isSunk()){
                            this.currentScore += 2*this.scoreSubmarine;
                        }else {
                            this.currentScore += this.scoreSubmarine;
                        }
                        return "Submarine";
                    }
                    case "Destroyer" -> {
                        if (testShip.isSunk()){
                            this.currentScore += 2*this.scoreDestroyer;
                        }else {
                            this.currentScore += this.scoreDestroyer;
                        }
                        return "Destroyer";
                    }
                    default -> throw new IllegalArgumentException("Unrecognizable ship on board");
                }
            }

        }
        return "";
    }
}
