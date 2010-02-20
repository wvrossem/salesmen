package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;

import be.vub.salesmen.entity.Category;

/*
 * 
 * Extra imports
 */
import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;


@Name("categoryHome")
public class CategoryHome extends EntityHome<Category>
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -468312262064764801L;
	@In
	EntityManager entityManager;
	
	@SuppressWarnings("unused")
	@Out(required = false)
	private List<Category> categories;
	
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
    
    @SuppressWarnings("unchecked")
    @Factory("categories")
    public void loadCategories()
    {
       categories = entityManager.createQuery(
             "from Category order by name")
             .getResultList();
    }

}
