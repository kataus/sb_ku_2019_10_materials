package ru.sbrf.ku.java.generics;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Intro {

    public static void main( String[] args ) {
        new Intro().beforGenerics();
        new Intro().generics();
    }

    //До Generics
    private void beforGenerics() {
        List list = new ArrayList();
        list.add( 4.0 );
        list.add( 4L );
        list.add( "Hello" );
        list.add( LocalTime.now() );

        printRow( list );
    }

    private void printRow( List list ) {
        for ( Object item : list ) { //Object !!!
            System.out.println( item );
        }
    }

    //Эра Generics
    private void generics() {
        List<Integer> list = new ArrayList<>();
        //list.add(4.0); //ошибка компиляции
        //list.add(4L);    //ошибка компиляции
        //list.add("Hello"); //ошибка компиляции
        //list.add(LocalTime.now()); //ошибка компиляции

        list.add( 2 );
        list.add( 3 );

        summInt( list );

        print( list );
    }

    private void summInt( List<Integer> list ) {
        int summ = 0;
        for ( int val : list ) {  //конкретный тип
            summ += val;
        }
        System.out.println( "\nsumma:" + summ );
    }

    private <T> void print( List<T> list ) {
        System.out.println( "" );
        for ( T item : list ) {
            System.out.println( item );
        }
    }

}
