package comport.test_port;


import domain.Load;
import org.json.JSONObject;

import java.util.Arrays;

public class ParseJSON {

    public static void main(String[] args) {


        String numbers = "[[1,7.934],[2,61.98],[3,60.61],[4,12.88],[5,45.41],[6,26.92],[7,63.68],[8,16.89],[9,99.05],[10,53.6],[11,75.86],[12,9.148]]";

        String string = numbers.substring(2, numbers.length() - 2);

        String s = "],\\[";

        String[] array = string.split(s);

        System.out.println(Arrays.deepToString(array));

    }
}
