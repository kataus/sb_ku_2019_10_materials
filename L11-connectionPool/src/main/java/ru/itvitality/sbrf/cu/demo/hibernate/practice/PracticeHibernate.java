package ru.itvitality.sbrf.cu.demo.hibernate.practice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.itvitality.sbrf.cu.demo.hibernate.demo.model.Person;
import ru.itvitality.sbrf.cu.demo.hibernate.demo.model.Phone;

import java.util.ArrayList;
import java.util.List;

/*
 * 1) Сделать так, чтобы метод throwLazyException генерировал исключение org.hibernate.LazyInitializationException
 *
 * */

public class PracticeHibernate {

    private static final String URL = "jdbc:h2:mem:testDB;DB_CLOSE_DELAY=-1";
    private final SessionFactory sessionFactory;

    public PracticeHibernate() {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "create");

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Phone.class)
                .getMetadataBuilder()
                .build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static void main(String[] args) {
        PracticeHibernate practiceHibernate = new PracticeHibernate();
        practiceHibernate.throwLazyException();
    }

    private void throwLazyException() {
        Person selected;
        try (Session session = sessionFactory.openSession()) {
            Person person = insertPerson();
            selected = session.load(Person.class, person.getId());
            System.out.println(selected.getPhones());
        }
    }

    private Person insertPerson() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");

            List<Phone> listPhone = new ArrayList<>();
            for (int idx = 0; idx < 100; idx++) {
                listPhone.add(new Phone("+" + idx, person));
            }
            person.setPhones(listPhone);

            System.out.println("persist...");
            session.save(person);

            System.out.println("commit...");
            transaction.commit();
            return person;
        }
    }

}
