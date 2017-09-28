import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author zhengcheng
 * @date 2017/9/27
 * @time 下午6:05
 **/

public class stream2 {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));


        menu.stream().filter(Dish::isVegetarian).collect(toList());

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
//        numbers.stream().filter(a -> a % 2 == 0).distinct().forEach(System.out::println);

        Stream<String> stringStream = menu.stream().map(Dish::getName);
        List<String> words = Arrays.asList("hello", "World");
//        words.stream().map(String::length).forEach(System.out::println);

        //流的扁平化
        words.stream().map(word -> word.split("")).forEach(a -> System.out.println(Arrays.toString(a)));

        Stream<String> strStreams = Arrays.stream(new String[]{});

        words.stream().map(word -> word.split("")).map(Arrays::stream).collect(toList());

        //使用flatMap   一言以蔽之，flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接 起来成为一个流
        words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(toList()).forEach(System.out::println);
        //给定两个数字列表，如何返回所有的数对呢?例如，给定列表[1, 2, 3]和列表[3, 4]，应 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代 表数对。

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(toList()).forEach(a -> System.out.println(Arrays.toString(a)));
        System.out.println();
        numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(toList()).forEach(a -> System.out.println(Arrays.toString(a)));

        //查找和匹配
        menu.stream().anyMatch(Dish::isVegetarian);//终端操作
        menu.stream().allMatch(Dish::isVegetarian);//
        menu.stream().noneMatch(Dish::isVegetarian);
        //查找元素
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));


    }

    public void test() {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> so = someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
        System.out.println(so.get());


        //归约

        int sum = someNumbers.stream().reduce(0, (a, b) -> a + b);
        someNumbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        someNumbers.stream().reduce(Integer::sum);
        Optional<Integer> reduce = someNumbers.stream().reduce(Integer::max);

        //计算元素的个数
        Optional<Integer> reduce1 = someNumbers.stream().map(a -> 1).reduce(Integer::sum);
        long count = someNumbers.stream().count();
        System.out.println(reduce1.get());

        someNumbers.parallelStream().map(d -> 1).reduce(Integer::sum);



    }
}
