package my;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author zhengcheng
 * @date 2017/9/29
 * @time 下午5:15
 * @email chengzhengzheng@meituan.com
 **/

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

        List<Dish> collect = menu.stream().collect(new ToListCollector<>());
        System.out.println(collect);


        //进行自定义收集而不去实现Collector
        List<Dish> dish = menu.stream().collect(ArrayList::new,//供应源
                List::add,//累加器
                List::addAll);//组合器




    }

    @Override
    public Supplier<List<T>> supplier() {
        //创建集合操 作的起始点
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        //累积遍历过的 项目，原位修改 累加器
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        //修改第一个累加 器，将其与第二个 累加器的内容合并
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    }


}
