package be.vub.salesmen.session;

import be.vub.salesmen.entity.User;
import be.vub.salesmen.entity.UserAccount;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.RunAsOperation;
import org.jboss.seam.security.management.IdentityManager;
import org.jboss.seam.security.management.JpaIdentityStore;

import javax.ejb.Remove;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.jboss.seam.ScopeType.CONVERSATION;

@Scope(CONVERSATION)
@Name("registerUserAccount")
public class RegisterUserAccountBean implements RegisterUserAccount, Serializable
{
	private static final long serialVersionUID = -5946808103467846878L;

	// Private attributes
	private User user;
	private UserAccount newAccount;
	private String username;
	private String password;
	private String passwordConfirmation;
	private boolean usernameValid = false;
  private boolean passwordValid = false;
	
	// In annotations
	@In EntityManager entityManager;
	@In Identity identity;
	@In IdentityManager identityManager;
	@In EmailService emailService;
  @In FacesMessages facesMessages;
  @In BasicSearch basicSearch;  

  @DataModel
	private List entities;
	
	@Begin(join = true)
	public void createUser()
	{
		if(this.user == null)//REQUIRED, otherwise view-fields will be emptied on error message
		{
			user = new User();
		}
	}

	public boolean next()
	{
		return usernameValid && passwordValid;
	}

	@Observer(JpaIdentityStore.EVENT_USER_CREATED)
	public void userAccountCreated(UserAccount account)
	{
		if (user == null)
		{
			user = new User();
			user.setScreenName(account.getUsername());
			user.setFirstName("John");
			user.setLastName("Smith");
			user.setEmail(account.getUsername() + "@nowhere.com");
			user.setGender(User.Gender.male);
			user.setDateOfBirth(new Date());
			user.setMemberSince(new Date());
			user.setCountry(Country.BE);
			user.setCity("Brussels");
			entityManager.persist(user);
		}
		account.setUser(user);
		this.newAccount = account;
	}
	
	@End
	public void createUserAccount()
	{
	  user.setMemberSince(new Date());
		entityManager.persist(user);
		new RunAsOperation()
		{
			public void execute()
			{
				identityManager.createUser(username, password);
				identityManager.grantRole(username, "user");
			}
		}.addRole("admin").run();
		
		newAccount.setUser(user);
    newAccount.setEnabled(false);

    // Generate activation key
    Long randomNumber = new Random().nextLong();
    newAccount.setActivationKey(randomNumber);

		newAccount = entityManager.merge(newAccount);
    
		// Send email
		emailService.sendActivateAccountEmail(newAccount);
	}

  public void verifyUsername()
  {
    UserAccount result  = basicSearch.findUserAccount(username);
    if (result == null)
    {
        facesMessages.addToControlFromResourceBundle("username", FacesMessage.SEVERITY_INFO, "salesmen.UserAccount.Username.Unique");
        usernameValid = true;
    }
    else
    {
        facesMessages.addToControlFromResourceBundle("username", FacesMessage.SEVERITY_ERROR, "salesmen.UserAccount.Username.AlreadyExists");
        usernameValid = false;
    }
  }

  public void verifyPassword()
	{
		passwordValid = (passwordConfirmation != null && passwordConfirmation.equals(password));
		if (!passwordValid)
		{
			facesMessages.addToControlFromResourceBundle("passwordConfirmation", FacesMessage.SEVERITY_ERROR, "salesmen.UserAccount.Password.NoMatch");
		}
        else 
        {
            facesMessages.addToControlFromResourceBundle("passwordConfirmation", FacesMessage.SEVERITY_INFO, "salesmen.UserAccount.Password.Match");
        }
	}
		
	@Destroy @Remove
	public void destroy() {}

	// Public Attribute getters/setters with annotations 
	public User getUser()
	{
		return user;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
		
	public String getPassword()
	{
		return password;
	}
		
	public void setPassword(String password)
	{
		this.password = password;
	}
		
	public String getPasswordConfirmation()
	{
		return passwordConfirmation;
	}
		
	public void setPasswordConfirmation(String passwordConfirmation)
	{
		this.passwordConfirmation = passwordConfirmation;
	}
}