package org.contacts;

import org.contacts.Contact.Contact;
import org.contacts.Contact.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {
    private Contact person;
    private LocalDateTime timeCreated;

    @BeforeEach
    void setUp() {
        person = new Person();
        timeCreated = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().withNano(0).withSecond(0);
    }

    @Test
    void getCorrectEntityNameTest() {
        person.setField("name", "This is the name");
        person.setField("surname", "This is the surname");
        assertEquals("This is the name This is the surname\n", person.getEntityName());
    }

    @Test
    void fieldsToChangeTest() {
        assertEquals("name, surname, birth, gender, number", person.fieldsToChange());
    }

    @Test
    void setFieldAndGetCorrectInfoTest() {
        person.setField("name", "Jan");
        person.setField("surname", "Kowalski");
        person.setField("gender", "M");
        person.setField("birth", "[no data]");
        person.setField("number", "123 321 x1");
        LocalDateTime currentTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().withNano(0).withSecond(0);
        String personInfo = person.getInfo();
        assertTrue(personInfo.contains("Name: Jan\n"));
        assertTrue(personInfo.contains("Surname: Kowalski\n"));
        assertTrue(personInfo.contains("Gender: M\n"));
        assertTrue(personInfo.contains("Birth date: [no data]\n"));
        assertTrue(personInfo.contains("Number: 123 321 x1\n"));
        assertTrue(personInfo.contains("Time created: " + timeCreated));
        assertTrue(personInfo.contains("Time last edit: " + currentTime));
    }

    @Test
    void setInvalidGenerTest() {
        person.setField("gender", "x");
        assertTrue(person.getInfo().contains("Gender: [no data]\n"));
    }

    @Test
    void setInvalidPhoneTest() {
        person.setField("number", "+ 1 1 123 321 x");
        assertTrue(person.getInfo().contains("Number: [no phone]\n"));
    }
}