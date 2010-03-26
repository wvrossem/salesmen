package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;

import be.vub.salesmen.entity.UserAccount;

@Name("userAccountHome")
public class UserAccountHome extends EntityHome<UserAccount>
{
	@RequestParameter Long userAccountId;

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
}