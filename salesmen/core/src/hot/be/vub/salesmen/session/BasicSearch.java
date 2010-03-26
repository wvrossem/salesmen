package be.vub.salesmen.session;

import org.richfaces.model.TreeNodeImpl;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BasicSearch {
	
	public int getPageSize();

	public void setPageSize(int pageSize);

	public String getSearchTerm();

	public void setSearchTerm(String searchTerm);

	public String getSearchPattern();

	public String getEntityType();
		
	public void setEntityType(String entityType);

	public void find();
	
	public Object findUser(String screenName);

    public Object findUserAccount(String userName);

    public TreeNodeImpl getCategoryTree();

	public List suggest(Object begin);

	public void nextPage();

	public boolean isNextPageAvailable();

	public boolean entityTypeUser();

	public boolean entityTypeAuction();

	public void destroy();

}