package ru.itvitality.sbrf.cu.rj.atm.atm.impl;

import com.google.gson.Gson;
import ru.itvitality.sbrf.cu.rj.atm.Balanceable;
import ru.itvitality.sbrf.cu.rj.atm.Nominal;
import ru.itvitality.sbrf.cu.rj.atm.atm.ATM;
import ru.itvitality.sbrf.cu.rj.atm.atm.ATMService;
import ru.itvitality.sbrf.cu.rj.atm.cell.Cell;
import ru.itvitality.sbrf.cu.rj.atm.cell.impl.CellImpl;
import ru.itvitality.sbrf.cu.rj.atm.exceptions.CellIsFullException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ATMImpl implements ATMService, ATM, Balanceable {
    private Safe safe = new Safe();
    private List<Balanceable> balanceables;

    private BufferedReader bufferedReader;

    private ATMImpl() {

    }

    public void loadFromFile( String fileName ) throws IOException {
        File file = new File( fileName );
        if (file.exists()) {
            safe.setCells( new ArrayList<>() );

            String data = readIniFile( fileName );
            Gson gson = new Gson();
            Safe safe = gson.fromJson( data, Safe.class );
            this.safe = safe;

        } else {
            this.safe.setCells( new ArrayList<>() );
            for ( Nominal nominal : Nominal.values() ) {
                this.safe.getCells().add( new CellImpl( UUID.randomUUID().toString(), nominal, 0 ) );
            }
        }
        sortCells();
    }

    @Override
    public List<Nominal> putCash( List<Nominal> cashList ) {
        List<Nominal> unacceptedBanknotes = new ArrayList<>();
        for ( Nominal banknoteNominal : cashList ) {
            boolean accepted = false;
            for ( Cell cell : safe.getCells() ) {
                if ( cell.getNominal().equals( banknoteNominal ) ) {
                    try {
                        cell.put( 1 );
                        accepted = true;
                        break;
                    } catch ( CellIsFullException e ) {
                        // TODO Залоггировать
                    }
                }
            }
            if ( ! accepted ) {
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

        for ( Cell cell : safe.getCells() ) {
            Nominal nominal = cell.getNominal();
            Integer mustGive = sum / nominal.getNominal();

            Integer canGive = cell.getCount();
            int processValue = Math.min( canGive, mustGive );
            if ( processValue > 0 ) {

                sum -= processValue * nominal.getNominal();
                cell.get( canGive );
                for ( int i = 0; i < processValue; i++ ) {
                    outList.add( nominal );
                }
            }
        }
        if ( sum != 0 ) {
            putCash( outList );
            outList = new ArrayList<>();
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
        Integer result = 0;
        for (Balanceable balanceable: balanceables){
            result += balanceable.getBalance();
        }
        return result;
    }

    @Override
    public void saveToFile( String fileName ) throws IOException {
        File file = new File( fileName );
        if ( file.exists() ) {
            file.delete();
        }
        Gson gson = new Gson();
        String data = gson.toJson( safe );
        try ( FileWriter writer = new FileWriter( file ) ) {
            writer.write( data );
            writer.flush();
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

    private String readIniFile( String fileName ) throws IOException {
        File file = new File( fileName );

        if ( bufferedReader == null ) {
            bufferedReader = new BufferedReader( new FileReader( file ) );
        }
        String line;
        StringBuilder sb = new StringBuilder(  );
        while ( ( line = bufferedReader.readLine() ) != null ) {
            sb.append( line );
        }
        return sb.toString();

    }

    public static class ATMImplBuilder {
        public static ATMImpl build() {
            ATMImpl atm = new ATMImpl();
            return atm;
        }

        public static ATMImpl buildFromFile( String fileName ) {
            ATMImpl atm = new ATMImpl();

            try {
                atm.loadFromFile( fileName );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            return atm;
        }
    }

    private void sortCells() {
        Collections.sort( safe.getCells(), new Comparator<Cell>() {
            @Override
            public int compare( Cell o1, Cell o2 ) {
                return o2.getNominal().getNominal().compareTo( o1.getNominal().getNominal() );
            }
        } );
        balanceables = safe.getCells().stream().collect( Collectors.toList());

    }
}
