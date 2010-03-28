package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.Category;
import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessages;
import org.richfaces.event.NodeSelectedEvent;

import javax.ejb.Remove;
import javax.persistence.EntityManager;
import java.io.Serializable;

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



	// In annotations
	@In EntityManager entityManager;
	//@in Auction auction

    @In
    StatusMessages statusMessages;

	@Begin(join = true)
	public void createAuction()
	{
		if(this.auction==null)  //REQUIRED, otherwise view-fields will be emptied on error message
		{
			this.setAuction(new Auction());
		}
	}

	
	public void checkInput()
	{
		if(this.auction.getStartingPrice()>0)
		{
			this.setInputIsOk(true);
			//entityManager.persist(auction);
		}
		else
		{
			FacesMessages.instance().addToControl("price", "Price should be greater than 0");
		}
	}

    public void save()
    {
         this.setNew(true);
        //this.auction.setStatus(Auction.AuctionStatus.UNLISTED);
        //entityManager.persist(this.auction);

        System.out.println("manageAuctionBean save(): ID "+this.auction.getId());
    }

    @End
	public void confirm()
	{

		this.auction.setStatus(Auction.AuctionStatus.LISTED);
		entityManager.merge(this.auction);

        System.out.println("manageAuctionBean confirmed,  conversation NOT ended");
	}
	
	@Destroy @Remove
	public void destroy()
	{
		System.out.println("manageAuctionBean destroyed");
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

	public void setCategoryId(Category categoryId)
	{
		this.category = categoryId;
        statusMessages.add("Category " + categoryId.getName() + " selected");
	}

    public void processTreeNodeImplSelection(final NodeSelectedEvent event) {
        System.out.println("Node selected : " + event);        
        statusMessages.add("Category selected");
    }    

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
    
}