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

import be.vub.salesmen.session.BasicSearchBean;

@Scope(CONVERSATION)
@Name("forgotPassword")
public class ForgotPasswordBean implements ForgotPassword, Serializable
{
	private static final long serialVersionUID = -5946808103467846879L;

	public String screenName;
	private User foundUser;
	private UserAccount foundUserAccount;
	private String foundEmail;
	public String password;
	
	@In EntityManager entityManager;

	@In Identity identity;
	
	@In IdentityManager identityManager;
	
	@In EmailService emailService;
	
	@Begin
	public void checkScreenName()
	{
	BasicSearchBean search = new BasicSearchBean();
	//Find the User corresponding to the screenName
	this.foundUser=search.findUser(screenName,entityManager);
	//Find the UserAccount corresponding to the screenName
	this.foundUserAccount=search.findUserAccount(screenName,entityManager);
	
	  
		if ( this.foundUser==null){
			FacesMessages.instance().addToControl("screenNameConfirmation", "Screenname "+this.screenName+" has not been found");

		}
		else{
			//Get Email from User
		    this.foundEmail=foundUser.getEmail();
			//Generate Random Password (length 8)
			this.password=generatePassword(8);
			//Change the password to this random generated password
			new RunAsOperation()
			{
				public void execute()
				{
				identityManager.changePassword(screenName, password);
				}
				
			}.addRole("admin").run();

		    FacesMessages.instance().addToControl("screenNameConfirmation", "We have found your screenname and we have sent your new password to your emailadres");
			// Send email with the new password
			emailService.sendPassword(this.foundEmail, this.screenName, this.password);
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
	
	
    public String getScreenName()
	{
		return screenName;
	}
	
	public void setScreenName(String screenName)
	{
		this.screenName = screenName;
	}
	
}
