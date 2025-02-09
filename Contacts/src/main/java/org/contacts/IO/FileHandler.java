package org.contacts.IO;

import org.contacts.Contact.Contact;
import org.contacts.Utils.SerializationUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class FileHandler {
    private String fileName;

    public FileHandler(final String fileName) {
        this.fileName = fileName;
    }

    public void save(final List<Contact> list) {
        if (Objects.equals(fileName, "")) {
            return;
        }
        try {
            SerializationUtils.serialize(list.toArray(new Contact[0]), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Contact[] readFromFile() {
        try {
            return (Contact[]) SerializationUtils.deserialize(fileName);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    public boolean fileExists() {
        return new File(fileName).exists();
    }
}
