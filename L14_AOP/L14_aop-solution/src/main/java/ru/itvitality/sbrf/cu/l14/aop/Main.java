package ru.itvitality.sbrf.cu.l14.aop;

import org.springframework.context.annotation.*;
import ru.itvitality.sbrf.cu.l14.aop.domain.Person;
import ru.itvitality.sbrf.cu.l14.aop.service.PersonService;

@EnableAspectJAutoProxy
@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        PersonService service = context.getBean(PersonService.class);

        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
    }
}
