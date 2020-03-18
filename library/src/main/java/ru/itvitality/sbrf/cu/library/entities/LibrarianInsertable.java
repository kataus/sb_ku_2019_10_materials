package ru.itvitality.sbrf.cu.library.entities;

import ru.itvitality.sbrf.cu.library.entities.impl.LibrarianImpl;

public interface LibrarianInsertable {
    void setLibrarian(LibrarianImpl librarian);
    LibrarianImpl getLibrarian();
}
