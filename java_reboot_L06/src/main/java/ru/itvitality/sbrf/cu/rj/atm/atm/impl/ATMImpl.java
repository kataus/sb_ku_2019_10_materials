package ru.itvitality.sbrf.cu.rj.atm.atm.impl;

import ru.itvitality.sbrf.cu.rj.atm.Nominal;
import ru.itvitality.sbrf.cu.rj.atm.atm.ATM;
import ru.itvitality.sbrf.cu.rj.atm.atm.ATMService;
import ru.itvitality.sbrf.cu.rj.atm.cell.Cell;
import ru.itvitality.sbrf.cu.rj.atm.cell.impl.CellImpl;

import java.io.*;
import java.util.*;

public class ATMImpl implements ATMService, ATM {
    private Map<Nominal, Cell> atmStorage;

    private BufferedReader bufferedReader;

    private ATMImpl() {
        this.atmStorage = new HashMap<>();
        for ( Nominal nominal : Nominal.values() ) {
            this.atmStorage.put( nominal, new CellImpl( UUID.randomUUID().toString(), nominal, 0 ) );
        }
    }

    void loadFromFile( String fileName ) throws IOException {
//        List<String> listIni = readIniFile( fileName );
//        if ( listIni != null ) {
//            for ( String str : listIni ) {
//                String splitStr[] = str.split( ":" );
//                Integer count = Integer.parseInt( splitStr[ 1 ] );
//                Integer currNominal = Integer.parseInt( splitStr[ 0 ] );
//                Cell cell = new CellImpl( Nominal.getNominalFromInt( currNominal ), count );
//                atmStorage.put( cell.getNominal(), cell );
//
//            }
//        }
    }

    @Override
    public void putCash( List<Nominal> cashList ) {
        for ( Nominal banknoteNominal : cashList ) {
            Cell curCell = this.atmStorage.get( banknoteNominal );
            curCell.put( 1 );

        }
    }

    @Override
    public List<Nominal> getCash( Integer sum ) {
        if ( sum % 100 != 0 ) {
            throw new IllegalArgumentException( "Введена некорректная сумма. Минимальная купюра - 100р." );
        }
        if ( sum > this.getBalance() ) {
            throw new IllegalArgumentException( "Запрашиваемая сумма превышает остаток денег в банкомате." );
        }
        List<Nominal> outList = new ArrayList<>();
        List<Nominal> nominalList = new ArrayList<>( this.atmStorage.keySet() );
        nominalList.sort( Comparator.reverseOrder() );

        Map<Nominal, Integer> checkMap = new HashMap<>();

        for ( Nominal nominal : nominalList ) {
            Cell cell = this.atmStorage.get( nominal );
            Integer mustGive = sum / nominal.getNominal();
            sum = sum % nominal.getNominal();

            Integer canGive = cell.getCount();

            if ( canGive < mustGive ) {
                sum += ( mustGive - canGive ) * nominal.getNominal();
                checkMap.put( nominal, canGive );
            } else {
                checkMap.put( nominal, mustGive );
            }
        }
        if ( sum != 0 ) {
            int iHave = 0;
            for ( Nominal key : checkMap.keySet() ) {
                iHave += key.getNominal() * checkMap.get( key );
            }
            throw new IllegalArgumentException( "Невозможно выдать запрашиваемую сумму имеющимися купюрами, максимально возможная сумма: " + iHave );
        } else {
            for ( Nominal key : checkMap.keySet() ) {
                addBanknotes( checkMap.get( key ), key, outList );
            }
        }
        return outList;
    }

    private void addBanknotes( Integer number, Nominal nominal, List<Nominal> outList ) {
        for ( int i = 0; i < number; i++ ) {
            outList.add( nominal );
        }
    }

    @Override
    public Integer getBalance() {
        Integer balance = 0;
        for ( Cell cell : this.atmStorage.values() ) {
            balance += cell.getCount() * cell.getNominal().getNominal();
        }
        return balance;
    }

    @Override
    public void saveToFile( String fileName ) throws IOException {
        File file = new File( fileName );
        if ( file.exists() ) {
            file.delete();
        }
        try ( FileWriter writer = new FileWriter( file ) ) {
            for ( Cell item : atmStorage.values() ) {
                String str = item.getNominal().getNominal() + ":" + item.getCount() + "\n";
                writer.write( str );
            }
        }
    }

    public ATMImpl setBufferedReader( BufferedReader bufferedReader ) {
        this.bufferedReader = bufferedReader;
        return this;
    }

    private List<String> readIniFile( String fileName ) throws IOException {
        File file = new File( fileName );

        if ( bufferedReader == null ) {
            bufferedReader = new BufferedReader( new FileReader( file ) );
        }
        String line;
        List<String> linesNominal = new ArrayList<String>();
        while ( ( line = bufferedReader.readLine() ) != null ) {
            linesNominal.add( line );
        }
        return linesNominal;

    }

    public static class ATMImplBuilder {
        public static ATMImpl build() {
            ATMImpl atm = new ATMImpl();
            return atm;
        }

        public static ATMImpl buildFromFile( String fileName ) {
            ATMImpl atm = new ATMImpl();
            atm.atmStorage = new HashMap<>();
            try {
                atm.loadFromFile( fileName );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            return atm;
        }
    }
}
