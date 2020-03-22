package ru.itvitality.sbrf.cu.library.dao;

import ru.itvitality.sbrf.cu.library.entities.Book;
import ru.itvitality.sbrf.cu.library.entities.Client;

public interface BookDao extends LibrarianInsertableDao{
    public void putBook(Book book, Client toClient);
}
