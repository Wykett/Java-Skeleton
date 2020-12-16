package backend.foritech.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@Table(name = "person")
public class PersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personId;

	@NotBlank
	@Column(unique = true, nullable = false)
	@Email
	private String personEmail;

	@NotBlank
	@Column(length = 20)
	private String role;

	private String firstname;

	private String lastname;

	private int age;

	@NotBlank
	private String password;

	private boolean isVerified;

	private String token;

	private Timestamp createdAt = new Timestamp(Calendar.getInstance().getTime().getTime());

	private Timestamp updatedAt;

	private String resetToken;

	private Timestamp resetTokenExpiry;

	@Override
	public boolean equals(Object obj) {
		return this.personEmail.equals(((PersonEntity) obj).personEmail);
	}

	public PersonEntity() {

	}

	public PersonEntity(String personEmail, String password, String role) {
		this.personEmail = personEmail;
		this.password = password;
		this.role = role;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long userId) {
		this.personId = userId;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String userEmail) {
		this.personEmail = userEmail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public Timestamp getResetTokenExpiry() {
		return resetTokenExpiry;
	}

	public void setResetTokenExpiry(Timestamp resetTokenExpiry) {
		this.resetTokenExpiry = resetTokenExpiry;
	}

}
