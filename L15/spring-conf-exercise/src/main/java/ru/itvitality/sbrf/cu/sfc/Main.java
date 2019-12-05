package ru.itvitality.sbrf.cu.sfc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.itvitality.sbrf.cu.sfc.domain.Person;
import ru.itvitality.sbrf.cu.sfc.service.PersonService;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        PersonService service = context.getBean(PersonService.class);

        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
    }
}
