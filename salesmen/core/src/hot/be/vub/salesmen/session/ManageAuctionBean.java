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
	private static final long serialVersionUID = 5797405997183391745L;
	
	// Private attributes
	Auction auction; 
	private boolean inputIsOk=false;
	private int categoryId;
	
	// In annotations
	@In EntityManager entityManager;
	//@in Auction auction

	@Begin
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

	@End
	public void confirm()
	{
		this.auction.setStatus(Auction.AuctionStatus.LISTED);
		entityManager.merge(this.auction);
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