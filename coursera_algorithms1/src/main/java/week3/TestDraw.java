package week3;

import edu.princeton.cs.algs4.CollisionSystem;
import edu.princeton.cs.algs4.StdDraw;

public class TestDraw {

    public static void testBasic() {
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(0.5, 0.5);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.line(0.2, 0.2, 0.8, 0.2);
    }


    public static void main(String[] args) {
        CollisionSystem.main(new String[]{"7"});
    }


}
