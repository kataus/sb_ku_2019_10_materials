package ru.itvitality.sbrf.cu.l14xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itvitality.sbrf.cu.l14xml.domain.Person;


public class Main {

    public static void main(String[] args) {
        Person ivan = null;
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
    }
}
