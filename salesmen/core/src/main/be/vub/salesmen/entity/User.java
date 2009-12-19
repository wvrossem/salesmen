package be.vub.salesmen.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.jboss.seam.annotations.Name;

import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;

@Entity
@Name("user")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements Serializable
{

	private static final long serialVersionUID = -5679551708265150079L;
	private Integer version;
	
	private Long userId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	
    @Id @GeneratedValue
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @NotNull
    @Length(min = 3, max = 32)
    @Pattern(regex="[a-zA-Z]?[a-zA-Z0-9_]+",
    	message="Usernames must start with a letter, and only contain letters, numbers, and undescores")
	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
    {
		this.username = username;
	}

	@NotNull
	@Length(min = 3, max = 32)
	@Pattern(regex = "[a-zA-Z]+",
		message = "First names only contain letters")
	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	@NotNull
	@Length(min = 3, max = 32)
	@Pattern(regex = "[a-zA-Z]+", 
		message="Last names must only contain letters")
	public String getLastName()
	{
		return lastName;
	}
	   
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@NotNull @Email
	public String getEmail()
	{
		return email;
	}
	   
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	@Version
    public Integer getVersion() {
        return version;
    }

	@SuppressWarnings("unused")
	private void setVersion(Integer version) {
        this.version = version;
    }
}