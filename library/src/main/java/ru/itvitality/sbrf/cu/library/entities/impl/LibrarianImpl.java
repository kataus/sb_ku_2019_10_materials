package ru.itvitality.sbrf.cu.library.entities.impl;

import ru.itvitality.sbrf.cu.library.dao.LibrarianInsertableDao;
import ru.itvitality.sbrf.cu.library.dao.impl.LibrarianInsertDaoImpl;
import ru.itvitality.sbrf.cu.library.entities.Book;
import ru.itvitality.sbrf.cu.library.entities.Client;
import ru.itvitality.sbrf.cu.library.entities.Librarian;
import ru.itvitality.sbrf.cu.library.entities.LibrarianInsertable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Librarian")
public class LibrarianImpl extends HumanImpl implements Librarian {

    public LibrarianImpl(String name){
        super(name);
    }

    @Override
    public <T extends LibrarianInsertable> void insert(T librarianInsertable) {
        librarianInsertable.setLibrarian(this);
        LibrarianInsertableDao librarianInsertableDao = new LibrarianInsertDaoImpl();
        if (librarianInsertable.getClass()==BookImpl.class)
            ((BookImpl)librarianInsertable).setHolder(this);
        librarianInsertableDao.insert(librarianInsertable);
    }

    @Override
    public void recieveBook(Book book, Client fromClient) {

    }

    @Override
    public void putBook(Book book, Client toClient) {

    }
}
