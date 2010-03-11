package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.annotations.remoting.WebRemote;

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

	@Out(required = false)
	private List<Category> categories;
	
	@Out(required = false)
	private List<Category> allCategories;
	
	@RequestParameter int categoryId;


    public Object getId()
    {
        if (categoryId == null)
        {
            return super.getId();
        }
        else
        {
            return (Integer)categoryId;
        }
    }

    @Begin(join=true)
    public void create() {
        super.create();
    }

    
    @Factory("categories")
    public void loadCategories()
    {
       categories = entityManager.createQuery(
             "from Category order by name")
             .getResultList();
    }

    @WebRemote
	public List<Category> getAllCategories()
    {
       allCategories = entityManager.createQuery("from Category").getResultList(); 
       return allCategories;
    }
    
}
