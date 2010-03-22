package be.vub.salesmen.session;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Remove;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.RunAsOperation;
import org.jboss.seam.security.management.IdentityManager;
import org.jboss.seam.security.management.JpaIdentityStore;

import be.vub.salesmen.entity.User;
import be.vub.salesmen.entity.UserAccount;

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
	private boolean passwordVerified;
	
	// In annotations
	@In EntityManager entityManager;
	@In Identity identity;
	@In IdentityManager identityManager;
	
	@Begin
	public void createUser()
	{
		user = new User();
	}

	public void verifyPassword()
	{
		passwordVerified = (passwordConfirmation != null && passwordConfirmation.equals(password));
		if (!passwordVerified)
		{
			FacesMessages.instance().addToControl("passwordConfirmation", "Passwords do not match");
		}
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
		newAccount = entityManager.merge(newAccount);
		
		// Automatically, sign the user in
		identity.getCredentials().setUsername(username);
		identity.getCredentials().setPassword(password);
		identity.login();
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
		
	public boolean isPasswordVerified()
	{
		return passwordVerified;
	}
}