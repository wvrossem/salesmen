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
	@In EntityManager entityManager;
	
	private static final long serialVersionUID = 5797405997183391745L;
	private Auction auction; 
	private boolean inputIsOk=false;
	
	

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
		}
		else
		{
			FacesMessages.instance().addToControl("price", "Price should be greater than 0");
		}
	}

	
	public void setAuction(Auction auction) {
		this.auction = auction;
	}


	public Auction getAuction() {
		return auction;
	}
	
	@Destroy @Remove
	public void destroy() {}


	public void setInputIsOk(boolean inputIsOk) {
		this.inputIsOk = inputIsOk;
	}


	public boolean isInputIsOk() {
		return inputIsOk;
	}
}
