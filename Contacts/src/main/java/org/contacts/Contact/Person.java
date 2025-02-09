package org.contacts.Contact;

import java.io.Serializable;
import java.util.Objects;

public final class Person extends Contact implements Serializable {
    private String name;
    private String surname;
    private String birthDate;
    private String gender;
    private static final long serialVersionUID = 2L;

    public Person() {
        super();
    }

    @Override
    public String getInfo() {
        return "Name: " + name + "\n"
                + "Surname: " + surname + "\n"
                + "Birth date: " + birthDate + "\n"
                + "Gender: " + gender + "\n"
                + "Number: " + phone + "\n"
                + "Time created: " + timeCreated + "\n"
                + "Time last edit: " + lastEdit + "\n";
    }

    @Override
    public String getEntityName() {
        return name + " " + surname + "\n";
    }

    public String setBirthDate(final String birthDate) {
        this.birthDate = "[no data]";
        return "Bad birth date\n";
    }

    public String setGender(final String gender) {
        boolean isValid = Objects.equals(gender, "M") || Objects.equals(gender, "F");
        if (!isValid) {
            this.gender = "[no data]";
            return "Bad gender\n";
        } else {
            this.gender = gender;
            return "";
        }
    }


    public void setName(final String name) {
        this.name = name;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    @Override
    public String fieldsToChange() {
        return "name, surname, birth, gender, number";
    }

    @Override
    public String setField(final String field, final String value) {
        switch (field) {
            case "name" -> {
                setName(value);
                return "";
            }
            case "surname" -> {
                setSurname(value);
                return "";
            }
            case "birth" -> {
                return setBirthDate(value) + "\n";
            }
            case "gender" -> {
                return setGender(value) + "\n";
            }
            case "number" -> {
                return setPhone(value) + "\n";
            }
            default -> {
                return "wrong field";
            }
        }
    }
}
