package interviews;

import java.util.*;


public class SolutionSep21 {
    public static void main(String[] args) {
//        String input = "1 2 3 4 5";
//        Arrays.asList(input.split("\\s+")).forEach(o -> System.out.println(Integer.parseInt(o) * Integer.parseInt(o)));
        Scanner sc = new Scanner(System.in);
        sc.forEachRemaining(o -> {
            if (Integer.parseInt(o) <= 5) System.out.println(Integer.parseInt(o) * Integer.parseInt(o));
            else try {
                throw new RuntimeException();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//        r.run();
    }
}
