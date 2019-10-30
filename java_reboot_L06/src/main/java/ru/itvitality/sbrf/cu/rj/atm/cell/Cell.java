package ru.itvitality.sbrf.cu.rj.atm.cell;

import ru.itvitality.sbrf.cu.rj.atm.Nominal;

public interface Cell {
    void put(Integer count);
    Integer get (Integer count);
    Integer getCount();
    Nominal getNominal();
}
