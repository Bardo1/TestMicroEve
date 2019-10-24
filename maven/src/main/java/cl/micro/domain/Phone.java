package cl.micro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_PHONES")
public class Phone {
	 
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	 
	 @Column(name="number")
	 private String number;
	 
	 @Column(name="citycode")
	 private String citycode;   
	 
	 @Column(name="countrycode")
	 private String countrycode;
	 
	 @Column(name="owner_id")
	 private Long owner; 

	 public Long getId() {
		return id;
	}



	public Long getOwner() {
		return owner;
	}



	public void setOwner(Long owner) {
		this.owner = owner;
	}



	public void setId(Long id) {
		this.id = id;
	}

	// Getter Methods 
	 public String getNumber() {
	  return number;
	 }

	 public String getCitycode() {
	  return citycode;
	 }

	 public String getCountrycode() {
	  return countrycode;
	 }
	 // Setter Methods
	 public void setNumber(String number) {
	  this.number = number;
	 }

	 public void setCitycode(String citycode) {
	  this.citycode = citycode;
	 }

	 public void setCountrycode(String countrycode) {
	  this.countrycode = countrycode;
	 }



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Phone [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(number);
		builder.append(", citycode=");
		builder.append(citycode);
		builder.append(", countrycode=");
		builder.append(countrycode);
		builder.append(", owner=");
		builder.append(owner);
		builder.append("]");
		return builder.toString();
	}




	}