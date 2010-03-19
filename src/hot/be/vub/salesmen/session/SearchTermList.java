package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import be.vub.salesmen.entity.SearchTerm;

@Name("searchTermList")
public class SearchTermList extends EntityQuery<SearchTerm>
{
	public SearchTermList()
	{
		setEjbql("select searchTerm from SearchTerm searchTerm");
	}
}