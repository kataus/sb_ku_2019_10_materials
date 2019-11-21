package ru.itvitality.sbrf.cu.library.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("placement")
public class Placement extends Holder {
    @Column
    private String descroption;

    public String getDescroption() {
        return descroption;
    }

    public Placement setDescroption( String descroption ) {
        this.descroption = descroption;
        return this;
    }
}
