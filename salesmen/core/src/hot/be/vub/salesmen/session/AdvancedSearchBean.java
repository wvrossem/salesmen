package be.vub.salesmen.session;

import be.vub.salesmen.entity.Category;
import be.vub.salesmen.entity.User;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
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
public class AdvancedSearchBean implements AdvancedSearch
{

	@PersistenceContext
	private EntityManager entityManager;

	String includeTerm;
	String excludeTerm;

	boolean searchAuctionTitle;
	boolean searchAuctionDescription;
	boolean searchUser;
	boolean searchOwner;
	boolean searchBid;
	boolean searchCategory;

	String category;
	String tags;
	
	User user = null;

	int priceMin = 0;
	int priceMax = 0;
	int minNrOfBids = 0;
	int maxNrOfBids = 0;

	Category includeCategory;

	private int pageSize = 10;
	private int page;

	private boolean nextPageAvailable;

	@DataModel
	private List entities;	
	
	private void queryEntities()
	{

		StringBuilder qry = new StringBuilder();
		boolean searchingElse = false;
        String[] includeKeywords = getIncludeKeywords();
        String[] excludeKeywords = getExcludeKeywords();

		System.out.println(includeKeywords);
		System.out.println(excludeKeywords);
		
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
				qry.append(" and upper(a.title) not like upper('%" + excludeKeyword + "%')");

			}
			searchingElse = true;
		}
		
		if (searchAuctionDescription)
		{
			if (searchingElse)
			{
				qry.append(" and");
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
				qry.append(" and upper(a.description) not like upper('%" + excludeKeyword + "%')");

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
		
		System.out.println(qry.toString());
		
		List results = entityManager.createQuery(qry.toString())
			.setMaxResults(pageSize) //+1?
			.setFirstResult( page * pageSize )
			.getResultList();

		nextPageAvailable = results.size() > pageSize;
		if (nextPageAvailable)
		{
			entities = new ArrayList(results.subList(0, pageSize));
		} else {
			entities = results;
		}
	}

	public void find()
	{
		page = 0;
		queryEntities();
	}
	
	public void auctionsOfUser(User user)
	{
		this.user = user;
		this.searchOwner = true;
		page = 0;
		queryEntities();
	}

	public List auctionsOfUser(String userName)
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
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
		return excludeTerm == null ? "%" : '%' + excludeTerm.replace('*', '%') + '%';
	}

	public void nextPage()
	{
		page++;
		queryEntities();
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

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public boolean isNextPageAvailable()
	{
		return false;
	}

	@Remove
	public void destroy() {}
}       
