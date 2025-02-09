package org.contacts;

public enum MenuCommand {
    MENU("[menu] Enter action (add, list, search, count, exit): "),
    INCORRECT_OPTION("Incorrect option\n"),
    INCORRECT_INDEX("Wrong index\n"),
    RECORD_ADDED("The record added.\n"),
    NOT_NUMBER("This is not a number\n"),
    NO_RECORD_TO_SHOW("No records to show!\n"),
    NO_RECORD_TO_SEARCH("No records to search!\n"),
    PROMPT_QUERY("Enter search query: "),
    TYPE("Enter the type (person, organization): "),
    ACTION_LIST("\n[list] Enter action ([number], back): "),
    ACTION_EDIT("\n[record] Enter action (edit, delete, menu): "),
    ACTION_AGAIN("\n[search] Enter action ([number], back, again): ");

    private final String option;

    MenuCommand(final String option) {
        this.option = option;
    }

    public String toString() {
        return option;
    }
}
