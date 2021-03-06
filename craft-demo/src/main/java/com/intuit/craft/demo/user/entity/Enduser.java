package com.intuit.craft.demo.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//@Entity
public class Enduser {
	//@Id
	//@GeneratedValue	
	private long id;
	
	//@Size(min=2, message = "Name should have at least 2 characters.")
	private String name;
	
	//@Past
	private Date birthday;

	public Enduser() {}
	public Enduser(long id, String name,
			Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return String.format("Enduser [id=%s, name=%s, birthday=%s", id, name, birthday);
	}
	
}
