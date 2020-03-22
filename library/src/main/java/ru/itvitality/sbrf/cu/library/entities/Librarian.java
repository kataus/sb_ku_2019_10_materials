package ru.itvitality.sbrf.cu.library.entities;

public interface Librarian extends Human{
    <T extends LibrarianInsertable> void insert(T t);
    void recieveBook(Book book, Client fromClient);
    void putBook(Book book, Client toClient);
}
