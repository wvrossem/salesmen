package be.vub.salesmen.session;

import be.vub.salesmen.entity.User;

import javax.ejb.Local;
import java.util.List;

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

    public List auctionsOfUser(User user);

    public List auctionOfUser(String userName);

    public void nextPage();

	public boolean isNextPageAvailable();

    public void destroy();
}
