package analysis_runtime;

public class RuntimeOrderOfGrowth {
    public static void main(String[] args) {
//        System.out.println("Duration: quadratic 8k: "+quadratic(8_000)/1000000d+" ms");
//        System.out.println("Duration: quadratic 16k: "+quadratic(16_000L)/1000000d+" ms");
//        System.out.println("Duration: quadratic 32k: "+quadratic(32_000L)/1000000d+" ms");
//        System.out.println("Duration: quadratic 64k: "+quadratic(64_000L)/1000000d+" ms");
//        System.out.println("Duration: quadratic 128k: "+quadratic(128_000L)/1000000d+" ms");

        long base = 8000;
        int k = 5;
        long[] res = new long[k];
        for (int i = 0; i < k; i++) {
            long input = (long) Math.pow(2, i) * base;
            res[i] = quadratic(input);
            System.out.println("Added time(ms):" + res[i] / 1000000d);
        }
        double ratio = calcRatio(res);
        ratio = doublingRatio(ratio);
        System.out.println("Power law order of growth:" + ratio);
    }

    private static double doublingRatio(double r) {
        return Math.log(r) / Math.log(2);
    }

    private static double calcRatio(long[] res) {
        double sum = 0;
        for (int i = 1; i < res.length; i++) {
            long r = res[i] / res[i - 1];
            sum += r;
            System.out.println("ratio found:" + doublingRatio(r));
        }
        return sum / (res.length - 1);
    }

    private static long quadratic(long N) {
        long startTime = System.nanoTime();
        long sum = 0;
        for (long i = 0; i < N; i++) {
            for (long j = 0; j < N; j++) {
                sum += i + j;
            }
        }
        long endTime = System.nanoTime();
        return (endTime - startTime);  //divide by 1000000 to get milliseconds.
    }
}
