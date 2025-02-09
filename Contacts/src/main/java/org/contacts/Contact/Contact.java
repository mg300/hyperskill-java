package org.contacts.Contact;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    protected final LocalDateTime timeCreated;
    protected LocalDateTime lastEdit;
    protected String phone;

    protected Contact() {
        LocalDateTime currentTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.timeCreated = currentTime;
        this.lastEdit = currentTime;
    }

    private void updateTime() {
        lastEdit = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    protected String setPhone(final String phone) {
        updateTime();
        boolean isValid = hasNumber(phone);
        if (!isValid) {
            this.phone = "[no phone]";
            return "Wrong phone number\n";
        } else {
            this.phone = phone;
            return "";
        }
    }

    private boolean hasNumber(final String phone) {
        String regex = "^\\+?([\\da-zA-Z]+[\\s-]?)?(\\([\\da-zA-Z]+(\\)[\\s-]|\\)$))"
                + "?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$";
        return Pattern.compile(regex).matcher(phone).matches();
    }

    public abstract String fieldsToChange();

    public abstract String setField(String field, String value);

    public abstract String getInfo();

    public abstract String getEntityName();
}
