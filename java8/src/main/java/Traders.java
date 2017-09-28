import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhengcheng
 * @date 2017/9/28
 * @time 下午3:56
 * @email chengzhengzheng@meituan.com
 **/

public class Traders {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //想想sql语句的写法

        //找出2011年的所有交易并按交易额排序(从低到高)
        transactions.stream().filter(a -> a.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue));
        //交易员都在哪些不同的城市工作过
        transactions.stream().map(a -> a.getTrader().getCity()).collect(Collectors.toSet());
        //查找所有来自于剑桥的交易员，并按姓名排序
        transactions.stream().map(a -> a.getTrader()).filter(a -> a.getCity().equals("Cambridge")).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        //返回所有交易员的姓名字符串，按字母顺序排序
        transactions.stream().map(a -> a.getTrader().getName()).distinct().sorted().reduce("", (n1, n2) -> n1 + n2);//todo
        //有没有交易员是在米兰工作的
        transactions.stream().anyMatch(a -> a.getTrader().getCity().equals("Milan"));
        //打印生活在剑桥的交易员的所有交易额
        transactions.stream().filter(a -> a.getTrader().getCity().equals("Cambridge")).map(a -> a.getValue()).forEach(System.out::println);
        //所有交易中，最高的交易额是多少
        Optional<Integer> max = transactions.stream().map(a -> a.getValue()).max(Integer::max);
        //找到交易额最小的交易
        transactions.stream().min(Comparator.comparing(Transaction::getValue));


        //数值流

        int sum = transactions.stream().mapToInt(a -> a.getValue()).sum();
        IntStream intStream = transactions.stream().mapToInt(a -> a.getValue());
        Stream<Integer> boxed = intStream.boxed();


        IntStream intStream1 = transactions.stream().mapToInt(a -> a.getValue());
        OptionalInt max1 = intStream1.max();
        int i = max1.orElse(1);

        //数值范围

        IntStream intStream2 = IntStream.rangeClosed(1, 1000).filter(n -> n % 2 == 0);
//        System.out.println(intStream2.count());


        //数值流应用:勾股数

        //生成勾股数最终方法

        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed().
                flatMap(a -> IntStream.rangeClosed(a, 100).
                        filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).
                        mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );


//        pythagoreanTriples.forEach(aa -> System.out.println(Arrays.toString(aa)));
//        pythagoreanTriples.limit(5)
//                .forEach(t ->
//                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));


        Stream<double[]> stream = IntStream.rangeClosed(1, 100).boxed().
                flatMap(a ->
                        IntStream.rangeClosed(a, 100).
                                mapToObj(b ->
                                        new double[]{a, b, Math.sqrt(a * a + b * b)}).
                                filter(c ->
                                        c[2] % 1 == 0));

        stream.limit(5)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));


        //由值创建流
        Stream<String> java8 = Stream.of("Java8", "test1");
        java8.map(String::toUpperCase).forEach(System.out::println);

        //由数组创建流
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum1 = Arrays.stream(numbers).sum();


        //由文件生成流






    }
}
