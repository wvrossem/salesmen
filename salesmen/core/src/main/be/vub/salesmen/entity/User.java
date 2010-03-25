package be.vub.salesmen.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import be.vub.salesmen.session.Country;
import org.jboss.seam.annotations.Name;

import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;

@Entity
@Name("user")
@Table(name="Member", uniqueConstraints = @UniqueConstraint(columnNames = "screenname"))
public class User implements Serializable
{
	private static final long serialVersionUID = 8131778475502231881L;

   public enum Gender {
     male, female
   }
  
	private Integer version;
	private Long userId;
	private String screenName;
	private String firstName;
	private String lastName;
	private String email;
  private Gender gender;
  private Date dateOfBirth;
  private Date memberSince;
  private String city;
  private Country country;
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    @NotNull
    @Length(min=3, max=32)
    @Pattern(regex="[a-zA-Z]?[a-zA-Z0-9_]+",
    		message="Usernames must start with a letter, and only contain letters, numbers, and underscores")
	public String getScreenName() 
	{
		return screenName;
	}

	public void setScreenName(String screenName) 
    {
		this.screenName = screenName;
	}

	@NotNull
	@Length(min=3, max=32)
	@Pattern(regex="[a-zA-Z]+",
		message="First names only contain letters")
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
	@Pattern(regex="[a-zA-Z]+(\\s[a-zA-Z]+)*",
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

  @NotNull
	public Gender getGender()
	{
		return gender;
	}

	public void setGender(Gender gender)
	{
		this.gender = gender;
	}

   @NotNull
   public Date getDateOfBirth()
   {
      return dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

   @NotNull
   public Date getMemberSince()
   {
      return memberSince;
   }

   public void setMemberSince(Date memberSince)
   {
      this.memberSince = memberSince;
   }

  @NotNull
  @Length(min = 3, max = 32)
	@Pattern(regex="[a-zA-Z]+(-[a-zA-Z]+)*",
		message="Please enter a valid city name")
	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}
	
	@Version
    public Integer getVersion() {
        return version;
    }

  @Enumerated(EnumType.STRING)
  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country)
  {
    this.country = country;
  }

	@SuppressWarnings("unused")
	private void setVersion(Integer version) {
        this.version = version;
    }
}