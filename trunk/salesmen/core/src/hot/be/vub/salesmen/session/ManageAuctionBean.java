package be.vub.salesmen.session;



import javax.ejb.Remove;
import javax.persistence.EntityManager;
import java.io.Serializable;



import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.FacesMessages;

import be.vub.salesmen.entity.Auction;
 import static org.jboss.seam.ScopeType.CONVERSATION;
import org.jboss.seam.annotations.Scope;

@Name("manageAuction")
@Scope(CONVERSATION)
public class ManageAuctionBean implements ManageAuction, Serializable 
{
	@In EntityManager entityManager;
	@In Auction auction; 
	private static final long serialVersionUID = 5797405997183391745L;

	private boolean inputIsOk=false;
    private int categoryId;
	
	

                 @Begin
    public void create() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void checkInput()
	{
		if(this.auction.getStartingPrice()>0)
		{
			this.setInputIsOk(true);
            entityManager.persist(auction);
		}
		else
		{
			FacesMessages.instance().addToControl("price", "Price should be greater than 0");
		}
	}


    public void confirm()
    {
        this.auction.setStatus(Auction.AuctionStatus.LISTED);
        entityManager.merge(auction);
    }
	
	public void setAuction(Auction auction)
    {
		this.auction = auction;
	}


	public Auction getAuction() {
		return auction;
	}
	
	@Destroy @Remove
	public void destroy()
    {}


	public void setInputIsOk(boolean inputIsOk) {
		this.inputIsOk = inputIsOk;
	}


	public boolean isInputIsOk() {
		return inputIsOk;
	}
        public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}