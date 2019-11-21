package ru.itvitality.sbrf.cu.library.entities;

import javax.persistence.*;

@Entity
@Table(name = "movement")
public class Movement {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;
}
