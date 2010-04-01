package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.Bid;
import be.vub.salesmen.entity.UserAccount;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.faces.FacesMessages;


import javax.faces.application.FacesMessage;
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
	private Auction auction;
    private double bidAmount=0;
    private Bid highestBid;
	
	//Request Parameters
    @RequestParameter
    Long auctionId;

    @In
    FacesMessages facesMessages;

    @DataModel
	private List<Bid> bids;

	//@In annotations
	@In
    EntityManager entityManager;

	@Begin(join=true)
	public void start()
	{
		if(this.auction==null)
		{
			BasicSearchBean search = new BasicSearchBean();
			if(this.auctionId==null)//page.xml should prevent auctionId from being null, this is just a fail-safe
			{
				this.auctionId=1L;
			}
			this.auction = (Auction)search.findAuction(this.auctionId,this.entityManager);
            //reload bids
            this.updateBids();
		}
	}
	 /*
	 //Deprecated
	public void selectAuction(Auction a)
	{
		if(a==null)
		{
			System.out.println("MESS: viewAuction.selectAuction called null auction ");
			this.auctionId=1L;
		}
		else
		{
			System.out.println("MESS: viewAuction.selectAuction called auction with title "+a.getTitle());
			this.auctionId=a.getId();
			this.auction=a;
            //reload bids
            this.updateBids();
		}
	}
	*/

    private void updateBids()
    {
        if(this.auction!=null && this.auction.getId()!=null)
        {
            BasicSearchBean search = new BasicSearchBean();
            //TODO: (Bart) do not use fixed value
            bids=search.findBids(this.auction,5,this.entityManager);


            //update highest bid
            if(bids!=null && bids.size()>0)
            {
                this.highestBid=bids.get(0);
            }
            else
            {
                this.highestBid=new Bid(0,null,this.auction);
            }
        }
        else
        {
            this.highestBid=new Bid(0,null,this.auction);
        }
    }
	/*
	Bid on the current auction
	*/
	public void bid(UserAccount owner)
	{
        if(this.bidAmount==0)
        {
            System.out.println("viewAuction.bid(): amount is zero, ignoring bid.");
            facesMessages.addToControl("bidRegion", FacesMessage.SEVERITY_ERROR, "Bid amount can't be zero");
            return;
        }
		if(this.auction!=null && owner!=null)
		{
            //reload bids
            this.updateBids();
            //check if amount is high enough
            if(highestBid.getAmount()>=this.bidAmount || this.auction.getStartingPrice()>this.bidAmount)
            {
                 System.out.println("viewAuction.bid(): Bid inadequate");
                 facesMessages.addToControl("bidRegion", FacesMessage.SEVERITY_ERROR, "Bid amount too low");
            }
            else
            {
                //continue
                Bid bid = new Bid(this.bidAmount, owner,this.auction);
                this.entityManager.persist(bid);

                //reload bids
                this.updateBids();

                facesMessages.addToControl("bidRegion", FacesMessage.SEVERITY_INFO, "Bid successful");
            }
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

    public double getBidAmount()
    {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount)
    {
        this.bidAmount = bidAmount;
    }

    public List getBids() 
    {
        return bids;
    }

    public void setBids(List bids)
    {
        this.bids = bids;
    }

    public Bid getHighestBid()
    {
        return highestBid;
    }

    public void setHighestBid(Bid highestBid)
    {
        this.highestBid = highestBid;
    }
}