package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.Category;
import be.vub.salesmen.entity.User;
import be.vub.salesmen.entity.SearchTerm;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.richfaces.model.TreeNodeImpl;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Stateful
@Name("basicSearch")
@Scope(ScopeType.SESSION)
public class BasicSearchBean implements BasicSearch
{
	@PersistenceContext
	private EntityManager entityManager;

	private String searchTerm;
	private String entityType;
	
	private int pageSize = 2;
	private int page;
	
	private boolean nextPageAvailable;

	private SearchTerm savedTerm;

	@DataModel
	private List entities;

	public void find()
	{
		page = 0;
		queryEntities();
		if (entities.size() != 0 && searchTerm.length() >= 3)
		{
			String q = "from SearchTerm s where s.term = #{searchTerm}";
			List entLst = entityManager.createQuery(q).getResultList();
			if (entLst.size() != 0)
			{
				savedTerm = new SearchTerm();
				savedTerm.setTerm(searchTerm);
				entityManager.persist(savedTerm);
			}
		}
	}

	public List suggest(Object begin)
	{
		AtomicReference<String> qry = new AtomicReference<String>("select s.term from SearchTerm s where s.term like(#{searchTerm})");
		return entityManager.createQuery(qry.get()).setMaxResults(10).getResultList();
	}

	public void nextPage()
	{
		page++;
		queryEntities();
	}

	private void queryEntities()
	{
		StringBuilder qry = new StringBuilder();
		
		if ( entityType.equals("Auction"))
		{
			qry.append("from Auction e");
			qry.append(" WHERE UPPER(e.title) LIKE UPPER(#{pattern})");
				qry.append(" AND e.status = " + Auction.AuctionStatus.LISTED.ordinal());
		} else if (entityType.equals("User"))
		{
			qry.append("from User e");
			qry.append(" WHERE UPPER(e.screenName) LIKE UPPER(#{pattern})");
			qry.append(" or UPPER(e.firstName) LIKE UPPER(#{pattern})");
			qry.append(" or UPPER(e.lastName) LIKE UPPER(#{pattern})");
		} else if (entityType.equals("Tag"))
		{
			qry.append("from Tag e");
		} else if (entityType.equals("UserAccount"))
		{
			qry.append("from UserAccount e");
		}		
		
		List results = entityManager.createQuery(qry.toString())
			.setMaxResults(pageSize) //+1?
			.setFirstResult( page * pageSize )
			.getResultList(); 

		nextPageAvailable = results.size() > pageSize;
		if (nextPageAvailable)
		{
			entities = new ArrayList<Object>(results.subList(0, pageSize));
		} else
		{
			entities = results;
		}
	}
	
	public Object findUser(String screenName)
	{
		String qry = "FROM User u WHERE u.screenName = #{screenName}";
		entities = entityManager.createQuery(qry).getResultList();
		if (entities.size() == 1)
		{
			return entities.get(0);
		} else
		{
			return false;
		}
	}

	public Object findUserAccount(String userName)
	{
	String qry = "FROM UserAccount u WHERE u.userName = #{userName}";
		entities = entityManager.createQuery(qry).getResultList();
		if (entities.size() == 1)
		{
			return entities.get(0);
		} else
		{
			return false;
		}
	}

	private TreeNodeImpl findParent(List<TreeNodeImpl<Category>> parents, TreeNodeImpl<Category> child)
	{
		for (TreeNodeImpl node : parents) {
			if (node.getData().equals(child.getData().getParent()))
			{
				return node;
			}
		}
		return null;
	}

	public TreeNodeImpl getCategoryTree()
	{
		List<Category> allCategories = entityManager.createQuery("from Category").getResultList();
		TreeNodeImpl<Category> categoryTree = new TreeNodeImpl<Category>();
		TreeNodeImpl<Category> root = new TreeNodeImpl<Category>();
		List<TreeNodeImpl<Category>> categoryTreeNodes = new ArrayList();
		for (Category cat : allCategories)
		{
			TreeNodeImpl categoryNode = new TreeNodeImpl<Category>();
			categoryNode.setData(cat);
			categoryTreeNodes.add(categoryNode);
			if (cat.getParent() == null)
			{
				root = categoryNode;
			}
		}
		int id = 0;
		for (TreeNodeImpl<Category> node : categoryTreeNodes)
		{
			TreeNodeImpl<Category> parent = findParent(categoryTreeNodes, node);
			parent.addChild(id++, node);
		}
		return categoryTree;
	}
	
	public boolean isNextPageAvailable()
	{
		return nextPageAvailable;
	}

	public boolean entityTypeUser()
	{
		return entityType.equals("User");
	}

	public boolean entityTypeAuction()
	{
		return entityType.equals("Auction");
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	@Factory(value = "pattern", scope = ScopeType.EVENT)
	public String getSearchPattern()
	{
		return searchTerm == null ? "%" : '%' + searchTerm.replace('*', '%') + '%';
	}

	public String getSearchTerm()
	{
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm)
	{
		this.searchTerm = searchTerm;
	}
	
	public String getEntityType()
	{
		return entityType;
	}
	
	public void setEntityType(String entityType)
	{
		this.entityType = entityType;
	}

	@Remove
	public void destroy() {}

}
