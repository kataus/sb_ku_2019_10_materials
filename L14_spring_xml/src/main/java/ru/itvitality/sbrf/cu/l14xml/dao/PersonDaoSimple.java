package ru.itvitality.sbrf.cu.l14xml.dao;

import org.springframework.stereotype.Service;
import ru.itvitality.sbrf.cu.l14xml.annotate.Loggable;
import ru.itvitality.sbrf.cu.l14xml.domain.Person;

@Service
public class PersonDaoSimple implements PersonDao {

    @Loggable
    public Person findByName( String name ) {
        return new Person( name, 18 );
    }
}
