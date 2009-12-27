package be.vub.salesmen.session;

import java.io.Serializable;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import be.vub.salesmen.entity.User;
import be.vub.salesmen.entity.UserAccount;

import static org.jboss.seam.ScopeType.CONVERSATION;


@Scope(CONVERSATION)
@Name("RegisterUserAccount")
public class RegisterUserAccountBean implements RegisterUserAccount, Serializable
{
	
	private static final long serialVersionUID = -4349512217411197622L;
	
	   @In
	   EntityManager entityManager;

	   @Out
	   private UserAccount newAccount;
	   
	   
	   
	   @Begin()
	   public void newRegistration()
	   {
	      if (newAccount == null)
	      {
	    	 newAccount = new UserAccount();
	    	 newAccount.setUser(new User());
	      }
	   }

	   
	   
	   @End()
	   public String register()
	   {
		
		   newAccount.getUser().setUsername(newAccount.getUsername());
		   newAccount.getUser().setFirstName("Test");
		   newAccount.getUser().setLastName("Test2");
		   
		   newAccount.setPasswordHash("test");
		   newAccount.setPasswordSalt("test");

		   entityManager.persist(newAccount.getUser());
		   entityManager.persist(newAccount);
		   return "success";

	   }

}
