package ru.itvitality.sbrf.cu.rj.atm;

public enum Nominal {
    ONE_HUNDRED( 100 ),
    TWO_HUNDREDS( 200 ),
    FIVE_HUNDRED( 500 ),
    ONE_THOUSAND( 1000 ),
    TWO_THOUSANDS( 2000 ),
    FIVE_THOUSANDS( 5000 );

    private Integer nominal;

    Nominal( Integer nominal ) {
        this.nominal = nominal;
    }

    public Integer getNominal() {
        return nominal;
    }

    public static Nominal getNominalFromInt( Integer value ) {
        if ( value == null ) return null;
        for ( Nominal nominal : values() ) {
            if ( nominal.nominal.equals( value ) ) return nominal;
        }
        return null;
    }
}
