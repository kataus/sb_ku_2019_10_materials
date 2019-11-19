package ru.itvitality.sbrf.cu.demo.hibernate.demo.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * 1) Написать jpql, который вернет все Phone у которых id = 90
 * 2) Написать jpql, который вернет все Phone у которых id in (50, 55, 60)
 * 3) Написать jpql, который вернет все Phone у которых id между [20, 30]
 * 4) Написать jpql, который вернет все Phone у владелец по имени 'John'
 * 5) Написать jpql, который вернет все Phone отсортированные по убыванию
 * */

public class HiberDemo {
    private static final String URL = "jdbc:h2:mem:testDB;DB_CLOSE_DELAY=-1";
    private final SessionFactory sessionFactory;

    private HiberDemo() {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "create");

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass( Person.class)
                .addAnnotatedClass(Phone.class)
                .getMetadataBuilder()
                .build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static void main(String[] args) {
        HiberDemo demo = new HiberDemo();

//        demo.entityExample();
//        demo.leakageExample();
//        demo.fetchExample();
        demo.JPQLExample();
//        demo.nativeExample();

    }

    private void entityExample() {
        try (Session session = sessionFactory.openSession()) {
            Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");
            session.persist(person);
            System.out.println(person);

            Person selected = session.load(Person.class, person.getId());
            System.out.println("selected:" + selected);
            System.out.println(">>> updating >>>");

            Transaction transaction = session.getTransaction();
            transaction.begin();
            person.setAddress("moved street");
            transaction.commit();

            Person updated = session.load(Person.class, person.getId());
            System.out.println("updated:" + updated);

            session.detach(updated);

            System.out.println(">>> updating detached>>>");

            Transaction transactionDetached = session.getTransaction();
            transactionDetached.begin();
            updated.setAddress("moved street NOT CHANGED");
            transactionDetached.commit();

            Person notUpdated = session.load(Person.class, person.getId());
            System.out.println("notUpdated:" + notUpdated);
        }
    }

    private void leakageExample() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");
            session.persist(person);
            System.out.println(person);

            transaction.commit();

            session.detach(person);
            deepInIn(person);

            Person selected = session.load(Person.class, person.getId());
            System.out.println("selected:" + selected);
        }
    }

    //Далекая часть программы
    private void deepInIn(Person person) {
        Person jon = person;
        jon.setName("John");
        System.out.println("John:" + jon);
    }

    private void fetchExample() {
        long personId;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");

            List<Phone> listPhone = new ArrayList<>();
            for (int idx = 0; idx < 100; idx++) {
                listPhone.add(new Phone("+7" + idx, person));
            }
            person.setPhones(listPhone);

            System.out.println("persist...");
            session.save(person);
            personId = person.getId();

            System.out.println("commit...");

            Person person1 = new Person();
            person1.setName( "Ilia" );
            person1.setNickName( "Murometz" );
            person1.setAddress( "forest" );

            listPhone = new ArrayList<>();
            for (int idx = 0; idx < 100; idx++) {
                listPhone.add(new Phone("+0" + idx, person));
            }
            person1.setPhones(listPhone);

            session.save( person1 );

            transaction.commit();
        }

//        Person selected;
//        try (Session session = sessionFactory.openSession()) {
//            Phone selectedPhone = session.load(Phone.class, 3L);
//            System.out.println("selectedPhone:" + selectedPhone);
//
//            selected = session.load(Person.class, personId);
//            System.out.println("selected person:" + selected.getName());
//            System.out.println(selected.getPhones());
//
//
//        }

        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Person> persons =  entityManager
                .createQuery( "select p from Person p", Person.class )
                .getResultList();
        System.out.println( ">>>>>>>>>> we get " + persons.size() + " persons" );
        System.out.println( persons.get( 0 ).getPhones() );

    }

    private void JPQLExample() {
        insertPhones();
        insertJohn();

        EntityManager entityManager = sessionFactory.createEntityManager();

        System.out.println("select phone list:");

        List<Phone> selectedPhones = entityManager.createQuery(
                "select p from Phone p where p.id = :paramId", Phone.class)
                .setParameter( "paramId", 50L)
                .getResultList();

        System.out.println(selectedPhones);

        Person person = entityManager
                .createNamedQuery("get_person_by_name", Person.class)
                .setParameter("name", "Ivan")
                .getSingleResult();

        System.out.println("selected person:" + person.getNickName());


        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("name"), "Ivan"));

        Person personCriteria = entityManager.createQuery(criteria).getSingleResult();
        System.out.println("selected personCriteria:" + personCriteria.getNickName());
    }

    private void nativeExample() {
        insertPhones();

        try (Session session = sessionFactory.openSession()) {
            String name = session.doReturningWork(connection -> {
                try (PreparedStatement ps = connection.prepareStatement("select name from person where id = ?")) {
                    ps.setLong(1, 1L);
                    try (ResultSet rs = ps.executeQuery()) {
                        rs.next();
                        return rs.getString("name");
                    }
                }
            });
            System.out.println("sqL name:" + name);
        }

    }

    private void insertPhones() {
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
            session.save(person);
            System.out.println("commit...");
            transaction.commit();
        }
    }

    private void insertJohn() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            Person person = new Person();
            person.setName("John");
            person.setNickName("Bobrov");
            person.setAddress("41 Street");
            person.setPhones(List.of(new Phone("78001112233", person)));

            session.save(person);
            transaction.commit();
        }
    }

}

