package highScore;

import scoreSystem.Score;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class HighScore {

    public ArrayList<int[]> highScoresCollection; // An ArrayList of all the high scores
    int people = 5; // The maximum of scores that are included in the high score

    //    Read the file from the hard drive.
    public HighScore(){
        highScoresCollection = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("src//highScore//highScore.txt"))) {
            String line = br.readLine();
            while (line != null){
                StringTokenizer tokenizer = new StringTokenizer(line,";");
                int place = Integer.parseInt(tokenizer.nextToken().trim());
                int score = Integer.parseInt(tokenizer.nextToken().trim());
                int [] placeScore = {place,score};
                highScoresCollection.add(placeScore);
                line = br.readLine();
            }
    //    The file that is read from the hard drive can not already include more scores than are allowed in the high score
            if(highScoresCollection.size() > people){
                throw new ArrayStoreException();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }catch (ArrayStoreException ae){
            JOptionPane.showMessageDialog(null, "The highScore initially stores already more scores then allowed." + ae);
        }
    }

    //    Update the highScore when a game has finished.
    //    Assess only the score of the winner of the game and decide if it is high enough to be included in the high score
    public void updateHighScore(Score player){
        int lengthOriginal = highScoresCollection.size();
        Integer [] tempScores = new Integer[lengthOriginal+1];
        for(int i = 0; i<lengthOriginal;i++){
            tempScores[i] = highScoresCollection.get(i)[1];
        }
        tempScores[lengthOriginal] = player.currentScore;
        Arrays.sort(tempScores, Collections.reverseOrder());
        highScoresCollection = new ArrayList<>();

        if(lengthOriginal+1 < people) {
            for (int i = 1; i <= lengthOriginal + 1; i++) {
                int[] placeScore = {i, tempScores[i - 1]};
                highScoresCollection.add(placeScore);
            }
        }else{
            for (int i = 1; i <= people; i++) {
                int[] placeScore = {i, tempScores[i - 1]};
                highScoresCollection.add(placeScore);
            }
        }
    }
    //    Convert the high scores stored in an ArrayList to a string to show in a popup panel
    public String convertToString(){
        StringBuilder s = new StringBuilder("Place" + "            " + "Score" + "\n");
        for (int[] value: highScoresCollection){
            s.append(value[0]).append("                     ").append(value[1]).append("\n");
        }
        return s.toString();
    }

//    Safe the high score to the hard drive to be read again when the game is rebooted
    public void saveHighscore(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src//highScore//highScore.txt",false))) {
            for (int [] value: highScoresCollection){
                bw.write(value[0] + ";" + value[1] + "\n");
            }
        } catch(FileNotFoundException fnf) {
            System.out.println("File not found when writing: " + fnf);
        } catch(IOException io) {
            System.out.println("An I/O writing error occurred: " + io);
        }
    }
}
