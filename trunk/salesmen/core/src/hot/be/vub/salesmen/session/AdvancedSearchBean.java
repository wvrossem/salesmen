package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.Category;
import be.vub.salesmen.entity.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Stateful
@Name("advancedSearch")
@Scope(ScopeType.SESSION)
@Synchronized(timeout = 1000000000)
@AutoCreate
public class AdvancedSearchBean implements AdvancedSearch
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

	String category = "";
	String tags;
	
	User user = null;

	int priceMin = 0;
	int priceMax = 0;
	int minNrOfBids = 0;
	int maxNrOfBids = 0;

	Category includeCategory;

	private String auctionsQry;
	private String usersQry;

	private boolean nextPageAvailable;

	@DataModel
	private List entities;
	@DataModel
	private List<Auction> auctions;
	@DataModel
	private List<User> users;
	private boolean searchUserScreenName = true;
	private boolean searchUserFirstName = true;
	private boolean searchUserLasttName = true;
	private String entityType = "Auction";

	private void createAuctionsQuery()
	{
		StringBuilder qry = new StringBuilder();
		boolean searchingElse = false;
        String[] includeKeywords = getIncludeKeywords();
        String[] excludeKeywords = getExcludeKeywords();
		
		qry.append("from");
		
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
			
		if (includeUser != null)
		{
			List filteredResults = new ArrayList();
		}
	}

	public void queryAuctions()
	{
		try
		{
			createAuctionsQuery();
			auctions =  entityManager.createQuery(auctionsQry).getResultList();
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
		queryAuctions();
		queryUsers();
	}

	public boolean entityTypeAuction()
	{
		return entityType.equals("Auction");
	}

	public boolean entityTypeUser()
	{
		return entityType.equals("User");
	}

	public void setEnityTypeAuction()
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
		includeCategory = cat;
		System.out.println("Category selected");
		auctionsOfCategory();
    }

	
	public List auctionsOfCategory() 
	{
		String qry = "FROM Auction a WHERE a.category.name = '" + includeCategory.getName() + "'";
		List<Category> children = getChildrenOfCategory(includeCategory); 
		for (Category cat : children)
		{
			qry += " OR a.category.name = '" + cat.getName() + "'"; 
		}
		entities = entityManager.createQuery(qry).getResultList();
		return entities;
	}

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

	public int getPriceMin()
	{
		return priceMin;
	}

	public void setPriceMin(int priceMin)
	{
		this.priceMin = priceMin;
	}

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
