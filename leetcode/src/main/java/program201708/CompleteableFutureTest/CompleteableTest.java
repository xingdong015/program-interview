package program201708.CompleteableFutureTest;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author zhengcheng
 * @date 2017/8/17
 * @time 下午2:47
 **/

public class CompleteableTest {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });

    }
}
