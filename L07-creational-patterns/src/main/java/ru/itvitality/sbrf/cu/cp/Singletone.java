package ru.itvitality.sbrf.cu.cp;


public class Singletone {
    private Singletone() {

    }

    private static Singletone instanse;

    public static Singletone getInstance() {
        if ( instanse == null ) {
            synchronized ( Singletone.class ) {
                if ( instanse == null ) {
                    instanse = new Singletone();
                }
            }
        }
        return instanse;
    }


}
