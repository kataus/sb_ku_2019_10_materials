package ru.itvitality.sbrf.cu.rj.atm;

import ru.itvitality.sbrf.cu.rj.atm.atm.ATM;
import ru.itvitality.sbrf.cu.rj.atm.atm.ATMService;
import ru.itvitality.sbrf.cu.rj.atm.atm.impl.ATMImpl;
import ru.itvitality.sbrf.cu.rj.atm.cell.Cell;
import ru.itvitality.sbrf.cu.rj.atm.cell.impl.CellImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Starter {
    private ATM atm;
    private String FILE_NAME;

    public static void main( String args[] ) throws IOException {
        Starter starter=new Starter();
        if(args.length>0) {
            starter.FILE_NAME = args[0];
            if (starter.FILE_NAME.trim().length() > 0) {
                File file=new File(starter.FILE_NAME);
                if(file.exists())
                    starter.startAtm(starter.FILE_NAME);
                else
                    starter.startAtm();
            }
            else
                starter.startAtm();
        }
        else
            starter.startAtm();
        starter.startClientInteraction();
    }



    private void startClientInteraction() throws IOException {
        System.out.println( "Hello, my dear friend. What's your name?" );
        Scanner scanner = new Scanner( System.in );
        Scanner stringScanner;
        String name = scanner.nextLine();
        System.out.println( "Hello " + name + "! What's your command? (add, get, exit, getCells, insertCell, extractCell)" );
        String operation = scanner.nextLine();
        while ( ! operation.equalsIgnoreCase( "exit" ) ) {
            switch ( operation.toLowerCase() ) {
                case "add":
                    System.out.println( "What nominal?" );
                    String nominalsString = scanner.nextLine();
                    stringScanner = new Scanner(nominalsString);
                    Integer value;
                    while (stringScanner.hasNextInt()){
                        value = stringScanner.nextInt();
                        Nominal nominal = Nominal.getNominalFromInt( value );
                        if ( nominal != null ) {
                            List<Nominal> nominals = new ArrayList<>();
                            nominals.add( nominal );
                            atm.putCash( nominals );
                            System.out.println( "Success " + nominal.getNominal() );
                        } else {
                            System.out.println( value + "? Was is das?" );
                        }
                    }
                    break;
                case "get":
                    System.out.println("How much? Enter sum");
                    String sumString = scanner.nextLine();
                    stringScanner = new Scanner(sumString);
                    value = stringScanner.nextInt();
                    atm.getCash(value);
                    break;
                case "getcells":
                    for(Cell cell:((ATMService)atm).getCells())
                        System.out.println(((CellImpl)cell).getId() + " " + ((CellImpl)cell).getNominal()
                            + " " + ((CellImpl)cell).getCount());
                    break;
                case "insertcell":
                    System.out.println("Enter cell's nominal");
                    String nominalString = scanner.nextLine();
                    stringScanner = new Scanner(nominalString);
                    value = stringScanner.nextInt();
                    ((ATMImpl)this.atm).insertCell(new CellImpl(UUID.randomUUID().toString(),Nominal.getNominalFromInt(value),0));
                    break;
                case "extractcell":
                    System.out.println("Enter cell's ID");
                    String iDString = scanner.nextLine();
                    ((ATMImpl)atm).extractCell(iDString);
                    break;
                default:
                System.out.println( "Incorrect command" );
            }

            System.out.println( " What's your next command? (add, get, exit, getCells, insertCell, extractCell)" );
            operation = scanner.nextLine();
        }
        ( (ATMService) atm ).saveToFile( FILE_NAME );

    }

    private void startAtm() {
        atm = ATMImpl.ATMImplBuilder.build();
    }

    private void startAtm( String fileName ) {
        atm= ATMImpl.ATMImplBuilder.buildFromFile( fileName );
    }
}
