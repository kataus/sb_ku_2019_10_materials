package ru.itvitality.sbrf.cu.library.entities;

import ru.itvitality.sbrf.cu.library.entities.impl.HumanImpl;

public interface Book extends LibrarianInsertable {
    void setHolder(HumanImpl human);
}
