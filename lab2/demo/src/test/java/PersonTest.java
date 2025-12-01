import org.junit.jupiter.api.Test;

import com.example.Person;

import nl.jqno.equalsverifier.EqualsVerifier;

class PersonTest {
    @Test
    public void testEqualsAndHashCodeContract() {
        System.out.println("Запускаємо перевірку контракту equals/hashCode для класу Person...");
       
        EqualsVerifier.forClass(Person.class).verify();

        System.out.println("Перевірка успішно пройдена! Контракт реалізовано правильно.");
    }
}