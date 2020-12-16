package highScore;

import scoreSystem.Score;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class HighScore {

    public ArrayList<int[]> highScoresCollection;
    int people = 5;

    //    Read in the file when constructed.
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

            if(highScoresCollection.size() > people){
                throw new ArrayStoreException();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }catch (ArrayStoreException ae){
            JOptionPane.showMessageDialog(null, "The highScore initially stores already more scores then allowed." + ae);

        }

    }

    //    Update the highScore --> in effect when a game is played. Assesses the score of the winner of the game.
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
//            tempScores.size();
            for (int i = 1; i <= people; i++) {
                int[] placeScore = {i, tempScores[i - 1]};
                highScoresCollection.add(placeScore);
            }
        }
    }

    public String convertToString(){
        String s = "Place" + "            " + "Score" + "\n";
        for (int[] value: highScoresCollection){
            s = new String(s + String.valueOf(value[0]) + "                     " + String.valueOf(value[1]) + "\n");
        }
        return s;


    }

//    Write highScore the the hard drive.
    public void saveHighscore(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src//highScore//highScore.txt",false))) {
            for (int [] value: highScoresCollection){
                bw.write(String.valueOf(value[0]) + ";" + String.valueOf(value[1]) + "\n");
            }
        } catch(FileNotFoundException fnf) {
            System.out.println("File not found when writing: " + fnf);
        } catch(IOException io) {
            System.out.println("An I/O writing error occurred: " + io);
        }
    }

//    public static void main(String[] args) {
//        HighScore hs = new HighScore();
//        for (int [] value: hs.highScoresCollection){
//            System.out.println(Arrays.toString(value));
//        }
//
////        JTextArea textArea = new JTextArea(10, 10);
//        String highScoreString = hs.convertToString();
////        textArea.setText(highScoreString);
////        textArea.setEditable(false);
//
////        JOptionPane.showMessageDialog(null,highScoreString);
//        JOptionPane.showMessageDialog(null,highScoreString,"High Score", JOptionPane.INFORMATION_MESSAGE);
//        hs.saveHighscore();
////
////        JScrollPane scrollPane = new JScrollPane(textArea);
////        JOptionPane.showMessageDialog(null,scrollPane);
//
//    }




}
