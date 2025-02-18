package net.javaguides.ems_backend.dto;

public class EmployeeDto {
    //Data transfer objects between client and server
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    //constructors
    public EmployeeDto() {
    }

    public EmployeeDto(String email, String lastName, String firstName, Long id) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
    }

    //Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
