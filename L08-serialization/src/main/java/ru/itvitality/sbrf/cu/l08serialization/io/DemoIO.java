package ru.itvitality.sbrf.cu.l08serialization.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class DemoIO {

    private static String personFile = "person.bin";
    private static String textFile = "textFile.txt";

    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        System.out.println( "current dir: " + System.getProperty( "user.dir" ) );
        //     copyFile();
        //       writeObject();
        readObject();
        //     writeTextFile();
        //     readTextFile();

    }

    //Обратите внимание на цепочку декораторов
    private static void copyFile() throws IOException {
        try ( ZipOutputStream zipOut = new ZipOutputStream( new BufferedOutputStream( new FileOutputStream( textFile + "_copy.zip" ) ) );
              BufferedInputStream bis = new BufferedInputStream( new FileInputStream( textFile ) ) ) {

            ZipEntry zipEntry = new ZipEntry( textFile );
            zipOut.putNextEntry( zipEntry );
            int buffer;
            while ( ( buffer = bis.read() ) > 0 ) {
                zipOut.write( buffer );
            }
            zipOut.closeEntry();
        }
    }

    private static void writeObject() throws IOException {
        try ( FileOutputStream fos = new FileOutputStream( personFile ) ) {
            ObjectOutputStream oos = new ObjectOutputStream( fos );
            Person person = new Person( 92, "SerialPerson", "hidden" );
            System.out.println( "serializing:" + person );
            oos.writeObject( person );
            oos.flush();
        }
    }

    private static void readObject() throws IOException, ClassNotFoundException {
        try ( FileInputStream fis = new FileInputStream( personFile ) ) {
            ObjectInputStream ois = new ObjectInputStream( fis );
            Person person = (Person) ois.readObject();
            System.out.println( "read object:" + person );
        }
    }

    private static void writeTextFile() throws IOException {
        String line1 = "Hello Java, str1";
        String line2 = "Hello Java, str2";
        try ( BufferedWriter writer = new BufferedWriter( new FileWriter( textFile ) ); ) {
            writer.write( line1 );
            writer.newLine();
            writer.write( line2 );
        }
    }

    private static void readTextFile() throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( textFile ) ) ) {
            String line = reader.readLine();
            System.out.println( "text from the file:" );
            while ( line != null ) {
                System.out.println( line );
                line = reader.readLine();
            }
        }
    }
}
