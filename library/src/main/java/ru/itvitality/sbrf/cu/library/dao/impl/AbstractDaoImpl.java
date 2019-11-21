package ru.itvitality.sbrf.cu.library.dao.impl;

import ru.itvitality.sbrf.cu.library.dao.utils.EntityManageContext;

import javax.persistence.EntityManager;

public abstract class AbstractDaoImpl {

    protected EntityManager em = EntityManageContext.getInstance();
}
