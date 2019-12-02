package ru.itvitality.sbrf.cu.l14xml;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.itvitality.sbrf.cu.l14xml.domain.Person;
import ru.itvitality.sbrf.cu.l14xml.service.PersonService;
import ru.itvitality.sbrf.cu.l14xml.service.PersonServiceImpl;


@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class Main {

    public static void main( String[] args ) {
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( "/spring-context.xml" );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( Main.class );
        PersonService personService = context.getBean( PersonServiceImpl.class );
        Person ivan = personService.getByName( "ivan" );
        System.out.println( "name: " + ivan.getName() + " age: " + ivan.getAge() );
    }
}
