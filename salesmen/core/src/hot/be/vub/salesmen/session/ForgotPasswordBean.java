package be.vub.salesmen.session;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Remove;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.FacesMessages;
import javax.faces.application.FacesMessage;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.RunAsOperation;
import org.jboss.seam.security.management.IdentityManager;
import org.jboss.seam.security.management.JpaIdentityStore;

import be.vub.salesmen.entity.User;
import be.vub.salesmen.entity.UserAccount;

import be.vub.salesmen.session.BasicSearchBean;

@Scope(CONVERSATION)
@Name("forgotPassword")
public class ForgotPasswordBean implements ForgotPassword, Serializable
{
	private static final long serialVersionUID = -5946808103467846879L;

	public String username;
	private UserAccount foundUserAccount;
	private String foundEmail;
	public String password;
	
	@In EntityManager entityManager;

	@In Identity identity;
	
	@In IdentityManager identityManager;
	
	@In EmailService emailService;
	
	@In FacesMessages facesMessages;

  @In BasicSearch basicSearch;
	
	@Begin(join=true)
	public void checkUsername()
	{
	//Find the UserAccount corresponding to the username
	this.foundUserAccount=basicSearch.findUserAccount(username);
	  
		if ( this.foundUserAccount==null){
			facesMessages.addToControlFromResourceBundle("MailSent", FacesMessage.SEVERITY_ERROR, "salesmen.ForgotPassword.UsernameNotFoundLong");			

		}
		else{
			//Get Email from User
		    this.foundEmail=foundUserAccount.getUser().getEmail();
			//Generate Random Password (length 8)
			this.password=generatePassword(8);
			//Change the password to this random generated password
			new RunAsOperation()
			{
				public void execute()
				{
				identityManager.changePassword(username, password);
				}

			}.addRole("admin").run();
			facesMessages.addToControlFromResourceBundle("MailSent",  FacesMessage.SEVERITY_INFO, "salesmen.ForgotPassword.MailSent");
			// Send email with the new password
			emailService.sendPassword(this.foundUserAccount, this.password);
		}
	}
	
	 public void verifyUsername()
	{
		BasicSearchBean searchUser = new BasicSearchBean();
		UserAccount result  = searchUser.findUserAccount(username, entityManager);
			if (result != null) 
		{
		  facesMessages.addToControlFromResourceBundle("username", FacesMessage.SEVERITY_INFO, "salesmen.ForgotPassword.UsernameFound");
		}
		else{
		  facesMessages.addToControlFromResourceBundle("username", FacesMessage.SEVERITY_ERROR, "salesmen.ForgotPassword.UsernameNotFound");
		}
	}

   public static String generatePassword(int n)
   {

    char[] pw = new char[n];
    int c  = 'A';
    int  r1 = 0;
    for (int i=0; i < n; i++)
    {
      r1 = (int)(Math.random() * 3);
      switch(r1) {
        case 0: c = '0' +  (int)(Math.random() * 10); break;
        case 1: c = 'a' +  (int)(Math.random() * 26); break;
        case 2: c = 'A' +  (int)(Math.random() * 26); break;
      }
      pw[i] = (char)c;
    }
    return new String(pw);
	}
	

   

	@Destroy @Remove
	public void destroy() {}
	
	
    public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
}
