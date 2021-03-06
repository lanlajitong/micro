package com.intuit.craft.demo.user.dto;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class EnduserDTO {
	private long id;
	//@Size(min=2, message = "First Name should have at least 2 characters.")
	private String firstName;
	//@Size(min=2, message = "Last Name should have at least 2 characters.")
	private String lastName;
	//@Past
	private Date birthday;
	
	public EnduserDTO() {}
	public EnduserDTO(String firstName,
			String lastName,
			Date birthday) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "EnduserDTO{" +
				"id='" + id + "'" +
				", firstName='" + firstName + "'" +
				", lastName='" + lastName + "'" +
				", birthday='" + birthday + "'" +
				"}";
	}
}
