package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;

import be.vub.salesmen.entity.UserRole;

@Name("userRoleHome")
public class UserRoleHome extends EntityHome<UserRole>
{
    @RequestParameter Long userRoleId;

    @Override
    public Object getId()
    {
        if (userRoleId == null)
        {
            return super.getId();
        }
        else
        {
            return userRoleId;
        }
    }

    @Override @Begin
    public void create() {
        super.create();
    }

}
