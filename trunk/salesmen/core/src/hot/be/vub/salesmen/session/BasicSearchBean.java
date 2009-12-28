package be.vub.salesmen.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.type.EntityType;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;
import org.jboss.seam.international.StatusMessages;

import be.vub.salesmen.entity.User;

@Stateful
@Name("basicSearch")
@Scope(ScopeType.SESSION)
public class BasicSearchBean implements BasicSearch {
	@PersistenceContext
	private EntityManager em;

	private String searchString;
	private int pageSize = 10;
	private int page;
	private boolean nextPageAvailable;

	@DataModel
	private List<User> entities;

	public void find() {
		page = 0;
		queryEntities();
	}

	public void nextPage() {
		page++;
		queryEntities();
	}

	private void queryEntities() {
		List<User> results = em
				.createQuery(
						"select e from #{entity}" +
						"where lower(e.name) like #{pattern}")
				.setMaxResults(pageSize + 1).setFirstResult(page * pageSize)
				.getResultList();

		nextPageAvailable = results.size() > pageSize;
		if (nextPageAvailable) {
			entities = new ArrayList<User>(results.subList(0, pageSize));
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
		return searchString == null ? "%" : '%' + searchString.toLowerCase()
				.replace('*', '%') + '%';
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	@Remove
	public void destroy() {
	}

}