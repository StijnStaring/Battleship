package startSituation;

import ships.*;

import java.util.Arrays;
import java.util.List;
import java.util.*;

public class RandomStart {

    int amountRows;
    int amountColumns;
    public ArrayList<Ship> shipsOnBoard = new ArrayList<>();

    public RandomStart(int amountRows, int amountColumns){
            this.amountRows = amountRows;
            this.amountColumns = amountColumns;

        List<String> possibleShips = Arrays.asList("Carrier", "Battleship", "Submarine", "Destroyer");
        Collections.shuffle(possibleShips);

        for(String ship: possibleShips){
            int[] randomStartIndex = {new Random().nextInt(amountRows), new Random().nextInt(amountColumns)};
            int randomDirection = new Random().nextInt(4);
            switch (ship) {
                case "Carrier" -> {
                    List<Object> checkedPlaceBoard = generateLegitimatePlace(new Carrier(randomStartIndex, randomDirection), shipsOnBoard);
                    shipsOnBoard.add(new Carrier((int[]) checkedPlaceBoard.get(0), (int) checkedPlaceBoard.get(1)));

                }
                case "Battleship" -> {
                    List<Object> checkedPlaceBoard = generateLegitimatePlace(new Battleship(randomStartIndex, randomDirection), shipsOnBoard);
                    shipsOnBoard.add(new Battleship((int[]) checkedPlaceBoard.get(0), (int) checkedPlaceBoard.get(1)));

                }
                case "Submarine" -> {
                    List<Object> checkedPlaceBoard = generateLegitimatePlace(new Submarine(randomStartIndex, randomDirection), shipsOnBoard);
                    shipsOnBoard.add(new Submarine((int[]) checkedPlaceBoard.get(0), (int) checkedPlaceBoard.get(1)));

                }
                case "Destroyer" -> {
                    List<Object> checkedPlaceBoard = generateLegitimatePlace(new Destroyer(randomStartIndex, randomDirection), shipsOnBoard);
                    shipsOnBoard.add(new Destroyer((int[]) checkedPlaceBoard.get(0), (int) checkedPlaceBoard.get(1)));

                }
            }

        }

    }

    public static boolean noOverlap(Ship newShip,ArrayList<Ship> shipsOnBoard){
        for(Ship testShip: shipsOnBoard) {
            for(int[] indices: newShip.allUsedIndices()) {
                for(int[] indicesTestShip: testShip.allUsedIndices()) {
                    if (Arrays.equals(indices, indicesTestShip)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean inBoard(Ship newShip,int amountRows, int amountColumns){
        for(int[] indices: newShip.allUsedIndices()){

            if(indices[0] >= amountRows || indices[0] >= amountColumns || indices[1] >= amountRows || indices[1] >= amountColumns || indices[0] < 0 || indices[1] < 0){
                return false;
            }

        }
        return true;
    }

    public List<Object> generateLegitimatePlace(Ship newShip, ArrayList<Ship> shipsOnBoard) {
        if (inBoard(newShip,this.amountRows,this.amountColumns) && noOverlap(newShip, shipsOnBoard)) {
            return Arrays.asList(newShip.startIndex, newShip.direction);
        } else {
            while (true) {
                int[] randomStartIndex = {new Random().nextInt(amountRows), new Random().nextInt(amountColumns)};
                int randomDirection = new Random().nextInt(4);
                newShip.setStartIndex(randomStartIndex);
                newShip.setDirection(randomDirection);
                if (inBoard(newShip,this.amountRows,this.amountColumns) && noOverlap(newShip, shipsOnBoard)) {
                    return Arrays.asList(newShip.startIndex, newShip.direction);
                }
            }
        }
    }
}

