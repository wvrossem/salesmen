package be.vub.salesmen.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;

import be.vub.salesmen.entity.User;

@Stateful
@Name("basicSearch")
@Scope(ScopeType.SESSION)
public class BasicSearchBean implements BasicSearch {
	@PersistenceContext
	private EntityManager entityManager;

	private String searchTerm;
	private String entityType;
	
	private int pageSize = 10;
	private int page;
	
	private boolean nextPageAvailable;

	@DataModel
	private List entities;

	public void find() {
		page = 0;
		queryEntities();
	}

	public void nextPage() {
		page++;
		queryEntities();
	}

	private void queryEntities() {
		
		StringBuilder qry = new StringBuilder();
		
		if ( entityType.equals("Auction")) {
			qry.append("from Auction e");
		} else if (entityType.equals("User")) {
			qry.append("from User e");
		} else if (entityType.equals("Tag")) {
			qry.append("from Tag e");
		} else if (entityType.equals("Category")){
			qry.append("from Category e");
		}
		
		// Only works for users
		qry.append(" where e.screenName like #{pattern}");
		
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

	public boolean isNextPageAvailable() {
		return nextPageAvailable;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Factory(value = "pattern", scope = ScopeType.EVENT)
	public String getSearchPattern() {
		return searchTerm == null ? "%" : '%' + searchTerm.toLowerCase()
				.replace('*', '%') + '%';
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	public String getEntityType() {
		return entityType;
	}
	
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@Remove
	public void destroy() {
	}

}
