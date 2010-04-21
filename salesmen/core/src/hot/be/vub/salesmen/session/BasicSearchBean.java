package be.vub.salesmen.session;

import be.vub.salesmen.entity.*;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.international.StatusMessages;
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
@Synchronized(timeout = 1000000000)
@AutoCreate
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
	private TreeNodeImpl<Category> categoryTree = null;

	@In
	StatusMessages statusMessages;

	public void find()
	{
		try
		{
			page = 0;
			queryEntities();
			if (entities.size() != 0 && searchTerm.length() >= 3)
			{
				String q = "from SearchTerm s where s.term = #{searchTerm}";
				List entLst = entityManager.createQuery(q).getResultList();
				if (entLst.size() == 0)
				{
					savedTerm = new SearchTerm();
					savedTerm.setTerm(searchTerm);
					entityManager.persist(savedTerm);
				}
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public List suggest(Object begin)
	{
		System.out.println("ok");
		String qry = "from SearchTerm s where s.term like #{pattern}";
		return entityManager.createQuery(qry).setMaxResults(10).getResultList();
	}

	public void nextPage()
	{
		page++;
		queryEntities();
	}

	private void queryEntities()
	{
		try
		{
			StringBuilder qry = new StringBuilder();

			if (entityType.equals("Auction"))
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
					.setFirstResult(page * pageSize)
					.getResultList();

			nextPageAvailable = results.size() > pageSize;
			if (nextPageAvailable)
			{
				entities = new ArrayList<Object>(results.subList(0, pageSize));
			} else
			{
				entities = results;
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public User findUser(String screenName)
	{
		String qry = "FROM User u WHERE u.screenName = '" + screenName + "'";
		entities = entityManager.createQuery(qry).getResultList();
		if (entities.size() == 1)
		{
			return (User) entities.get(0);
		} else
		{
			return null;
		}
	}

	public User findUser(String screenName, EntityManager em)
	{
		String qry = "FROM User u WHERE u.screenName = '" + screenName + "'";
		entities = em.createQuery(qry).getResultList();
		if (entities.size() == 1)
		{
			return (User) entities.get(0);
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

	public UserAccount findUserAccount(String userName, EntityManager em)
	{
		String qry = "FROM UserAccount u WHERE u.username = '" + userName + "'";
		entities = em.createQuery(qry).getResultList();
		if (entities.size() == 1)
		{
			return (UserAccount) entities.get(0);
		} else
		{
			return null;
		}
	}

	private TreeNodeImpl<Category> findParent(List<TreeNodeImpl<Category>> parents, TreeNodeImpl<Category> child)
	{
		for (TreeNodeImpl<Category> node : parents)
		{
			if (node.getData().getName().equals(child.getData().getParent().getName()))
			{
				return node;
			}
		}
		return null;
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

	public void createCategoryTree()
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

	@Factory(value = "termPattern", scope = ScopeType.EVENT)
	public String getSearchTermPattern()
	{
		return searchTerm == null ? "" : searchTerm.replace('*', '%') + '%';
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

	public TreeNodeImpl<Category> getCategoryTree()
	{
		if (categoryTree == null)
		{
			createCategoryTree();
		}
		return categoryTree;
	}

	@Remove
	public void destroy()
	{
	}

}
