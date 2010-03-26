package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import be.vub.salesmen.entity.User;

@Name("userList")
public class UserList extends EntityQuery<User>
{
	public UserList()
	{
		setEjbql("select user from User user");
	}
}