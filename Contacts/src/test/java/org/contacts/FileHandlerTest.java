package org.contacts;

import org.contacts.Contact.Contact;
import org.contacts.Contact.Person;
import org.contacts.IO.FileHandler;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class FileHandlerTest {
    FileHandler fileHandler;

    @Test
    void ReadSaveTest() {
        fileHandler = new FileHandler("fileName");
        fileHandler.save(List.of(new Person()));
        File file = new File("./fileName");
        assertTrue(file.exists());
        assertTrue(fileHandler.fileExists());
        Contact[] contactList = fileHandler.readFromFile();
        Contact contact = contactList[0];
        assertNotNull(contact);
        file.delete();
    }

    @Test
    void emptyFileNameTest() {
        fileHandler = new FileHandler("");
        fileHandler.save(List.of(new Person()));
        File file = new File("./fileName");
        assertFalse(file.exists());
    }

    @Test
    void readFromFileNotPresentTest() {
        fileHandler = new FileHandler("x");
        Contact[] contacts = fileHandler.readFromFile();
        assertNull(contacts);
    }
}