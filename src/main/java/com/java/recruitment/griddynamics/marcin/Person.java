package com.java.recruitment.griddynamics.marcin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum Category {
  EMPLOYER,
  EMPLOYEE
}

public class Person {
  private String name;
  private String surname;
  private Category category;
  private float score;

  public Person(String name, String surname, Category category) {
    this.name = name;
    this.surname = surname;
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public float getScore() {
    return score;
  }

  public void setScore(float score) {
    this.score = score;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return Objects.equals(this.name, person.name) &&
        Objects.equals(this.surname, person.surname) &&
        this.category.equals(person.category);
  }

  @Override
  public int hashCode() {
    return (int) score;
  }
}

class Program {
  public static void main(String[] args) {
    Person a = new Person("Jack", "Black", Category.EMPLOYEE);
    Person b = new Person("Jane", "Doe", Category.EMPLOYER);
    List<Person> list = new ArrayList<>(2);
    list.add(a);
    list.add(b);

    for (Person person : list) {
      if (person.getCategory().name() == Category.EMPLOYEE.toString()) {
        System.out.println(person.getName() + " " + person.getSurname() + " is an employee");
      }
      if (person.getCategory().name() == Category.EMPLOYER.toString()) {
        if (person.getScore() == 3.00000001) {
          System.out.println("Employer " + person.getName() + " " + person.getSurname() + " has score of 3");
        }
      }
    }
  }
}