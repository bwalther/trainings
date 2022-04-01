package numbers_and_arithmetics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxicabNumbers {
    public static void main(String[] args) {
        int n = 1000;
        printNumbers(n);
    }

    public static void printNumbers(int n) {
        //a^3 + b^3 = c^3 + d^3
        Map<Long, List<String>> map = new HashMap<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Long res = (long) (Math.pow(i, 3) + Math.pow(j, 3));
                List<String> val = map.getOrDefault(res, new ArrayList<>());
                val.add(i + ", " + j);
                map.put(res, val);
            }
        }

        long min = Long.MAX_VALUE;
        for (Long key : map.keySet()) {
            List<String> values = map.get(key);
            if (values.size() > 1) {
                if (key < min) min = key;
                System.out.println(key + ": " + values);
            }
        }
        System.out.println("Min:" + min + ": " + map.get(min));

    }

}
