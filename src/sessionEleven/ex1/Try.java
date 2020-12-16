package sessionEleven.ex1;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Arrays;
import java.util.Collections;

public class Try {


    public static  void main(String[] args) {

//        JFileChooser chooseFile = new JFileChooser();
//        FileNameExtensionFilter filterOptions = new FileNameExtensionFilter("Text files", "txt");
//        chooseFile.setFileFilter(filterOptions);
//        int returnVal = chooseFile.showOpenDialog(frame);
//        if(returnVal == JFileChooser.APPROVE_OPTION) {
//            System.out.println("You chose to open this file: " + chooseFile.getSelectedFile().getAbsolutePath());
//        }



        Integer[] array = {1,2,23,4};
        Arrays.sort(array, Collections.reverseOrder());
        System.out.println(Arrays.toString(array));
    }
}
