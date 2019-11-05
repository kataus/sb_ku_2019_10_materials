package ru.itvitality.sbrf.cu.rj.atm.atm;

import ru.itvitality.sbrf.cu.rj.atm.cell.Cell;

import java.io.IOException;
import java.util.List;

public interface ATMService {
    Integer getBalance();
    void saveToFile(String fileName) throws IOException;

    List<Cell> getCells();
    Cell extractCell(String id);
    void insertCell(Cell cell);
}
