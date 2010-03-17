package be.vub.salesmen.session;

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
import java.util.List;

@Stateful
@Name("advancedSearch")
@Scope(ScopeType.SESSION)
public class AdvancedSearchBean implements AdvancedSearch {

    @PersistenceContext
	private EntityManager entityManager;

    String includeTerm;
    String excludeTerm;

    boolean searchAuctionTitle;
    boolean searchAuctionDescription;
    boolean searchUser;

    String category;
    String tags;
    
    User user = null;

    int priceMin;
    int priceMax;

    private int pageSize = 10;
	private int page;

	private boolean nextPageAvailable;

    @DataModel
	private List entities;


    public String getIncludeTerm() {
        return includeTerm;  
    }

    public void setIncludeTerm(String term) {
        this.includeTerm = term;
    }

    public String getExcludeTerm() {
        return excludeTerm;
    }

    public void setExcludeTerm(String term) {
        this.excludeTerm = term;
    }

    public boolean getSearchAuctionTitle() {
        return searchAuctionTitle;
    }

    public void setSearchAuctionTitle(boolean searchTitle) {
        this.searchAuctionTitle = searchTitle;
    }

    public boolean getSearchAuctionDescription() {
        return searchAuctionDescription;
    }

    public void setSearchAuctionDescription(boolean searchDescription) {
        this.searchAuctionDescription = searchDescription;
    }

    public boolean isSearchUser() {
        return searchUser;
    }

    public void setSearchUser(boolean searchUser) {
        this.searchUser = searchUser;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    private void queryEntities() {

		StringBuilder qry = new StringBuilder();
		
		qry.append("from Auction a where upper(a.title) like upper(#{includePattern})");
		
		if (searchAuctionTitle) {
			//qry.append(" and ");
			qry.append(" and upper(a.title) not like upper(#{excludePattern})");
		}
		
		if (searchAuctionDescription) {
			qry.append(" and upper(a.description) like upper(#includePattern)");
			qry.append(" and upper(a.description) not like upper(#{excludePattern})");
		}
		
		if (user != null) {	
			qry.append(" and a.owner = #{user.userId}");
		}
		
		List results = entityManager.createQuery(qry.toString())
			.setMaxResults(pageSize) //+1?
            .setFirstResult( page * pageSize )
            .getResultList(); 

		nextPageAvailable = results.size() > pageSize;
		if (nextPageAvailable) {
			entities = new ArrayList(results.subList(0, pageSize));
		} else {
			entities = results;
		}
	}

    public void find() {
		page = 0;
		queryEntities();
    }
    
    @Factory(value = "includePattern", scope = ScopeType.EVENT)
	public String getSearchIncludePattern() {
		return includeTerm == null ? "%" : '%' + includeTerm.replace('*', '%') + '%';
	}
	
	@Factory(value = "excludePattern", scope = ScopeType.EVENT)
	public String getSearchExcludePattern() {
		return excludeTerm == null ? "%" : '%' + excludeTerm.replace('*', '%') + '%';
	}

    public void nextPage() {
		page++;
		queryEntities();
    }

    public boolean isNextPageAvailable() {
        return false;
    }

    @Remove
    public void destroy() {
        
    }
}       
