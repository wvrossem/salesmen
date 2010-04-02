package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.Category;
import be.vub.salesmen.entity.UserAccount;
import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessages;
import org.richfaces.component.UITree;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;

import javax.ejb.Remove;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.jboss.seam.ScopeType.CONVERSATION;

@Name("manageAuction")
@Scope(CONVERSATION)
public class ManageAuctionBean implements ManageAuction, Serializable 
{
	private static final long serialVersionUID = 5797405997183391745L;
	
	// Private attributes
	Auction auction; 
	private boolean inputIsOk=false;
	private Category category;
	private boolean isNew=false;    //used by
    private Date auctionEndDate;
	
	// In annotations
	@In EntityManager entityManager;
    @In FacesMessages facesMessages;
	@In StatusMessages statusMessages;

    public ManageAuctionBean()
    {
        long currentTimeInMillisec = System.currentTimeMillis();
        this.auctionEndDate = new Date( currentTimeInMillisec+24*60*60*1000 );//1day = 24*60*60*1000 milliseconds
    }

	@Begin(join = true)
	public void createAuction()
	{
		if(this.auction==null)  //REQUIRED, otherwise view-fields will be emptied on error message
		{
			this.setAuction(new Auction());
		}
	}


    public void verifyPrice()
    {
        if(this.auction.getStartingPrice()<=0)
		{
            facesMessages.addToControlFromResourceBundle("price", FacesMessage.SEVERITY_INFO, "salesmen.Auction.create.priceBelowZero");
           // return false;
        }
        //return true;
    }

	
	public void checkInput()
	{

		if(this.category==null)
		{
			//FacesMessages.instance().addToControl("category", "Please select a category");
			System.out.println("Category NOT set!");
		}
        else
		{
			this.auction.setCategory(this.category);
			System.out.println("Category set, OK!");
		}



        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();//current date & time
        this.auction.setEndDate(this.auctionEndDate);
        if(now.after(this.auction.getEndDate()))
        {
            facesMessages.addToControlFromResourceBundle("endDate", FacesMessage.SEVERITY_INFO, "salesmen.Auction.create.endDateBeforeNow");
            return;
        }

        //all ok
		this.setInputIsOk(true);
	}

	public void save(UserAccount user)
	{
		this.setNew(true);
		this.auction.setOwner(user);

		//this.auction.setStatus(Auction.AuctionStatus.UNLISTED);
		//entityManager.persist(this.auction);
	}

	@End
	public void confirm()
	{
        Calendar cal = Calendar.getInstance();
        this.auction.setStartDate(cal.getTime());  //current date & time
		this.auction.setStatus(Auction.AuctionStatus.LISTED);
		entityManager.persist(this.auction);
	}
	
	@Destroy @Remove
	public void destroy()
	{

	}
	
	// Public Attribute getters/setters with annotations 
	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}
	
	public Auction getAuction()
	{
		return auction;
	}

	public void setInputIsOk(boolean inputIsOk)
	{
		this.inputIsOk = inputIsOk;
	}

	public boolean isInputIsOk()
	{
		return inputIsOk;
	}
	
	public Category getCategoryId()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
		statusMessages.add("Category " + category.getName() + " selected");
	}

    public void processTreeNodeImplSelection(NodeSelectedEvent event) {
		System.out.println("Category selected");
		HtmlTree tree = (HtmlTree) event.getComponent();
		TreeNode<Category> currentNode = tree.getModelTreeNode();
		System.out.println(currentNode.getData().getName());
    }

    public boolean isNew()
	{
		return isNew;
	}

	public void setNew(boolean aNew)
	{
		isNew = aNew;
	}


    public Date getAuctionEndDate()
    {
        return auctionEndDate;
    }

    public void setAuctionEndDate(Date auctionEndDate)
    {
        this.auctionEndDate = auctionEndDate;
    }
}