package ru.itvitality.sbrf.cu.sfc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itvitality.sbrf.cu.sfc.domain.Person;
import ru.itvitality.sbrf.cu.sfc.service.PersonService;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = null;

        PersonService service = context.getBean(PersonService.class);

        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
    }
}
