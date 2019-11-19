package ru.itvitality.sbrf.cu.rj.atm.cell.impl;

import ru.itvitality.sbrf.cu.rj.atm.Nominal;
import ru.itvitality.sbrf.cu.rj.atm.cell.Cell;
import ru.itvitality.sbrf.cu.rj.atm.exceptions.CellIsFullException;

public class CellImpl implements Cell {
    public static final Integer MAX_CAPACITY = 10;
    private final String id;
    private final Nominal nominal;
    private Integer count;

    public CellImpl( String id, Nominal cellNominal, Integer cellCount ) {
        this.id = id;
        this.nominal = cellNominal;
        this.count = cellCount;
    }

    @Override
    public void put( Integer count ) {
        if (this.count + count > MAX_CAPACITY){
            throw new CellIsFullException();
        }
        this.count += count;
    }

    @Override
    public Integer get( Integer count ) {
        Integer toReturn = ( this.count >= count ) ? count : this.count;
        this.count -= toReturn;
        return toReturn;
    }

    @Override
    public Integer getCount() {
        return this.count;
    }

    @Override
    public Nominal getNominal() {
        return this.nominal;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Integer getBalance() {
        return nominal.getNominal()*count;
    }
}
