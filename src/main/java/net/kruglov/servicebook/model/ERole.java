package net.kruglov.servicebook.model;


public enum ERole {

    CLIENT("ROLE_CLIENT"),
    ADMIN("ROLE_ADMIN");

    private String name;

    private ERole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
