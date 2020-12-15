package sessionEleven;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Try {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C", "D");
        System.out.println("List before Shuffle : "+list);
        Collections.shuffle(list);
        System.out.println("List after shuffle : "+list);
        System.out.println(list.get(0));

        Random ran = new Random();
        System.out.println(ran.nextInt());
    }



}
