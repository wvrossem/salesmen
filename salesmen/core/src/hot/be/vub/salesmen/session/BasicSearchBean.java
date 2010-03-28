package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.Category;
import be.vub.salesmen.entity.SearchTerm;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.international.StatusMessages;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    private TreeNodeImpl<Category> categoryTree;

    @In
    StatusMessages statusMessages;

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
	
	public Object findAuction(Long auctionId, EntityManager em)
    {
        if(em==null)
        {
              System.out.println("findAuction ERROR: entityManager is null");
        }
        try
        {
            String qry = "FROM Auction a WHERE a.id = #{auctionId}";
            entities = em.createQuery(qry).getResultList();
            if(entities==null)
            {
                  System.out.println("findAuction ERROR: entities is null");
            }
            if (entities!=null && entities.size() == 1)
            {
                return entities.get(0);
            }
            else
            {
                return null;
            }
        }
        catch(NullPointerException e)
        {
            System.out.println("findAuction ERROR: searching for ID="+auctionId);
        }
        return null;
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

	private TreeNodeImpl<Category> findParent(List<TreeNodeImpl<Category>> parents, TreeNodeImpl<Category> child)
	{
		for (TreeNodeImpl<Category> node : parents) {
			if (node.getData().getName().equals(child.getData().getParent().getName()))
			{
				return node;
			}
		}
		return null;
	}

	public void createCategoryTree()
	{
		List<Category> allCategories = entityManager.createQuery("from Category").getResultList();
        /*statusMessages.add("All categories");
        for (Category c : allCategories) {
            statusMessages.add(c.getParent().getName());
        }    */
		categoryTree = new TreeNodeImpl<Category>();
		List<TreeNodeImpl<Category>> categoryTreeNodes = new ArrayList();
        int id = 0;
		for (Category cat : allCategories)
		{
            if (cat.getParent() == null)
			{
                //categoryTreeNodes.add(categoryTree);
				categoryTree.setData(cat);
			} else
            {
			    TreeNodeImpl categoryNode = new TreeNodeImpl<Category>();
			    categoryNode.setData(cat);
                categoryTree.addChild(id++,categoryNode);
			    //categoryTreeNodes.add(categoryNode);
            }
		}
        //statusMessages.add("Category nodes");
        /*for (TreeNode<Category> c : categoryTreeNodes) {
            statusMessages.add(c.getData().getParent().getName());
        }   */
		//int id = 0;
		/*for (TreeNodeImpl<Category> firstNode : categoryTreeNodes)
		{
            if (firstNode.getData().getParent() != null )
            {
                for (TreeNodeImpl<Category> secondNode : categoryTreeNodes) {
                    if (secondNode.getData().getParent().getName().equals(firstNode.getData().getParent().getName()))
                    {
                        secondNode.addChild(id++,firstNode);
                        break;
                    }
                }
            }    */
			/*//TreeNodeImpl<Category> parent = ;
            if (node.getData().getParent() != null) {
               findParent(categoryTreeNodes, node).addChild(id++, node);
            }        */
       // }
        /*Iterator<Map.Entry<Object,TreeNode<Category>>> it = categoryTree.getChildren();
        statusMessages.add("test0");
        while(it.hasNext()) {
            statusMessages.add("test1");
            Map.Entry<Object,TreeNode<Category>> node = it.next();
            statusMessages.add(node.getValue().getData().getName());
            statusMessages.add("test2");
        }
        statusMessages.add("no children");    */
        //statusMessages.add(categoryTree.getChild(1).getData().getName());
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

    public TreeNodeImpl<Category> getCategoryTree() {
        return categoryTree;
    }

    @Remove
	public void destroy() {}

}
