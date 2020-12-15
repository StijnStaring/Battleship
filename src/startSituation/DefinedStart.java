package startSituation;

import ships.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DefinedStart {
    public ArrayList<Ship> shipsOnBoard;

    public DefinedStart(String path){

        shipsOnBoard = new ArrayList<>();
//        JFileChooser chooseFile = new JFileChooser();
//        FileNameExtensionFilter filterOptions = new FileNameExtensionFilter("Text files", "txt");
//        chooseFile.setFileFilter(filterOptions);
//        int returnVal = chooseFile.showOpenDialog(null);
//        if(returnVal == JFileChooser.APPROVE_OPTION) {
//            System.out.println("You chose to open this file: " + chooseFile.getSelectedFile().getAbsolutePath());
//        }
//        chooseFile.getSelectedFile().getAbsolutePath()

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {

            //read the board size
            String line = br.readLine();
            StringTokenizer tokenizerOld = new StringTokenizer(line);
            int amountRows = Integer.parseInt(tokenizerOld.nextToken(";").trim());
            int amountColumns = Integer.parseInt(tokenizerOld.nextToken(";").trim());
            System.out.println(amountRows);
            System.out.println(amountColumns);

            //start reading in the ships
            line = br.readLine();
            while (line != null){
                int [] startIndex= {0,0};
                int direction;
                StringTokenizer tokenizer = new StringTokenizer(line,";");
                int countTokens = tokenizer.countTokens();
                String shipName = tokenizer.nextToken().trim();
                for (int i = 0; i<countTokens-1;i++) {
                    String candidate = tokenizer.nextToken().trim();
                    int rowIndex = Integer.parseInt(candidate.substring(0,1));
                    int columnIndex = Integer.parseInt(candidate.substring(2,3));
                    int[] indices = {rowIndex, columnIndex};
                    if(i==0){
                        startIndex = indices;
                    }else if(i == 1){
                        direction = getDirection(startIndex,indices);
                        switch(shipName){
                            case "Carrier" -> shipsOnBoard.add(new Carrier(startIndex,direction));
                            case "Battleship" -> shipsOnBoard.add(new Battleship(startIndex,direction));
                            case "Submarine" -> shipsOnBoard.add(new Submarine(startIndex,direction));
                            case "Destroyer" -> shipsOnBoard.add(new Destroyer(startIndex,direction));
                        }
                        break;
                    }
                }

                line = br.readLine();
            }
        } catch(FileNotFoundException fnf) {
            System.out.println("File not found when reading: " + fnf);
        } catch(IOException io) {
            System.out.println("An I/O reading error occurred: " + io);
        }

        for(int i =0; i<4;i++){
            System.out.println(shipsOnBoard.get(i).getName());
        }
    }

    public static int getDirection(int[] firstIndex, int[] secondIndex) {

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
            throw new IllegalArgumentException("Unrecognizable ship on board");
        }

    }

}


