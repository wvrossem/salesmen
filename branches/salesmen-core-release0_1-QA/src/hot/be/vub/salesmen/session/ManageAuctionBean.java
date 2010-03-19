package be.vub.salesmen.session;

import static org.jboss.seam.ScopeType.CONVERSATION;

import javax.ejb.Remove;
import javax.persistence.EntityManager;
import java.io.Serializable;



import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.FacesMessages;

import be.vub.salesmen.entity.Auction;

@Scope(CONVERSATION)
@Name("manageAuction")
public class ManageAuctionBean implements ManageAuction, Serializable 
{
	// Private attributes
	private static final long serialVersionUID = 5797405997183391745L;
	private Auction auction; 
	private boolean inputIsOk=false;
	private int categoryId;
	
	// In annotations
	@In EntityManager entityManager;

	@Begin
	public void createAuction()
	{
		this.setAuction(new Auction());
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

	@End
	public void confirm()
	{
		this.auction.setStatus(Auction.AuctionStatus.LISTED);
		entityManager.merge(auction);
	}
	
	@Destroy @Remove
	public void destroy() {}
	
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
	
	public int getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(int categoryId)
	{
		this.categoryId = categoryId;
	}
}