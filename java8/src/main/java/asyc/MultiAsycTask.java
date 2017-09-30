package asyc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author zhengcheng
 * @date 2017/9/30
 * @time 下午4:22
 * @email chengzhengzheng@meituan.com
 **/

public class MultiAsycTask {

    static List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("chengzheng")
    );

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),

            new ThreadFactory() {
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }

            });

    public static void main(String[] args) {

        System.out.println("**********************************");

        long start4 = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        long duration4 = (System.nanoTime() - start4) / 1_000_000;
        System.out.println("Done in " + duration4 + " msecs");
        System.out.println("======================findPrices4===========================");


        System.out.println("**********************************");

        long start = System.nanoTime();
        System.out.println(findPriceAsyn("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
        System.out.println("======================findPrices4===========================");
    }


    //将两个CompletableFuture对象整合起来，无论它们是否存在依赖
    public static void combine(String product) {
        ExchangeService exchangeService = new ExchangeService();
        Stream<CompletableFuture<Integer>> completableFutureStream = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product)).thenCombine(CompletableFuture.supplyAsync(() ->
                exchangeService.getRate(Money.EUR, Money.USD)), (price, rate) -> Integer.parseInt(price) * (Integer) rate)
        );
//        ExecutorService executor = Executors.newCachedThreadPool();
//        final Future<Double> futureRate = executor.submit(new Callable<Double>() {
//            public Double call() {
//                double priceInEUR = shop.getPrice(product);
//                return priceInEUR * futureRate.get();
//            }});
//
//            ExecutorService executor = Executors.newCachedThreadPool();
//            final Future<Double> futureRate = executor.submit(new Callable<Double>() {
//                public Double call() {
//                    double priceInEUR = shop.getPrice(product);
//                    return priceInEUR * futureRate.get();
//                }});

    }


    public static List<String> findPrices(String product) {
        return shops.stream().map(shop -> shop.getPrice(product)).map(Quote::parse).
                map(Discount::applyDiscount).collect(toList());
    }

    //构造同步和异步操作
    public static List<String> findPriceAsyn(String product) {
        List<CompletableFuture<String>> collect = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor)).
                map(future -> future.thenApply(Quote::parse)).
                map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor))).collect(toList());
        return collect.stream().map(CompletableFuture::join).collect(toList());


    }
}
