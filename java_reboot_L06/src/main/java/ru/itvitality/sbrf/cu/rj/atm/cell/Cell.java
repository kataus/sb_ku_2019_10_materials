package ru.itvitality.sbrf.cu.rj.atm.cell;

import ru.itvitality.sbrf.cu.rj.atm.Balanceable;
import ru.itvitality.sbrf.cu.rj.atm.Nominal;

public interface Cell extends Balanceable {
    void put(Integer count);
    Integer get (Integer count);
    Integer getCount();
    Nominal getNominal();
}
