package tn.esprit.entities;


import jakarta.persistence.*;

@Entity
public class Room {


    @Id @GeneratedValue
    private Long id;

    private String name;

    // No-arg constructor required for Hibernate
    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    public Room(long id, String name) {
        this(name);
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
