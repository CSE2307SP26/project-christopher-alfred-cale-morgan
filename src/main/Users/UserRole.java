package main.Users;

public enum UserRole {
    Customer("Customer (Standard)"),
    Administrator("Staff (Admin)");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
