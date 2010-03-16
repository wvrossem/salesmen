package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import be.vub.salesmen.entity.Category;

@Name("categoryList")
public class CategoryList extends EntityQuery<Category>
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7600898821103032030L;

	public CategoryList()
    {
        setEjbql("select category from Category category");
    }
}
