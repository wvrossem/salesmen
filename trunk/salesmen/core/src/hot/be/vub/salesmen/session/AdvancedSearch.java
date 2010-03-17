package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface AdvancedSearch {
    public String getIncludeTerm();

    public void setIncludeTerm(String term);

    public String getExcludeTerm();

    public void setExcludeTerm(String term);

    public boolean getSearchAuctionTitle();

    public void setSearchAuctionTitle(boolean searchTitle);

    public boolean getSearchAuctionDescription();

    public void setSearchAuctionDescription(boolean searchDescription);

    public boolean isSearchUser();

    public void setSearchUser(boolean searchUser);

    public int getPageSize();

	public void setPageSize(int pageSize);

    public void find();

    public void nextPage();

	public boolean isNextPageAvailable();

    public void destroy();
}
