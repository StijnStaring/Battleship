package startSituation;

import ships.*;

import javax.naming.NamingException;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DefinedStart {
    public ArrayList<Ship> shipsOnBoard;
    public int amountRows;
    public int amountColumns;
    public boolean feasible = true;
    public boolean wrongLength = false;

    public DefinedStart(String path){

        shipsOnBoard = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {

            //read the board size
            String line = br.readLine();
            StringTokenizer tokenizerOld = new StringTokenizer(line);
            int amountRows = Integer.parseInt(tokenizerOld.nextToken(";").trim());
            int amountColumns = Integer.parseInt(tokenizerOld.nextToken(";").trim());
            this.amountRows = amountRows;
            this.amountColumns = amountColumns;

            //start reading in the ships
            line = br.readLine();
            while (line != null){
                ArrayList<int[]> allIndices = new ArrayList<>();
                int [] startIndex= {0,0};
                int direction;
                StringTokenizer tokenizer = new StringTokenizer(line,";");
                int countTokens = tokenizer.countTokens();
                String shipName = tokenizer.nextToken().trim();
                for (int i = 0; i<countTokens-1;i++) {
                    String candidate = tokenizer.nextToken().trim();
                    int rowIndex;
                    int columnIndex;
                    try {
                        rowIndex = Integer.parseInt(candidate.substring(0, 1));
                        columnIndex = Integer.parseInt(candidate.substring(2, 3));
                    }catch (StringIndexOutOfBoundsException sioobe){
                        JOptionPane.showMessageDialog(null, "You have provided infeasible coördinates!\nRandom placement will apply.");
                        this.feasible = false;
                        break;
                    }
                    int[] indices = {rowIndex, columnIndex};
                    allIndices.add(indices);
                    if(i==0){
                        startIndex = indices;
                    }else if(i == 1){
                        direction = getDirection(startIndex,indices);
                        switch(shipName){
                            case "Carrier", "carrier" -> shipsOnBoard.add(new Carrier(startIndex,direction));
                            case "Battleship","battleship" -> shipsOnBoard.add(new Battleship(startIndex,direction));
                            case "Submarine","submarine" -> shipsOnBoard.add(new Submarine(startIndex,direction));
                            case "Destroyer","destroyer" -> shipsOnBoard.add(new Destroyer(startIndex,direction));
                            default -> throw new NamingException();
                        }
                    }
                }

                if (shipsOnBoard.get(shipsOnBoard.size()-1).getLength() != allIndices.size()){
                    this.wrongLength = true;
                }
                line = br.readLine();
            }
            ArrayList<Ship> shipsOnBoardTest = new ArrayList<>();
            for (int i= 0; i<4;i++){
                shipsOnBoardTest.add(shipsOnBoard.get(i));
            }
            for(Ship testShip: shipsOnBoard) {
                shipsOnBoardTest.remove(testShip);
//                for (Ship ship2:shipsOnBoardTest){
//                    System.out.println(ship2.getName());
//                }
                if (!RandomStart.noOverlap(testShip, shipsOnBoardTest)) {
                    JOptionPane.showMessageDialog(null, "You have provided ships that have overlap!\nRandom placement will apply.");
                    this.feasible = false;
                    break;
                }
                if (!RandomStart.inBoard(testShip, this.amountRows, this.amountColumns)) {
                    JOptionPane.showMessageDialog(null, "You have provided ships that lay outside the board!\nRandom placement will apply.");
                    this.feasible = false;
                    break;
//                    throw new IllegalArgumentException("The ships are not all inside the board.\nPleas provide a feasible setup of the ships.");
                }
            }

            if(this.wrongLength){
                throw new ArrayStoreException();
            }
        } catch(FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null, "File was not found!\nRandom placement will apply.");
            this.feasible = false;
//            System.out.println("File not found when reading: " + fnf);
        } catch(IOException io) {
            JOptionPane.showMessageDialog(null, "Input/Output Exception!\nRandom placement will apply.");
            this.feasible = false;
//            System.out.println("An I/O reading error occurred: " + io);
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(null, "The coördinates provided are wrong!\nRandom placement will apply.");
            this.feasible = false;
//            e.printStackTrace();
        } catch (NamingException ne) {
            JOptionPane.showMessageDialog(null, "The name of the ship is wrong!\nRandom placement will apply.");
            this.feasible = false;
        } catch (ArrayStoreException ase){
            JOptionPane.showMessageDialog(null, "The length of the ships is wrong!\nShips will be placed based on their first two coördinates");
        }
    }

    public static int getDirection(int[] firstIndex, int[] secondIndex) throws IllegalArgumentException{

        if(firstIndex[0] < secondIndex[0] && firstIndex[1] == secondIndex[1]){
            return 0;
        }
        else if(firstIndex[0] > secondIndex[0] && firstIndex[1] == secondIndex[1]){
            return 2;
        }else if(firstIndex[0] == secondIndex[0] && firstIndex[1] < secondIndex[1]){
            return 1;
        }else if(firstIndex[0] == secondIndex[0] && firstIndex[1] > secondIndex[1]){
            return 3;
        }else{
            throw new IllegalArgumentException();
        }

    }

}


