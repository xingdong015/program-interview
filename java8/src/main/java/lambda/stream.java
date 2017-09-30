package lambda;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

/**
 * @author zhengcheng
 * @date 2017/9/27
 * @time 下午4:38
 **/

public class stream {
    public static void main(String[] args) {
        List<Apple> appleList = Lists.newArrayList();
        appleList.parallelStream().filter(a -> a.getWeight() > 100).
                sorted(Comparator.comparing(Apple::getAge)).map(Apple::getAge);

    }

}


class Apple {
    int age;
    int weight;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
