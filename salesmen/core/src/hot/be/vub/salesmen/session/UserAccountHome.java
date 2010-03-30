package be.vub.salesmen.session;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.faces.FacesMessages;

import be.vub.salesmen.entity.UserAccount;

@Name("userAccountHome")
public class UserAccountHome extends EntityHome<UserAccount>
{
	@RequestParameter Long userAccountId;
	
	private String passwordConfirmation;
	private boolean passwordVerified;
	
	@In FacesMessages facesMessages;

	@Override
	public Object getId()
	{
		if (userAccountId == null)
		{
				return super.getId();
		}
		else
		{
				return userAccountId;
		}
	}

	@Override @Begin
	public void create()
	{
		super.create();
	}
	
	public void verifyPassword()
	{
		passwordVerified = (passwordConfirmation != null && passwordConfirmation.equals(passwordConfirmation));
		if (!passwordVerified)
		{
			facesMessages.addToControl("passwordConfirmation", "Passwords do not match");
		}
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