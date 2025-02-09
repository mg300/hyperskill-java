package org.contacts;

import org.contacts.Contact.Contact;
import org.contacts.Contact.ContactList;
import org.contacts.Contact.Organization;
import org.contacts.IO.FileHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactListTest {

    @BeforeEach
    void setUp() {
        fileHandlerMock = Mockito.mock(FileHandler.class);
    }

    @Mock
    FileHandler fileHandlerMock;

    @InjectMocks
    private ContactList contactList;

    @Test
    void readFromFileTest() {
        when(fileHandlerMock.fileExists()).thenReturn(true);
        when(fileHandlerMock.readFromFile()).thenReturn(new Contact[]{createContact()});
        contactList = new ContactList(fileHandlerMock);
        assertEquals(1, contactList.size());
    }

    @Test
    void addRemoveContactTest() {
        Contact contact = createContact();
        contactList.add(contact);
        assertFalse(contactList.isEmpty(), "List should not be empty");
        assertEquals(1, contactList.size(), "List should have one element");
        contact = createSecContact();
        contactList.add(contact);
        assertEquals(contact.getInfo(), contactList.getList().get(1).getInfo(), "Contact should be added on the end of the list");
        List<Contact> searchList = contactList.getList("okia");
        assertEquals(contact.getInfo(), searchList.get(0).getInfo(), "List should contain searching element");
        assertEquals(1, searchList.size(), "List should contain only one element");
        contactList.remove(1);
        assertEquals(contact.getInfo(), contactList.get(1).getInfo(), "Should be removed correct element");
        contactList.remove(1);
        assertTrue(contactList.isEmpty(), "List should be empty");


    }

    private Contact createContact() {
        Contact contact = new Organization();
        contact.setField("name", "Grid");
        contact.setField("address", "Jaworska");
        contact.setField("number", "123 123 123");
        return contact;
    }

    private Contact createSecContact() {
        Contact contact = new Organization();
        contact.setField("name", "Nokia");
        contact.setField("address", "Wrocławska");
        contact.setField("number", "321 321 321");
        return contact;
    }

}