package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;

import be.vub.salesmen.entity.Category;

@Name("categoryHome")
public class CategoryHome extends EntityHome<Category>
{
    @RequestParameter Long categoryId;

    @Override
    public Object getId()
    {
        if (categoryId == null)
        {
            return super.getId();
        }
        else
        {
            return categoryId;
        }
    }

    @Override @Begin
    public void create() {
        super.create();
    }

}
