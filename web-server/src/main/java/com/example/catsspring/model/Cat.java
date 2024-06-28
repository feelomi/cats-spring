package com.example.catsspring.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "breed", nullable = false)
    private String breed;
    @Column(name = "description", nullable = false)
    private String description;


    public Cat(Long id, String name, int age, String breed, String description, String photo_url) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.description = description;
    }

    public Cat() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, breed, description);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", description='" + description + '\'' +
                ", photo_url='" +  '\'' +
                '}';
    }
}

