import java.util.Arrays;

public class Utils {

    public static int[] parseToIntArray(String s) {
        return Arrays.stream(s.trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
    }
}
