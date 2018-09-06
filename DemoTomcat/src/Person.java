
import java.io.*;

public class Person {
    private String name;
    private int age;
    private String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public void saySomething() {
        System.out.println("Person saySomething : " + this.name + ", " +  this.age + ", " + this.city);
    }
}
