package ru.itvitality.sbrf.cu.l14xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itvitality.sbrf.cu.l14xml.domain.Person;
import ru.itvitality.sbrf.cu.l14xml.service.PersonService;
import ru.itvitality.sbrf.cu.l14xml.service.PersonServiceImpl;


public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( "/spring-context.xml" );
        PersonService personService = context.getBean( PersonServiceImpl.class );
        Person ivan = personService.getByName( "ivan" );
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
    }
}
