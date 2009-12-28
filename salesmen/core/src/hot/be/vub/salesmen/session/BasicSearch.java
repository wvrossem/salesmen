package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface BasicSearch {
	
	public int getPageSize();

	public void setPageSize(int pageSize);

	public String getSearchString();

	public void setSearchString(String searchString);

	public String getSearchPattern();

	public void find();

	public void nextPage();

	public boolean isNextPageAvailable();

	public void destroy();

}