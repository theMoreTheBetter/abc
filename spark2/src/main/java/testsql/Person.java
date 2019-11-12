package testsql;

import lombok.Data;

import java.io.Serializable;


@Data
public class Person implements Serializable {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(){}
}
