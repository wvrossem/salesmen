package be.vub.salesmen.session;

import be.vub.salesmen.entity.*;
import org.richfaces.model.TreeNodeImpl;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import java.util.List;

@Local
public interface Search
{
	/*
		MAIN SEARCH METHODS
	 */
	public void queryAuctions();

	public void queryUsers();

	public void queryAuctionsAndUsers();

	public List suggest(Object begin);

	/*
		METHODS CONCERNING ENTITY TYPE
	 */
	public boolean entityTypeAuction();

	public boolean entityTypeUser();

	public void setEntityTypeAuction();

	public void setEntityTypeUser();

	/*
		METHODS CONCERNING CATEGORIES
	 */
	public TreeNodeImpl<Category> getCategoryTree();

	public void processTreeNodeImplSelection(Category cat);

	/*
		INDIVIDUAL SEARCH METHODS
	 */

	public void auctionsOfUser(User user);

  public List auctionsOfUser(String userName);

  public List auctionsOfCategory(Category cat);

	public Object findUser(String screenName);

	public void findAuctionsOfUser(UserAccount userAccount);

	public void findAuctionsWithBidsByUser(UserAccount userAccount);

	public void findTransactionsWithUserSeller(UserAccount userAccount);

	public void findTransactionsWithUserBuyer(UserAccount userAccount);

	public Transaction findTransaction(Long transactionId);

	public UserAccount findUserAccount(String userName);

	public Object findAuction(Long auctionId, EntityManager em);

	public List<Bid> findBids(Auction auction, int limit, EntityManager em);

	public List<UserComment> findComments(Auction auction, EntityManager em);

	public List<UserComment> findComments(Transaction transaction);

	public List<AuctionImage> findImages(Auction auction, int limit, EntityManager em);

	/*
		GETTERS AND SETTERS
	 */
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

	public Category getIncludeCategory();

	public void setIncludeCategory(Category includeCategory);

	public boolean getSearchFinished();

	public void setSearchFinished(boolean searchFinished);

	public int getPriceMin();

	public void setPriceMin(int priceMin);

	public String getIncludeUser();

	public void setIncludeUser(String includeUser);

	/*
		OTHER METHODS
	 */
	public void destroy();
}

