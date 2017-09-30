package asyc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author zhengcheng
 * @date 2017/9/30
 * @time 下午12:24
 * @email chengzhengzheng@meituan.com
 **/

public class CompletableFutureTest {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");

        doSomethingElse();
        try {
            double price = futurePrice.get(2, TimeUnit.SECONDS);
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");

        System.out.println("======================findPrices===========================");
        long start1 = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Done in " + duration1 + " msecs");


        System.out.println("**********************************");

        long start2 = System.nanoTime();
        System.out.println(findPricesParam("myPhone27S"));
        long duration2 = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("Done in " + duration2 + " msecs");
        System.out.println("======================findPricesParam===========================");


        System.out.println("**********************************");

        long start3 = System.nanoTime();
        System.out.println(findPrices3("myPhone27S"));
        long duration3 = (System.nanoTime() - start3) / 1_000_000;
        System.out.println("Done in " + duration3 + " msecs");
        System.out.println("======================findPrices3===========================");


        System.out.println("**********************************");

        long start4 = System.nanoTime();
        System.out.println(findPrices4("myPhone27S"));
        long duration4 = (System.nanoTime() - start4) / 1_000_000;
        System.out.println("Done in " + duration4 + " msecs");
        System.out.println("======================findPrices4===========================");



    }

    public static List<String> findPrices3(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " +
                                        shop.getPrice(product)))
                        .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join).collect(Collectors.toList());


    }

    public static List<String> findPrices4(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " + shop.getPrice(product), executor
                                )
                        )
                        .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join).collect(Collectors.toList());


    }

    static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("chengzheng"),
            new Shop("chengzheng2"),
            new Shop("chengzheng3"),
            new Shop("chengzheng4"),
            new Shop("chengzheng4"),
            new Shop("chengzheng4"),
            new Shop("chengzheng4"),
            new Shop("chengzheng4")
    );
    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),

            new ThreadFactory() {
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }

            });

    public static List<String> findPrices(String product) {
        return shops.stream().map(
                shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public static List<String> findPricesParam(String product) {
        return shops.stream().parallel().map(
                shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }


    private static void doSomethingElse() {


    }


}
