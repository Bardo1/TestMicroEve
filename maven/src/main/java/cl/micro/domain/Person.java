package cl.micro.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TBL_PERSONS")
public class Person {
	

     @Id
     @Column(name="id")
     @GeneratedValue(strategy= GenerationType.IDENTITY)
     private Long id;
	 
     @Column(name="name")
     private String name;
     
     @Column(name="email", nullable=false, length=200)
	 private String email;
     
     @Column(name="password")
	 private String password;
     
     @Column(name="token")
	 private String token;
     
     @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
     @JoinColumn(name="owner_id")
     private List<Phone> phones;
	 

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public String getToken() {
   	  return token;
   	 }

	public void setToken(String token) {
   	  this.token = token;
   	 }     
	 // Getter Methods 
	 public Long getId() {
	  return id;
	 }

	 public String getName() {
	  return name;
	 }

	 public String getEmail() {
	  return email;
	 }

	 public String getPassword() {
	  return password;
	 }

	 // Setter Methods 
	 public void setId(Long id) {
		  this.id = id;
	 }
	 
	 public void setName(String name) {
	  this.name = name;
	 }

	 public void setEmail(String email) {
	  this.email = email;
	 }

	 public void setPassword(String password) {
	  this.password = password;
	 }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", token=");
		builder.append(token);
		builder.append(", phones=");
		builder.append(phones);
		builder.append("]");
		return builder.toString();
	}

	 
	}

