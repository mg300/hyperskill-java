package org.contacts;

import org.contacts.Contact.Contact;
import org.contacts.Contact.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrganizationTest {
    private Contact organization;
    private LocalDateTime timeCreated;

    @BeforeEach
    void setUp() {
        organization = new Organization();
        timeCreated = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().withNano(0).withSecond(0);
    }


    @Test
    void getCorrectEntityNameTest() {
        organization.setField("name", "This is the name");
        assertEquals("This is the name\n", organization.getEntityName());

    }

    @Test
    void fieldsToChangeTest() {
        assertEquals("name, address, number", organization.fieldsToChange());
    }

    @Test
    void setFieldAndGetCorrectInfoTest() {
        organization.setField("name", "This is the name");
        organization.setField("address", "This is the address");
        organization.setField("number", "123 321 x1");
        assertEquals("wrong field", organization.setField("numbedr", "123 321 x1"));
        LocalDateTime currentTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().withNano(0).withSecond(0);
        String organizationInfo = organization.getInfo();
        assertTrue(organizationInfo.contains("Organization name: This is the name\n"));
        assertTrue(organizationInfo.contains("Address: This is the address\n"));
        assertTrue(organizationInfo.contains("Number: 123 321 x1\n"));
        assertTrue(organizationInfo.contains("Time created: " + timeCreated));
        assertTrue(organizationInfo.contains("Time last edit: " + currentTime));
    }

    @Test
    void setInvalidPhoneTest() {
        organization.setField("number", "+ 1 1 123 321 x");
        assertTrue(organization.getInfo().contains("Number: [no phone]\n"));
    }


}