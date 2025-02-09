package org.contacts.Contact;

public final class Organization extends Contact {
    private String name;
    private String address;


    @Override
    public String getInfo() {
        return "Organization name: " + name + "\n"
                + "Address: " + address + "\n"
                + "Number: " + phone + "\n"
                + "Time created: " + timeCreated + "\n"
                + "Time last edit: " + lastEdit + "\n";
    }

    @Override
    public String getEntityName() {
        return name + "\n";
    }

    @Override
    public String fieldsToChange() {
        return "name, address, number";
    }

    @Override
    public String setField(final String field, final String value) {
        switch (field) {
            case "name" -> {
                name = value;
                return "";
            }
            case "address" -> {
                address = value;
                return "";
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
