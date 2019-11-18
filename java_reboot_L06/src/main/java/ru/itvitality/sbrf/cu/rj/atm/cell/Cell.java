package ru.itvitality.sbrf.cu.rj.atm.cell;

import ru.itvitality.sbrf.cu.rj.atm.Nominal;

public interface Cell {
    public static int MAX_COUNT = 10;
    void put(Integer count);
    Integer get (Integer count);
    Integer getCount();
    Nominal getNominal();
    public String getId();
}
