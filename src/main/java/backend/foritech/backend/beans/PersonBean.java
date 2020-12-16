package backend.foritech.backend.beans;

import backend.foritech.backend.entities.PersonEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PersonBean {
    private Long personId;

    @NotBlank
    @Email
    private String personEmail;

    private String firstname;

    private String lastname;

    private int age;

    private String userType;

    public PersonBean() {

    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
