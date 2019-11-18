package ru.itvitality.sbrf.cu.rj.atm.atm.impl;

import org.junit.jupiter.api.Test;
import ru.itvitality.sbrf.cu.rj.atm.Nominal;
import ru.itvitality.sbrf.cu.rj.atm.atm.ATM;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AtmImplTest {

    @Test
    public void getCashTestEven() {
        List<Nominal> nominals = new ArrayList<>();
        nominals.add( Nominal.FIVE_HUNDRED );
        nominals.add( Nominal.TWO_HUNDREDS );
        nominals.add( Nominal.ONE_HUNDRED );
        nominals.add( Nominal.ONE_HUNDRED );
        nominals.add( Nominal.ONE_HUNDRED );

        ATM atm = ATMImpl.ATMImplBuilder.build();
        atm.putCash( nominals );

        List<Nominal> gotList = atm.getCash( 1000 );

        Integer gotCash = 0;
        for ( Nominal nominal : gotList ) {
            gotCash += nominal.getNominal();
        }

        assertEquals( 1000, gotCash );
    }

    @Test
    public void getCashTestLess() {
        List<Nominal> nominals = new ArrayList<>();
        nominals.add( Nominal.FIVE_HUNDRED );
        nominals.add( Nominal.TWO_HUNDREDS );
        nominals.add( Nominal.ONE_HUNDRED );
        nominals.add( Nominal.ONE_HUNDRED );
        nominals.add( Nominal.ONE_HUNDRED );

        ATM atm = ATMImpl.ATMImplBuilder.build();
        atm.putCash( nominals );

        List<Nominal> gotList = atm.getCash( 900 );

        Integer gotCash = 0;
        for ( Nominal nominal : gotList ) {
            gotCash += nominal.getNominal();
        }

        assertEquals( 900, gotCash );
    }

    @Test
    public void getCashTestNegative() {
        List<Nominal> nominals = new ArrayList<>();
        nominals.add( Nominal.FIVE_HUNDRED );
        nominals.add( Nominal.TWO_HUNDREDS );
        nominals.add( Nominal.ONE_HUNDRED );
        nominals.add( Nominal.ONE_HUNDRED );
        nominals.add( Nominal.ONE_HUNDRED );

        ATM atm = ATMImpl.ATMImplBuilder.build();
        atm.putCash( nominals );

        assertThrows( IllegalArgumentException.class, () -> {
            atm.getCash( 950 );

        } );
    }

    @Test
    public void getCashTestOverdraft() {
        List<Nominal> nominals = new ArrayList<>();
        nominals.add( Nominal.FIVE_HUNDRED );
        nominals.add( Nominal.TWO_HUNDREDS );
        nominals.add( Nominal.ONE_HUNDRED );
        nominals.add( Nominal.ONE_HUNDRED );
        nominals.add( Nominal.ONE_HUNDRED );

        ATM atm = ATMImpl.ATMImplBuilder.build();
        atm.putCash( nominals );

        assertThrows( IllegalArgumentException.class, () -> {
            atm.getCash( 1200 );
        } );
    }

    @Test
    public void testLoadFromFile() throws IOException {
        BufferedReader mockedReaded = mock(BufferedReader.class);
        when( mockedReaded.readLine() ).thenReturn( "4f5c408d-67aa-40b0-8410-f3b95565d12b:200:1" ).thenReturn( "3a4c84d2-502f-440e-9fcd-49597e67511b:100:3" ).thenReturn( null );

        ATMImpl atm = ATMImpl.ATMImplBuilder.build();
        atm.setBufferedReader( mockedReaded );

        atm.loadFromFile( "/home/renatka/Downloads/test/1121977/sb_ku_2019_10_materials/test.ini" );
//        atm.loadFromFile( "/test" );

        assertEquals( atm.getBalance(), 500 );
    }
}
