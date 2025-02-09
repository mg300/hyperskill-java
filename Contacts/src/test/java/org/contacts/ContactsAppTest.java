package org.contacts;

import org.contacts.Contact.ContactList;
import org.contacts.Contact.Organization;
import org.contacts.IO.ConsoleManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ContactsAppTest {

    @Mock
    private ConsoleManager ioManager;

    @Mock
    private ContactList contacts;

    private ContactsApp contactsApp;

    @BeforeEach
    public void setUp() {
        ioManager = mock(ConsoleManager.class);
        contactsApp = new ContactsApp(ioManager, contacts);
    }

    @Test
    void addPersonTest() {
        when(ioManager.readLine()).thenReturn("add", "person", "Jan", "Kowalski", "x", "M", "123 123123", "exit");
        contactsApp.start();
        verify(ioManager, times(2)).printText("[menu] Enter action (add, list, search, count, exit): ");
        verify(ioManager).printText("Enter the name: ");
        verify(ioManager).printText("Enter the surname: ");
        verify(ioManager).printText("Enter the birth date: ");
        verify(ioManager).printText("Enter the gender (M,F): ");
        verify(ioManager).printText("Enter the number: ");
        verify(ioManager).printText("The record added.\n");
    }

    @Test
    void addOrganizationTest() {
        when(ioManager.readLine()).thenReturn("add", "organization", "Grid", "Jaworska", "123 123123", "exit");
        contactsApp.start();
        verify(ioManager, times(2)).printText("[menu] Enter action (add, list, search, count, exit): ");
        verify(ioManager).printText("Enter the name: ");
        verify(ioManager).printText("Enter the address: ");
        verify(ioManager).printText("Enter the number: ");
        verify(ioManager).printText("The record added.\n");
    }

    @Test
    void displayEmptyListTest() {
        when(ioManager.readLine()).thenReturn("list", "exit");
        when(contacts.isEmpty()).thenReturn(true);
        contactsApp.start();
        verify(contacts).isEmpty();
        verify(ioManager).printText("No records to show!\n");
    }

    @Test
    void displayListProvideWrongIndex() {
        when(ioManager.readLine()).thenReturn("list", "2", "exit");
        when(contacts.isEmpty()).thenReturn(false);
        contactsApp.start();
        verify(ioManager).printText("Wrong index\n");
    }

    @Test
    void displayListProvideCharIndex() {
        when(ioManager.readLine()).thenReturn("list", "x", "exit");
        when(contacts.isEmpty()).thenReturn(false);
        contactsApp.start();
        verify(ioManager, never()).printText("Wrong index\n");
        verify(ioManager, never()).printText("No records to show!\n");
        verify(ioManager).printText("This is not a number\n");
    }

    @Test
    void displayListShowRecordThenEditRecordAndRemoveTest() {
        when(ioManager.readLine()).thenReturn("list", "1", "edit", "name", "Nokia", "delete", "exit");
        when(contacts.isEmpty()).thenReturn(false);
        Organization organization = getOrganization();
        when(contacts.get(1)).thenReturn(organization);
        when(contacts.size()).thenReturn(1);
        contactsApp.start();
        verify(ioManager).printText(organization.getInfo());
        verify(ioManager, times(2)).printText("\n[record] Enter action (edit, delete, menu): ");
        verify(ioManager).printText("Select a field (name, address, number): ");
        verify(ioManager).printText("Enter name: ");
        verify(contacts).remove(1);
    }

    @Test
    void searchFindAndOpenAdditionalMenuTest() {
        when(ioManager.readLine()).thenReturn("search", "grid", "back", "exit");
        when(contacts.isEmpty()).thenReturn(false);
        Organization organization = getOrganization();
        when(contacts.getList("grid")).thenReturn(List.of(organization));
        contactsApp.start();
        verify(ioManager).printText("Enter search query: ");
        verify(ioManager).printText("Found 1 results:\n");
        verify(ioManager).printText("\n[search] Enter action ([number], back, again): ");
    }

    @Test
    void searchAgainAndAgain() {
        when(ioManager.readLine()).thenReturn("search", "grid", "again", "rid", "exit");
        when(contacts.isEmpty()).thenReturn(false);
        Organization organization = getOrganization();
        when(contacts.getList("grid")).thenReturn(List.of(organization));
        contactsApp.start();
        verify(ioManager, times(2)).printText("\n[search] Enter action ([number], back, again): ");
    }

    @Test
    void afterSearchOpenEditMenu() {
        when(ioManager.readLine()).thenReturn("search", "grid", "1", "exit");
        when(contacts.isEmpty()).thenReturn(false);
        Organization organization = getOrganization();
        when(contacts.getList("grid")).thenReturn(List.of(organization));
        contactsApp.start();
        verify(ioManager).printText("\n[record] Enter action (edit, delete, menu): ");
    }

    @Test
    void displayCount() {
        when(ioManager.readLine()).thenReturn("count", "exit");

        when(contacts.size()).thenReturn(2);
        contactsApp.start();
        verify(ioManager).printText("The Phone Book has 2 records.");
    }


    private Organization getOrganization() {
        Organization organization = new Organization();
        organization.setField("name", "grid");
        organization.setField("address", "jaworska");
        organization.setField("number", "1231 23");
        return organization;
    }
}