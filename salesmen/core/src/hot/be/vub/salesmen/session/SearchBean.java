package be.vub.salesmen.session;

import be.vub.salesmen.entity.*;
import org.hibernate.validator.Pattern;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.richfaces.model.TreeNodeImpl;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Stateful
@Name("search")
@Scope(ScopeType.SESSION)
@Synchronized(timeout = 1000000000)
@AutoCreate
public class SearchBean implements Search
{

	@PersistenceContext
	private EntityManager entityManager;

	String includeTerm = "";
	String excludeTerm = "";
	String includeUser = "";

	boolean searchAuctionTitle = true;
	boolean searchAuctionDescription = true;
	boolean searchUser;
	boolean searchOwner;
	boolean searchBid;
	boolean searchCategory;
	boolean searchFinished;

	String category = null;
	String tags;

	User user = null;

	int priceMin = 0;
	int priceMax = 0;
	int minNrOfBids = 0;
	int maxNrOfBids = 0;

	Category includeCategory = null;

	private String auctionsQry;
	private String usersQry;

	@DataModel
	private List entities;
	@DataModel
	private List<Auction> auctions;
	@DataModel
	private List<User> users;
	@DataModel
	private List<Transaction> transactions;
	private TreeNodeImpl<Category> categoryTree = null;
	private boolean searchUserScreenName = true;
	private boolean searchUserFirstName = true;
	private boolean searchUserLasttName = true;
	private String entityType = "Auction";
	private boolean useAdvancedFilter = true;

	private void createAuctionsQuery()
	{
		StringBuilder qry = new StringBuilder();
		boolean searchingElse = false;
        String[] includeKeywords = getIncludeKeywords();
        String[] excludeKeywords = getExcludeKeywords();

		qry.append("select distinct a from");

		if (searchAuctionTitle | searchAuctionDescription | searchOwner)
		{
			qry.append(" Auction a");
		}

		qry.append(" where");

		if (searchAuctionTitle)
		{
			boolean first = true;
			for (String includeKeyword : includeKeywords)
			{
				if (first)
				{
					qry.append(" upper(a.title) like upper('%" + includeKeyword + "%')");
					first = false;
				} else
				{
					qry.append(" and upper(a.title) like upper('%" + includeKeyword + "%')");
				}

			}

			for (String excludeKeyword : excludeKeywords)
			{
				qry.append(" and upper(a.title) != upper('" + excludeKeyword + "')");

			}
			searchingElse = true;
		}

		if (searchAuctionDescription)
		{
			if (searchingElse)
			{
				qry.append(" or");
			}
			boolean first = true;
			for (String includeKeyword : includeKeywords)
			{
				if (first)
				{
					qry.append(" upper(a.description) like upper('%" + includeKeyword + "%')");
					first = false;
				} else
				{
					qry.append(" and upper(a.description) like upper('%" + includeKeyword + "%')");
				}

			}

			for (String excludeKeyword : excludeKeywords)
			{
				qry.append(" and upper(a.description) != upper('" + excludeKeyword + "')");

			}
			searchingElse = true;
		}

		if (priceMin > 0)
		{
			qry.append(" and a.startingprice <= " + priceMin);
		}

		if (searchCategory)
		{
			qry.append(" and a.category = " + includeCategory);
		}

		if (searchOwner & user != null)
		{
			if (searchingElse)
			{
				qry.append(" and");
			}
			qry.append(" a.owner = " + user.getUserId());
		}

		if (searchFinished)
		{
			qry.append(" AND a.status = " + Auction.AuctionStatus.FINISHED.ordinal());
		} else
		{
			qry.append(" AND a.status = " + Auction.AuctionStatus.LISTED.ordinal());
		}

		auctionsQry = qry.toString();

		System.out.println("Auction query: " + auctionsQry);
	}

	public void filterAuctions()
	{
		List<Auction> filteredResults = new ArrayList<Auction>();
		FilteringBean filter = new FilteringBean();
		boolean filterElse = false;
		if (includeCategory != null)
		{
			filterElse = true;
			for (Auction a : auctions)
			{
				if (a.getCategory().getId() == includeCategory.getId())
				{
					filteredResults.add(a);
				}
			}
		}
		if (priceMax != 0)
		{
			if (filterElse)
			{
				List<Auction> filteredElseResults = new ArrayList<Auction>();
				for (Auction a : filteredResults)
				{
					if (filter.getHighestBidAmount(a) <= priceMax)
					{
						filteredElseResults.add(a);
					}
				}
				filteredResults = filteredElseResults;
			} else
			{
				filterElse = true;
				for (Auction a : auctions)
				{
					if (filter.getHighestBidAmount(a) <= priceMax)
					{
						filteredResults.add(a);
					}
				}
			}
		}
		if (priceMin != 0)
		{
			if (filterElse)
			{
				List<Auction> filteredElseResults = new ArrayList<Auction>();
				for (Auction a : filteredResults)
				{
					if (filter.getHighestBidAmount(a) >= priceMin)
					{
						filteredElseResults.add(a);
					}
				}
				filteredResults = filteredElseResults;
			} else
			{
				filterElse = true;
				for (Auction a : auctions)
				{
					if (filter.getHighestBidAmount(a) >= priceMin)
					{
						filteredResults.add(a);
					}
				}
			}
		}

		if (includeUser.isEmpty())
		{
			if (filterElse)
			{
				List<Auction> filteredElseResults = new ArrayList<Auction>();
				for (Auction a : filteredResults)
				{
					if (a.getOwner().getUser().getScreenName().equals(includeUser))
					{
						filteredElseResults.add(a);
					}
				}
				filteredResults = filteredElseResults;
			} else
			{
				filterElse = true;
				for (Auction a : auctions)
				{
					if (a.getOwner().getUser().getScreenName().equals(includeUser))
					{
						filteredResults.add(a);
					}
				}
			}
		}

		if (filterElse)
		{
			auctions = filteredResults;
		}
	}

	public void queryAuctions()
	{
		try
		{
			createAuctionsQuery();
			auctions =  entityManager.createQuery(auctionsQry).getResultList();
			if(useAdvancedFilter)
			{
				filterAuctions();
			}
		} catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

	private void createUsersQuery()
	{
		StringBuilder qry = new StringBuilder();
		boolean searchingElse = false;
        String[] includeKeywords = getIncludeKeywords();
        String[] excludeKeywords = getExcludeKeywords();

		qry.append("from User u where");

		if (searchUserScreenName)
		{
			boolean first = true;
			for (String includeKeyword : includeKeywords)
			{
				if (first)
				{
					qry.append(" upper(u.screenName) like upper('%" + includeKeyword + "%')");
					first = false;
				} else
				{
					qry.append(" and upper(u.screenName) like upper('%" + includeKeyword + "%')");
				}

			}

			for (String excludeKeyword : excludeKeywords)
			{
				qry.append(" and upper(u.screenName) != upper('" + excludeKeyword + "')");

			}
			searchingElse = true;
		}

		if (searchUserFirstName)
		{
			if (searchingElse)
			{
				qry.append(" or");
			}
			boolean first = true;
			for (String includeKeyword : includeKeywords)
			{
				if (first)
				{
					qry.append(" upper(u.firstName) like upper('%" + includeKeyword + "%')");
					first = false;
				} else
				{
					qry.append(" and upper(u.firstName) like upper('%" + includeKeyword + "%')");
				}

			}

			for (String excludeKeyword : excludeKeywords)
			{
				qry.append(" and upper(u.firstName) != upper('" + excludeKeyword + "')");

			}
			searchingElse = true;
		}

		if (searchUserLasttName)
		{
			if (searchingElse)
			{
				qry.append(" or");
			}
			boolean first = true;
			for (String includeKeyword : includeKeywords)
			{
				if (first)
				{
					qry.append(" upper(u.lastName) like upper('%" + includeKeyword + "%')");
					first = false;
				} else
				{
					qry.append(" and upper(u.lastName) like upper('%" + includeKeyword + "%')");
				}

			}

			for (String excludeKeyword : excludeKeywords)
			{
				qry.append(" and upper(u.lastName) != upper('" + excludeKeyword + "')");

			}
			searchingElse = true;
		}

		usersQry = qry.toString();

		System.out.println("User query: " + usersQry);
	}

	public void queryUsers()
	{
		try
		{
			createUsersQuery();
			users =  entityManager.createQuery(usersQry).getResultList();
		} catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

	public void queryAuctionsAndUsers()
	{
		useAdvancedFilter = false;
		queryAuctions();
		queryUsers();
		useAdvancedFilter = true;

		if ((!auctions.isEmpty() || !users.isEmpty()) && includeTerm.length() > 3)
		{
			String q = "FROM SearchTerm s WHERE s.term = '" + includeTerm + "'";
			System.out.println(q);
			List entLst = entityManager.createQuery(q).getResultList();
			System.out.println(entLst);
			if (entLst.isEmpty())
			{
					SearchTerm savedTerm = new SearchTerm();
					savedTerm.setTerm(includeTerm.toLowerCase());
					entityManager.persist(savedTerm);
			}
		}
	}

	public List suggest(Object begin)
	{
		String qry = "from SearchTerm s where s.term like '" + (String)begin + "%'";
		return entityManager.createQuery(qry).setMaxResults(10).getResultList();
	}

	public boolean entityTypeAuction()
	{
		return entityType.equals("Auction");
	}

	public boolean entityTypeUser()
	{
		return entityType.equals("User");
	}

	public void setEntityTypeAuction()
	{
		entityType = "Auction";
	}

	public void setEntityTypeUser()
	{
		entityType = "User";
	}

	public void auctionsOfUser(User user)
	{
		this.user = user;
		this.searchOwner = true;
		queryAuctions();
	}

	public List auctionsOfUser(String userName)
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	private void createCategoryTree()
	{
		List<Category> allCategories = entityManager.createQuery("from Category").getResultList();
		categoryTree = new TreeNodeImpl<Category>();
		List<TreeNodeImpl<Category>> categoryTreeNodes = new ArrayList();

		for (Category cat : allCategories)
		{
			if (cat.getParent() == null)
			{
				categoryTreeNodes.add(categoryTree);
				categoryTree.setData(cat);
			} else
			{
				TreeNodeImpl categoryNode = new TreeNodeImpl<Category>();
				categoryNode.setData(cat);
				categoryTreeNodes.add(categoryNode);
			}
		}
		int id = 0;
		for (TreeNodeImpl<Category> firstNode : categoryTreeNodes)
		{
			if (firstNode.getData().getParent() != null)
			{
				for (TreeNodeImpl<Category> secondNode : categoryTreeNodes)
				{
					if (secondNode.getData() == firstNode.getData().getParent())
					{
						secondNode.addChild(id++, firstNode);
						break;
					}
				}
			}
		}
	}

	public TreeNodeImpl<Category> getCategoryTree()
	{
		if (categoryTree == null)
		{
			createCategoryTree();
		}
		return categoryTree;
	}

	private List<Category> getChildrenOfCategory(Category category)
	{
		List<Category> allCategories = entityManager.createQuery("from Category").getResultList();
		List<Category> foundChildren = new ArrayList<Category>();

		while (!allCategories.isEmpty())
		{
			for (Category cat : allCategories)
			{
				if (cat == includeCategory.getParent())
				{
					foundChildren.add(cat);
				}
				allCategories.remove(cat);
			}
		}
		return foundChildren;
	}

	public void processTreeNodeImplSelection(Category cat) {
		auctionsOfCategory(cat);
    }


	/*
		SEPARATE SEARCH METHODS
	 */
	public List auctionsOfCategory(Category cat)
	{
		String qry = "FROM Auction a WHERE a.category.name = '" + cat.getName() + "'";
		auctions = entityManager.createQuery(qry).getResultList();
		return auctions;
	}

	public User findUser(String screenName)
	{
		String qry = "FROM User u WHERE u.screenName = '" + screenName + "'";
		users = entityManager.createQuery(qry).getResultList();
		if (entities.size() == 1)
		{
			return users.get(0);
		} else
		{
			return null;
		}
	}

	public void findAuctionsOfUser(UserAccount userAccount)
	{
		String qry = "FROM Auction a WHERE a.owner = " + userAccount.getAccountId();
		auctions = entityManager.createQuery(qry).getResultList();
	}

	public void findAuctionsWithBidsByUser(UserAccount userAccount)
	{
		String qry = "SELECT DISTINCT a FROM Auction a, Bid b WHERE b.owner = " + userAccount.getAccountId() + " and b.auction = a.id";
		auctions = entityManager.createQuery(qry).getResultList();
	}

	public void findTransactionsWithUserSeller(UserAccount userAccount)
	{
		String qry = "SELECT DISTINCT t FROM Transaction t WHERE t.seller = " + userAccount.getAccountId();
		transactions = entityManager.createQuery(qry).getResultList();
	}

	public void findTransactionsWithUserBuyer(UserAccount userAccount)
	{
		String qry = "SELECT DISTINCT t FROM Transaction t WHERE t.buyer = " + userAccount.getAccountId();
		transactions = entityManager.createQuery(qry).getResultList();
	}

	public UserAccount findUserAccount(String userName)
	{
		String qry = "FROM UserAccount u WHERE u.username = '" + userName + "'";
		entities = entityManager.createQuery(qry).getResultList();
		if (entities.size() == 1)
		{
			return (UserAccount) entities.get(0);
		} else
		{
			return null;
		}
	}

	public Object findAuction(Long auctionId, EntityManager em)
	{
		if (em == null)
		{
			System.out.println("findAuction ERROR: entityManager is null");
		}
		try
		{
			String qry = "FROM Auction a WHERE a.id = #{auctionId}";
			entities = em.createQuery(qry).getResultList();
			if (entities == null)
			{
				System.out.println("findAuction ERROR: entities is null");
			}
			if (entities != null && entities.size() == 1)
			{
				return entities.get(0);
			} else
			{
				return null;
			}
		}
		catch (NullPointerException e)
		{
			System.out.println("findAuction ERROR: searching for ID=" + auctionId);
		}
		return null;
	}

	public Transaction findTransaction(Long transactionId)
	{
		try
		{
			String qry = "FROM Transaction t WHERE t.id = #{transactionId}";
			transactions = entityManager.createQuery(qry).getResultList();
			if (transactions.size() == 1)
			{
				return transactions.get(0);
			} else
			{
				return null;
			}
		}
		catch (NullPointerException e)
		{
			System.out.println("findAuction ERROR: searching for ID=" + transactionId);
			return null;
		}
	}

	public User findUser(Long userId)
	{
		 try
		{
			String qry = "FROM User u WHERE u.userId = #{userId}";
			users = entityManager.createQuery(qry).getResultList();
			if (users.size() == 1)
			{
				return users.get(0);
			} else
			{
				return null;
			}
		}
		catch (NullPointerException e)
		{
			System.out.println("findAuction ERROR: searching for ID=" + userId);
			return null;
		}
	}

	public List<Bid> findBids(Auction auction, int limit, EntityManager em)
	{
		List<Bid> bids = null;
		if (auction.getId() != null)
		{
			String qry = "FROM Bid b WHERE b.auction.id = '" + auction.getId() + "' ORDER BY b.amount DESC, b.id ASC";
			bids = (List<Bid>) em.createQuery(qry).getResultList();
			if (bids != null && bids.size() > 5)
			{
				bids = (List<Bid>) em.createQuery(qry).getResultList().subList(0, limit);
			}
		}
		return bids;
	}

	public List<UserComment> findComments(Auction auction, EntityManager em)
	{
		List<UserComment> comments = null;
		if (auction.getId() != null)
		{
			String qry = "FROM UserComment c WHERE c.auction.id = '" + auction.getId() + "' ORDER BY c.date DESC";
			comments = (List<UserComment>) em.createQuery(qry).getResultList();
		}
		return comments;
	}

	public List<UserComment> findComments(Transaction transaction)
	{
		List<UserComment> comments = null;
		if (transaction.getId() != null)
		{
			String qry = "FROM UserComment c WHERE c.transaction.id = '" + transaction.getId() + "' ORDER BY c.date DESC";
			comments = entityManager.createQuery(qry).getResultList();
		}
		return comments;
	}

	public List<AuctionImage> findImages(Auction auction, int limit, EntityManager em)
	{
		//TODO: if no images were returned, return a default image
		List<AuctionImage> images = null;
		if (auction.getId() != null)
		{
			String qry = "FROM AuctionImage a WHERE a.auction.id = '" + auction.getId() + "' ORDER BY a.id ASC";
			images = (List<AuctionImage>) em.createQuery(qry).getResultList();
			if (images != null && images.size() > 5)//TODO: remove fixed value
			{
				images = (List<AuctionImage>) em.createQuery(qry).getResultList().subList(0, limit);
			}
		}
		return images;
	}

	/*
		OTHERS METHODS
	 */

    public String[] getIncludeKeywords() {
        String regex = "[,;|\\s]";
        return includeTerm.split(regex);
    }

    public String[] getExcludeKeywords() {
        String regex = "[,;|\\s]";
        return excludeTerm.split(regex);
    }

	@Factory(value = "includePattern", scope = ScopeType.EVENT)
	public String getSearchIncludePattern()
	{
		return includeTerm == null ? "%" : '%' + includeTerm.replace('*', '%') + '%';
	}

	@Factory(value = "excludePattern", scope = ScopeType.EVENT)
	public String getSearchExcludePattern()
	{
		return excludeTerm;
	}

	@Factory(value = "termPattern", scope = ScopeType.EVENT)
	public String getSearchTermPattern()
	{
		return includeTerm == null ? "" : includeTerm + '%';
	}

	public String getIncludeTerm()
	{
		return includeTerm;
	}

	public void setIncludeTerm(String term)
	{
		this.includeTerm = term;
	}

	public String getExcludeTerm()
	{
		return excludeTerm;
	}

	public void setExcludeTerm(String term)
	{
		this.excludeTerm = term;
	}

	public boolean getSearchAuctionTitle()
	{
		return searchAuctionTitle;
	}

	public void setSearchAuctionTitle(boolean searchTitle)
	{
		this.searchAuctionTitle = searchTitle;
	}

	public boolean getSearchAuctionDescription()
	{
		return searchAuctionDescription;
	}

	public void setSearchAuctionDescription(boolean searchDescription)
	{
		this.searchAuctionDescription = searchDescription;
	}

	public boolean isSearchUser()
	{
		return searchUser;
	}

	public void setSearchUser(boolean searchUser)
	{
		this.searchUser = searchUser;
	}

	@Pattern(regex="[0-9]+",
			 message="Minimum price may only contain numbers")
	public int getPriceMin()
	{
		return priceMin;
	}

	public void setPriceMin(int priceMin)
	{
		this.priceMin = priceMin;
	}

	@Pattern(regex="[0-9]+",
			 message="Minimum price may only contain numbers")
	public int getPriceMax()
	{
		return priceMax;
	}

	public void setPriceMax(int priceMax)
	{
		this.priceMax = priceMax;
	}

	public Category getIncludeCategory()
	{
		return includeCategory;
	}

	public void setIncludeCategory(Category includeCategory)
	{
		this.includeCategory = includeCategory;
	}

	public boolean getSearchFinished()
	{
		return searchFinished;
	}

	public void setSearchFinished(boolean searchFinished)
	{
		this.searchFinished = searchFinished;
	}

	public String getIncludeUser()
	{
		return includeUser;
	}

	public void setIncludeUser(String includeUser)
	{
		this.includeUser = includeUser;
	}

	@Remove
	public void destroy() {}
}
