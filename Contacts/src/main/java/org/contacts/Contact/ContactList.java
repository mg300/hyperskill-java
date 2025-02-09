package org.contacts.Contact;

import org.contacts.IO.FileHandler;

import java.util.ArrayList;
import java.util.List;

public final class ContactList {
    private final ArrayList<Contact> list;
    private final FileHandler fileHandler;

    public ContactList(final FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        if (fileHandler.fileExists()) {
            list = new ArrayList<>(List.of(fileHandler.readFromFile()));
        } else {
            list = new ArrayList<>();
        }
    }


    public void add(final Contact contact) {
        list.add(contact);
        fileHandler.save(list);
    }

    public void remove(final int row) {
        list.remove(row - 1);
        fileHandler.save(list);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Contact get(final int row) {
        return list.get(row - 1);
    }

    public List<Contact> getList() {
        return new ArrayList<>(list);
    }

    public List<Contact> getList(final String query) {
        return new ArrayList<>(list.stream()
                .filter(contact -> contact.getInfo().toLowerCase().contains(query.toLowerCase()))
                .toList());
    }

}
