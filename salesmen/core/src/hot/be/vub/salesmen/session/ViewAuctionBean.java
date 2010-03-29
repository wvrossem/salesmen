package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.SearchTerm;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.web.RequestParameter;


import javax.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;

import static org.jboss.seam.ScopeType.CONVERSATION;

@Scope(CONVERSATION)
@Name("viewAuction")
public class ViewAuctionBean implements ViewAuction   , Serializable 
{
	private static final long serialVersionUID = 5797405497183391745L;
	
	//Attributes
	Auction auction;
	
	//Request Parameters
    @RequestParameter
    Long auctionId;

	//@In annotations
	@In EntityManager entityManager;

	@Begin(join=true)
	public void start()
	{
		System.out.println("MESS: viewAuction.start() called for auction with ID="+this.auctionId);

		if(this.auction==null)
		{
			BasicSearchBean search = new BasicSearchBean();
			if(this.auctionId==null)
			{
				this.auctionId=1L;
			}
			this.auction = (Auction)search.findAuction(this.auctionId,this.entityManager);
		}
	}
	
	public void selectAuction(Auction a)
	{
		if(a==null)
		{
			System.out.println("MESS: viewAuction.selectAuction called null auction ");
			this.auctionId=2L;
		}
		else
		{
			System.out.println("MESS: viewAuction.selectAuction called auction with title "+a.getTitle());
			this.auctionId=a.getId();
			this.auction=a;
		}
	}

	/*
	Bid on the current auction
	*/
	public void bid()
	{
		if(this.auction!=null)
		{
			//...
			this.auction=null;
		}
	}

	//Public getters/setters
	public Auction getAuction()
	{
		return auction;
	}

	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}
	
	public Long getAuctionId()
	{
		return auctionId;
	}

	public void setAuctionId(Long auctionId)
	{
		this.auctionId = auctionId;
	}
}