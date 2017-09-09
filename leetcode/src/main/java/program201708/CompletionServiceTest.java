package program201708;


import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author zhengcheng
 * @date 2017/8/17
 * @time 上午11:22
 **/

public class CompletionServiceTest {
    public static void main(String[] args) {
        CompletableFuture future = new CompletableFuture();
        future.supplyAsync(new Supplier<Object>() {
            @Override
            public Object get() {
                return null;
            }
        }).thenAccept(new Consumer<Object>() {
            @Override
            public void accept(Object o) {

            }
        });
    }
}
