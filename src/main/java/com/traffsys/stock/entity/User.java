package com.traffsys.stock.entity;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;



@Entity 
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String password;
     
  
    @Column(unique = true)
    private String userName;
    
    
    private String userContact;
    
    @Column(name = "email")
    private String Useremail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	

	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUseremail() {
		return Useremail;
	}

	public void setUseremail(String useremail) {
		Useremail = useremail;
	}

	public User(Long id, String firstName, String lastName, String password, String userName, String userContact,
			String useremail) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userName = userName;
		this.userContact = userContact;
		Useremail = useremail;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", userName=" + userName + ", userContact=" + userContact + ", Useremail=" + Useremail + "]";
	}

	

 
    
    
    
    
    
    
    
    
    
    
    
   // @PrePersist   //data save
  //  private void prepersist() {
  //  	this.password=new BCrypt
  //   PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
  //  }
}

