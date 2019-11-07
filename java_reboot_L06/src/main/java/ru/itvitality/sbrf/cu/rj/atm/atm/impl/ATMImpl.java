package ru.itvitality.sbrf.cu.rj.atm.atm.impl;

import ru.itvitality.sbrf.cu.rj.atm.Nominal;
import ru.itvitality.sbrf.cu.rj.atm.atm.ATM;
import ru.itvitality.sbrf.cu.rj.atm.atm.ATMService;
import ru.itvitality.sbrf.cu.rj.atm.cell.Cell;
import ru.itvitality.sbrf.cu.rj.atm.cell.impl.CellImpl;
import ru.itvitality.sbrf.cu.rj.atm.exceptions.CellIsFullException;

import java.io.*;
import java.util.*;

public class ATMImpl implements ATMService, ATM {
    private List<Cell> atmStorage;

    private BufferedReader bufferedReader;

    private ATMImpl() {
        this.atmStorage = new ArrayList<>(  );
        for ( Nominal nominal : Nominal.values() ) {
            this.atmStorage.add( new CellImpl( UUID.randomUUID().toString(), nominal, 0 ) );
        }
        sortCells();
    }

    public void loadFromFile( String fileName ) throws IOException {
        atmStorage = new ArrayList<>(  );
        List<String> listIni = readIniFile( fileName );
        if ( listIni != null ) {
            for ( String str : listIni ) {
                String splitStr[] = str.split( ":" );
                Integer count = Integer.parseInt( splitStr[ 2 ] );
                Integer currNominal = Integer.parseInt( splitStr[ 1 ] );
                String id = splitStr[0];
                Cell cell = new CellImpl( id, Nominal.getNominalFromInt( currNominal ), count );
                atmStorage.add( cell );
            }
        }
        sortCells();
    }

    @Override
    public List<Nominal> putCash( List<Nominal> cashList ) {
        List<Nominal> unacceptedBanknotes = new ArrayList<>(  );
        for ( Nominal banknoteNominal : cashList ) {
            boolean accepted = false;
            for (Cell cell : atmStorage){
                if (cell.getNominal().equals( banknoteNominal )){
                    try {
                        cell.put( 1 );
                        accepted = true;
                        break;
                    } catch ( CellIsFullException e ){
                        // TODO Залоггировать
                    }
                }
            }
            if (!accepted){
                unacceptedBanknotes.add( banknoteNominal );
            }
        }
        return unacceptedBanknotes;
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

        for ( Cell cell : atmStorage ) {
            Nominal nominal = cell.getNominal();
            Integer mustGive = sum / nominal.getNominal();

            Integer canGive = cell.getCount();
            int processValue = Math.min( canGive, mustGive );
            if ( processValue > 0 ) {

                sum -= processValue * nominal.getNominal();
                cell.get( canGive );
                for (int i = 0; i < processValue; i++){
                    outList.add( nominal );
                }
            }
        }
        if ( sum != 0 ) {
            putCash( outList );
            outList = new ArrayList<>(  );
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
        for ( Cell cell : this.atmStorage ) {
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
            for ( Cell item : atmStorage ) {
                String str = item.getNominal().getNominal() + ":" + item.getCount() + "\n";
                writer.write( str );
            }
        }
    }

    @Override
    public List<Cell> getCells() {
        return null;
    }

    @Override
    public Cell extractCell( String id ) {
        return null;
    }

    @Override
    public void insertCell( Cell cell ) {

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
            atm.atmStorage = new ArrayList<>();
            try {
                atm.loadFromFile( fileName );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            return atm;
        }
    }

    private void sortCells() {
        Collections.sort( atmStorage, new Comparator<Cell>() {
            @Override
            public int compare( Cell o1, Cell o2 ) {
                return o2.getNominal().getNominal().compareTo( o1.getNominal().getNominal() );
            }
        } );

    }
}
