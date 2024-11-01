package com.example.spring_first_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_demo")
public class UserDemo {
    @Id()
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column()
    private String firstName;

    @Column()
    private String lastName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserDemo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
