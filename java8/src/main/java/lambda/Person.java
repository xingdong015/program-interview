package lambda;

import java.util.Optional;

public class Person {
    private Optional<Car> car;
    public Optional<Car> getCar() { return car; }

    int age;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
