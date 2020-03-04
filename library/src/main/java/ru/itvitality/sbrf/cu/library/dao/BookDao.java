package ru.itvitality.sbrf.cu.library.dao;

import ru.itvitality.sbrf.cu.library.entities.Book;

public interface BookDao extends Dao{
    void insert (Book book);

}
