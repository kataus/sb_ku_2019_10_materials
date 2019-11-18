package ru.itvitality.sbrf.cu.rj.atm.atm.impl;

import ru.itvitality.sbrf.cu.rj.atm.Nominal;
import ru.itvitality.sbrf.cu.rj.atm.atm.ATM;
import ru.itvitality.sbrf.cu.rj.atm.atm.ATMService;
import ru.itvitality.sbrf.cu.rj.atm.cell.Cell;
import ru.itvitality.sbrf.cu.rj.atm.cell.impl.CellImpl;

import java.io.*;
import java.util.*;

public class ATMImpl implements ATMService, ATM {
    private Map<Cell, Nominal> atmStorage;

    private BufferedReader bufferedReader;

    private ATMImpl() {
        this.atmStorage = new HashMap<>();
        for ( Nominal nominal : Nominal.values() ) {
            this.atmStorage.put( new CellImpl( UUID.randomUUID().toString(), nominal, 0 ), nominal );
        }
    }

    void loadFromFile( String fileName ) throws IOException {
        List<String> listIni = readIniFile( fileName );
        if ( listIni != null ) {
            for ( String str : listIni ) {
                String splitStr[] = str.split( ":" );
                Integer count = Integer.parseInt( splitStr[ 2 ] );
                Integer currNominal = Integer.parseInt( splitStr[ 1 ] );
                Cell cell = new CellImpl(splitStr[0], Nominal.getNominalFromInt( currNominal ), count );
                atmStorage.put( cell, cell.getNominal() );

            }
        }
    }

    @Override
    public void putCash( List<Nominal> cashList ) {
        Cell curCell=null;
        for ( Nominal banknoteNominal : cashList ) {
            Integer maxCount=0;
            for(Map.Entry<Cell,Nominal> cellNominalEntry: atmStorage.entrySet()) {
                if(cellNominalEntry.getValue()==banknoteNominal&&cellNominalEntry.getKey().getCount()>=maxCount&&cellNominalEntry.getKey().getCount().intValue()<CellImpl.MAX_COUNT){
                    maxCount=cellNominalEntry.getKey().getCount();
                    curCell=cellNominalEntry.getKey();
                }
            }
            curCell.put(1);
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
        List<Nominal> nominalList = new ArrayList<>(this.atmStorage.values());
        nominalList.sort( Comparator.reverseOrder() );

        Map<Nominal, Integer> checkMap = new HashMap<>();

        for ( Nominal nominal : nominalList ) {
            Integer mustGive = sum / nominal.getNominal();
            sum = sum % nominal.getNominal();
            Integer canGive = 0;
            for(Cell cell:this.atmStorage.keySet())
                if(cell.getNominal()==nominal)
                    canGive+=cell.getCount();

            if (canGive < mustGive) {
                sum += (mustGive - canGive) * nominal.getNominal();
                checkMap.put(nominal, canGive);
            } else {
                if(!checkMap.containsKey(nominal))
                    checkMap.put(nominal, mustGive);
            }
        }
        if ( sum != 0 ) {
            int iHave = 0;
            for ( Nominal key : checkMap.keySet() ) {
                iHave += key.getNominal() * checkMap.get( key );
            }
            throw new IllegalArgumentException( "Невозможно выдать запрашиваемую сумму имеющимися купюрами, максимально возможная сумма: " + iHave );
        } else {
            for ( Nominal key : checkMap.keySet() )
                addBanknotes( checkMap.get( key ), key, outList );
        }
        return outList;
    }
    private void addBanknotes( Integer number, Nominal nominal, List<Nominal> outList ) {
        for ( int i = 0; i < number; i++ ) {
            Cell minCountCell = null;
            int minCount=Cell.MAX_COUNT;
            outList.add( nominal );
            for(Cell cell:this.atmStorage.keySet())
                if (cell.getNominal()==nominal&&cell.getCount()>0&&cell.getCount()<=minCount){
                    minCount=cell.getCount();
                    minCountCell=cell;
                }
            minCountCell.get(1);
        }
    }

    @Override
    public Integer getBalance() {
        Integer balance = 0;
        for ( Cell cell : this.atmStorage.keySet() ) {
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
            for ( Cell item : this.atmStorage.keySet() ) {
                String str = (item.getId() + ":" + item.getNominal().getNominal() + ":" + item.getCount() + "\n");
                writer.write( str );
            }
        }
    }

    @Override
    public List<Cell> getCells() {
        List<Cell> listCell = new ArrayList<>();
        for(Cell cell: this.atmStorage.keySet())
            listCell.add(cell);
        return listCell;
    }

    @Override
    public Cell extractCell(String id) {
        Cell returnedCell = null;
        for(Cell cell:this.atmStorage.keySet())
            if(cell.getId().compareToIgnoreCase(id)==0){
                returnedCell=cell;
                break;
            }
        if(returnedCell!=null)
            this.atmStorage.remove(returnedCell, returnedCell.getNominal());
        else
            System.out.println("There isn't ID " + id + "!");
        return returnedCell;
    }

    @Override
    public void insertCell(Cell cell) {
        this.atmStorage.put(cell,cell.getNominal());
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
