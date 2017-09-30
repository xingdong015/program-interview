package lambda; /**
 * @author zhengcheng
 * @date 2017/9/28
 * @time 下午4:13
 * @email chengzhengzheng@meituan.com
 **/

import my.CaloricLevel;
import my.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class DataStream {
    public static void main(String[] args) {
        // 规约操作

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

        menu.stream().count();
        menu.stream().collect(counting());
        menu.stream().collect(Collectors.toList());


        menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getName)));


        int collect = menu.stream().collect(Collectors.summingInt(Dish::getCalories));

        Double collect1 = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));

        IntSummaryStatistics collect2 = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

        System.out.println("============");

        System.out.println(collect2);//IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}

        //链接字符串

        String collect3 = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(collect3);//porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon

        String collect4 = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(collect4);


        //广义的归约汇总

        Integer collect5 = menu.stream().map(Dish::getCalories).collect(Collectors.reducing(0, (x, y) -> (x + y)));

        menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (x, y) -> x + y));
        Integer collect6 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        Integer collect8 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));


        Optional<Dish> collect7 = menu.stream().collect(Collectors.reducing((x, y) -> x.getCalories() > y.getCalories() ? x : y));


        //收集与归约

        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        //语义问题在于，reduce方法
        //旨在把两个值结合起来生成一个新值，它是一个不可变的归约。与此相反，collect方法的设 计就是要改变容器，从而累积要输出的结果

        menu.stream().collect(Collectors.counting());

        Integer integer = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();

        int sum = menu.stream().mapToInt(Dish::getCalories).sum();

        Map<Dish.Type, List<Dish>> collect9 = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(collect9);


        Map<CaloricLevel, List<Dish>> collect11 = menu.stream().collect(Collectors.groupingBy(Dish::getDishCaloricLevelFunction));
        System.out.println("collect11:" + collect11);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect10 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(Dish::getDishCaloricLevelFunction)));

        System.out.println("collect10:" + collect10);

        //类似sql语句中的group by ---having count
        Map<Dish.Type, Long> collect12 = menu.stream().collect(groupingBy(Dish::getType, Collectors.counting()));
        System.out.println("collect12---->" + collect12);

        Map<Dish.Type, Optional<Dish>> collect13 = menu.stream().collect(groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparing(Dish::getCalories))));
        System.out.println("collect13---->" + collect13);


        //，这个操作(Optional::get)放在这里是安全的，因为reducing 收集器永远都不会返回Optional.empty()。其结果是下面的Map:
        Map<Dish.Type, Dish> collect14 = menu.stream().
                collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println("collect14----->" + collect14);

        menu.stream().collect(groupingBy(Dish::getType, Collectors.summarizingInt(Dish::getCalories)));


        Map<Dish.Type, Set<CaloricLevel>> collect15 = menu.stream().collect(groupingBy(Dish::getType, mapping(
                Dish::getDishCaloricLevelFunction, toSet())
        ));
        System.out.println("collect15----->" + collect15);

        Map<Dish.Type, Set<CaloricLevel>> collect16 = menu.stream().collect(groupingBy(Dish::getType, mapping(
                Dish::getDishCaloricLevelFunction, toCollection(HashSet::new))
        ));


        //分区
        Map<Boolean, List<Dish>> collect17 = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        List<Dish> collect18 = menu.stream().filter(Dish::isVegetarian).collect(toList());

        Map<Boolean, Map<Dish.Type, List<Dish>>> collect19 = menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println("collect19----->" + collect19);


        Map<Boolean, Dish> collect20 = menu.stream().collect(partitioningBy(
                Dish::isVegetarian, collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)
                )
        );
        System.out.println("collect20----->" + collect20);

        Map<Boolean, List<Integer>> primeJudge = isPrimeJudge(50);
        System.out.println("primeJudge----->" + primeJudge);
        menu.stream().collect(toList());

    }

    private static Map<Boolean, List<Integer>> isPrimeJudge(int n) {
        return IntStream.range(2, n).boxed().collect(partitioningBy(i -> isPrime(i)));
    }

    public static boolean isPrime(int n) {
        int sqrt = (int) Math.sqrt((double) n);
        return IntStream.rangeClosed(2, sqrt).noneMatch(i -> n % i == 0);
    }


}



