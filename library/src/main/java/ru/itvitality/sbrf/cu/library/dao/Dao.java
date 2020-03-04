package ru.itvitality.sbrf.cu.library.dao;

import org.hibernate.SessionFactory;

import java.util.List;

public interface Dao {
    <T> void insert (T t);
}
