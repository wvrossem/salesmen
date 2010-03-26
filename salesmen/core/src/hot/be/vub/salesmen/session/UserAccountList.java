package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import be.vub.salesmen.entity.UserAccount;

@Name("userAccountList")
public class UserAccountList extends EntityQuery<UserAccount>
{
	public UserAccountList()
	{
		setEjbql("select userAccount from UserAccount userAccount");
	}
}