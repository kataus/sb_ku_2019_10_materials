package ru.itvitality.sbrf.cu.rj.atm.cell.impl;

import org.junit.jupiter.api.Test;
import ru.itvitality.sbrf.cu.rj.atm.Nominal;
import ru.itvitality.sbrf.cu.rj.atm.exceptions.CellIsFullException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CellImplTest {
    @Test
    public void testPutCash() {
        CellImpl cell = new CellImpl( UUID.randomUUID().toString(), Nominal.ONE_HUNDRED, 5 );
        cell.put( 2 );
        assertEquals( cell.getCount(), 7 );
    }

    @Test
    public void testGetCashIfPresent() {
        CellImpl cell = new CellImpl( UUID.randomUUID().toString(), Nominal.ONE_HUNDRED, 10 );
        Integer gotCash = cell.get( 9 );
        assertEquals( gotCash, 9 );
    }

    @Test
    public void testGetCashIfAbsent() {
        CellImpl cell = new CellImpl( UUID.randomUUID().toString(), Nominal.ONE_HUNDRED, 10 );
        Integer gotCash = cell.get( 10 );
        assertEquals( gotCash, 10 );
        assertEquals( cell.getCount(), 0 );
    }

    @Test
    void testOverloadEmptyCell() {
        CellImpl cell = new CellImpl( UUID.randomUUID().toString(), Nominal.ONE_HUNDRED, 0 );
        assertThrows( CellIsFullException.class, () -> cell.put( 11 ) );
    }

    @Test
    void testOverloadCell() {
        CellImpl cell = new CellImpl( UUID.randomUUID().toString(), Nominal.ONE_HUNDRED, 0 );
        cell.put( 10 );
        assertThrows( CellIsFullException.class, () -> cell.put( 1 ) );
    }
}
