package lambda;

import java.util.function.Function;

/**
 * @author zhengcheng
 * @date 2017/9/29
 * @time 下午5:57
 * @email chengzhengzheng@meituan.com
 **/

public class ConcurrStream {
    public static void main(String[] args) {
        ConcurrStream concurrStream = new ConcurrStream();
//        System.out.println("Sequential sum done in:" +
//                concurrStream.measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs");


    }

    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
}
