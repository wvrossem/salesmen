package be.vub.salesmen.session;

import be.vub.salesmen.entity.Category;
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

	public Category getIncludeCategory();

	public void setIncludeCategory(Category includeCategory);

	public int getPriceMin();

	public void setPriceMin(int priceMin);

	public void find();

	public void nextPage();

	public boolean isNextPageAvailable();

	public void auctionsOfUser(User user);

	public List auctionsOfUser(String userName);

	public void destroy();
}