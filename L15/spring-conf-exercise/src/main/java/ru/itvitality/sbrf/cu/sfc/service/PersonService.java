package ru.itvitality.sbrf.cu.sfc.service;

import ru.itvitality.sbrf.cu.sfc.domain.Person;

public interface PersonService {

    Person getByName(String name);
}
