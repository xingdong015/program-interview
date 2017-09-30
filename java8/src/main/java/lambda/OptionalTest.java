package lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * @author zhengcheng
 * @date 2017/9/30
 * @time 上午10:52
 * @email chengzhengzheng@meituan.com
 **/

public class OptionalTest {
    public static void main(String[] args) {
        Insurance insurance = new Insurance();
        Optional<Insurance> insurance1 = Optional.ofNullable(insurance);
        Optional<String> name = insurance1.map(Insurance::getName);


        ///
        Person person = new Person();
        Optional<Person> optPerson = Optional.of(person);
        Optional<Optional<Car>> car = optPerson.map(Person::getCar);


        Optional<Car> car1 = optPerson.flatMap(Person::getCar);
        Optional<Insurance> insurance2 = car1.flatMap(Car::getInsurance);
        Optional<String> s = insurance2.map(Insurance::getName);


        String instName = Optional.of(person).
                flatMap(Person::getCar).flatMap(Car::getInsurance).
                map(Insurance::getName).orElse("unKnown");


        //filter 引用
        Insurance insurance3 = new Insurance();
        if (insurance3 != null && "CambridgeInsurance".equals(insurance3.getName()))
            System.out.println("ok");

        Optional<Insurance> insurance4 = Optional.ofNullable(insurance3);
        Optional<Insurance> insurance6 = insurance4.filter(insurance5 -> "xxx".equals(insurance5.getName()));
        insurance4.ifPresent(x -> System.out.println(x));


        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");


    }


    public int readDurationOption(Properties properties, String name) {
        Optional<String> property = Optional.ofNullable(properties.getProperty(name));
        return property.flatMap(x -> {
            try {
                return Optional.of(Integer.parseInt(x));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }).filter(x -> x > 0).orElse(0);

    }

    public int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
//                将String属性转 换为数字类型
                if (i > 0) {
                    return i;
//                    检查返回的数
//                            字是否为正数
                }
            } catch (NumberFormatException nfe) {
            }
        }
        return 0;
    }


    public static void testOptions() {
        Map<String, String> map = new HashMap<>();
        Optional<String> key = Optional.ofNullable(map.get("key"));


    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    //找出年龄大于或者等于minAge参数的Person所对应的保险公司列表
    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge).
                flatMap(Person::getCar).
                flatMap(Car::getInsurance).
                map(Insurance::getName).
                orElse("");

    }


    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar).flatMap(Car::getInsurance).
                map(Insurance::getName).orElse("unkown");
    }


    /**
     * 两个optional
     *
     * @param person
     * @param car
     * @return
     */
    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    private <U> U findCheapestInsurance(Person p, Car c) {
        return null;
    }

}
