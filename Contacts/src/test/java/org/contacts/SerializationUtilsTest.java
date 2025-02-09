package org.contacts;

import org.contacts.Contact.Contact;
import org.contacts.Contact.Organization;
import org.contacts.Utils.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SerializationUtilsTest {

    @Test
    void serializeDeserializeTest() throws IOException, ClassNotFoundException {
        Contact organization = new Organization();
        organization.setField("name", "grid");
        organization.setField("address", "jaworska");
        organization.setField("number", "1231 23");
        SerializationUtils.serialize(new Contact[]{organization}, "fileName");
        Contact[] newContacts = (Contact[]) SerializationUtils.deserialize("fileName");
        assertTrue(newContacts[0].getInfo().contains("name: grid"));
        File file = new File("./fileName");
        if (file.exists()) {
            file.delete();
        }
    }
}