package org.contacts;

import org.contacts.Contact.Contact;
import org.contacts.Contact.ContactList;
import org.contacts.Contact.Organization;
import org.contacts.Contact.Person;
import org.contacts.IO.FileHandler;
import org.contacts.IO.IOManager;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class ContactsApp {
    private final IOManager ioManager;
    private final ContactList contacts;
    private static final String ADD = "add";
    private static final String LIST = "list";
    private static final String SEARCH = "search";
    private static final String COUNT = "count";
    private static final String EXIT = "exit";
    private static final String EDIT = "edit";
    private static final String BACK = "back";
    private static final String AGAIN = "again";
    private static final String DELETE = "delete";

    protected ContactsApp(final IOManager ioManager, final ContactList contacts) {
        this.contacts = contacts;
        this.ioManager = ioManager;
    }

    public ContactsApp(final IOManager ioManager, final String argument) {
        this.contacts = new ContactList(new FileHandler(argument));
        this.ioManager = ioManager;
    }

    public void start() {
        while (true) {
            ioManager.printText(MenuCommand.MENU.toString());
            String action = ioManager.readLine();
            switch (action) {
                case ADD -> {
                    add();
                }
                case LIST -> {
                    list();
                }
                case SEARCH -> {
                    search();
                }
                case COUNT -> {
                    ioManager.printText("The Phone Book has " + contacts.size() + " records.");
                }
                case EXIT -> {
                    return;
                }
                default -> ioManager.printText(MenuCommand.INCORRECT_OPTION.toString());
            }
            ioManager.printText("\n");
        }
    }

    private void add() {
        ioManager.printText(MenuCommand.TYPE.toString());
        String type = ioManager.readLine();
        Contact contact;
        if (Objects.equals(type, "person")) {
            contact = new Person();
        } else if (Objects.equals(type, "organization")) {
            contact = new Organization();
        } else {
            return;
        }
        String[] fields = contact.fieldsToChange().split(", ");
        for (String field : fields) {
            if (Objects.equals(field, "birth")) {
                ioManager.printText("Enter the " + field + " date: ");
            } else if (Objects.equals(field, "gender")) {
                ioManager.printText("Enter the " + field + " (M,F): ");
            } else {
                ioManager.printText("Enter the " + field + ": ");
            }
            String input = ioManager.readLine();
            String result = contact.setField(field, input);
            ioManager.printText(result);
        }
        contacts.add(contact);
        ioManager.printText(MenuCommand.RECORD_ADDED.toString());
    }

    private void edit(final int selectedIndex) {
        Contact contact = contacts.get(selectedIndex);
        String[] fieldCanBeChanged = contact.fieldsToChange().trim().split(", ");
        ioManager.printText("Select a field (" + contact.fieldsToChange() + "): ");
        String chosenField = ioManager.readLine();
        if (!Arrays.asList(fieldCanBeChanged).contains(chosenField)) {
            ioManager.printText("Wrong field!\n");

        } else {
            ioManager.printText("Enter " + chosenField + ": ");
            String input = ioManager.readLine();
            String result = contact.setField(chosenField, input);
            ioManager.printText(result);
        }
        ioManager.printText("Saved\n");
        ioManager.printText(contact.getInfo());
        editMenu(selectedIndex);
    }

    private void list() {
        if (contacts.isEmpty()) {
            ioManager.printText(MenuCommand.NO_RECORD_TO_SHOW.toString());
            return;
        }
        int index = 0;
        for (Contact contact : contacts.getList()) {
            index++;
            ioManager.printText(index + ".  " + contact.getEntityName());
        }
        ioManager.printText(MenuCommand.ACTION_LIST.toString());
        String option = ioManager.readLine();
        if (!option.matches("^[1-9]\\d*")) {
            ioManager.printText(MenuCommand.NOT_NUMBER.toString());

            return;
        }
        int selectedIndex = Integer.parseInt(option);
        if (selectedIndex < 1 || selectedIndex > contacts.size()) {
            ioManager.printText(MenuCommand.INCORRECT_INDEX.toString());
            return;
        }
        Contact contact = contacts.get(selectedIndex);
        ioManager.printText(contact.getInfo());
        editMenu(selectedIndex);

    }

    private void editMenu(final int selectedIndex) {
        ioManager.printText(MenuCommand.ACTION_EDIT.toString());
        String option = ioManager.readLine();
        switch (option) {
            case EDIT -> edit(selectedIndex);
            case DELETE -> contacts.remove(selectedIndex);
            default -> {
            }
        }
    }

    private void search() {
        if (contacts.isEmpty()) {
            ioManager.printText(MenuCommand.NO_RECORD_TO_SEARCH.toString());
            return;
        }
        ioManager.printText(MenuCommand.PROMPT_QUERY.toString());
        String query = ioManager.readLine();
        List<Contact> result = contacts.getList(query);
        int index = 0;
        ioManager.printText("Found " + result.size() + " results:\n");
        for (Contact contact : result) {
            index++;
            ioManager.printText(index + ". " + contact.getEntityName());
        }
        ioManager.printText(MenuCommand.ACTION_AGAIN.toString());
        String option = ioManager.readLine();
        switch (option) {
            case BACK -> {
            }
            case AGAIN -> {
                search();
            }
            default -> {
                if (!option.matches("^[1-9]\\d*")) {
                    return;
                }
                int selectedIndex = Integer.parseInt(option);
                ioManager.printText("" + result.size());
                if (selectedIndex < 1 || selectedIndex > result.size()) {
                    ioManager.printText(MenuCommand.INCORRECT_INDEX.toString());
                    return;
                }
                Contact contact = result.get(selectedIndex - 1);
                ioManager.printText(contact.getInfo());
                editMenu(selectedIndex);
            }
        }

    }
}


