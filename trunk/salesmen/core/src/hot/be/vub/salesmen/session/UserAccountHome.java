package be.vub.salesmen.session;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.RunAsOperation;
import org.jboss.seam.security.management.IdentityManager;
import org.jboss.seam.security.management.JpaIdentityStore;

import be.vub.salesmen.entity.UserAccount;

@Name("userAccountHome")
public class UserAccountHome extends EntityHome<UserAccount>
{
	@RequestParameter Long userAccountId;
	
	private String newPassword;
	private String newPasswordConfirmation;
	private boolean passwordValid;
	
	@In FacesMessages facesMessages;
	@In IdentityManager identityManager;

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
	
	@Override
	public String update()
	{
		if (passwordValid)
		{
			new RunAsOperation()
			{
				public void execute()
				{
					identityManager.changePassword(getInstance().getUsername(), newPassword);
				}
			}.addRole("admin").run();
			return super.update();
		} else
		{
			return "Password not valid";
		}
	}

	@Override @Begin
	public void create()
	{
		super.create();
	}
	
	public void verifyPassword()
	{
		passwordValid = (newPasswordConfirmation != null && newPasswordConfirmation.equals(newPassword));
		if (!passwordValid)
		{
			facesMessages.addToControl("newPasswordConfirmation", "Passwords do not match");
		}
	}
	
	public String getNewPasswordConfirmation()
	{
		return newPasswordConfirmation;
	}
	
	public void setNewPasswordConfirmation(String newPasswordConfirmation)
	{
		this.newPasswordConfirmation = newPasswordConfirmation;
	}
	
	public String getNewPassword()
	{
		return newPassword;
	}
	
	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}
}