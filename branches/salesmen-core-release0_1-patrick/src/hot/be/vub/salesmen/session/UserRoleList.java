package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import be.vub.salesmen.entity.UserRole;

@Name("userRoleList")
public class UserRoleList extends EntityQuery<UserRole>
{
    public UserRoleList()
    {
        setEjbql("select userRole from UserRole userRole");
    }
}
