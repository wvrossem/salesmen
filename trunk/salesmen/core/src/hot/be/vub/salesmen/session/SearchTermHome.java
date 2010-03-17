package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;

import be.vub.salesmen.entity.SearchTerm;

@Name("searchTermHome")
public class SearchTermHome extends EntityHome<SearchTerm>
{
    @RequestParameter Long searchTermId;

    @Override
    public Object getId()
    {
        if (searchTermId == null)
        {
            return super.getId();
        }
        else
        {
            return searchTermId;
        }
    }

    @Override @Begin
    public void create() {
        super.create();
    }

}
