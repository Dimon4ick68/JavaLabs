package com.example;

import java.util.Objects;

public final class  Person {
    private  String lastName;
    private  String firstName;
    private  int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }


@Override
public boolean equals(Object o) {
    // 1. Швидка перевірка: чи це не один і той самий об'єкт?
    if (this == o) return true;

    // 2. КЛЮЧОВИЙ РЯДОК: Перевірка на null та точне збігання класів.
    if (o == null || getClass() != o.getClass()) return false;

    // 3. Приводимо тип, оскільки ми вже знаємо, що це Person
    Person person = (Person) o;

    // 4. Порівнюємо всі значущі поля:
    return age == person.age &&
            Objects.equals(lastName, person.lastName) &&
            Objects.equals(firstName, person.firstName);
}


    @Override
    public int hashCode() {

        return Objects.hash(lastName, firstName, age);
    }
    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }
}