package ru.itvitality.sbrf.cu.demo.hibernate.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Phone {

    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "phone_number", nullable = false)
    private String number;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Phone() {
    }

    public Phone(String number, Person person) {
        this.number = number;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", personId=" + person.getId() +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
