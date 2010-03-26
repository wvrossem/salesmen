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



    @RequestParameter
    Long auctionId;
	 
	//@In annotations
	
	@In EntityManager entityManager;

	@Begin
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
  
              /*
             // keep this code for a while, BART will delete it
            StringBuilder qry = new StringBuilder();
            qry.append("from Auction e");
            qry.append(" WHERE id="+this.auctionId.intValue()+"");
            qry.append(" AND e.status = " + Auction.AuctionStatus.LISTED.ordinal());

            this.auction = (Auction)(entityManager.createQuery(qry.toString()).getSingleResult());

            //System.out.println("viewAuction.start() MESS: after query, auction: "+this.auction.getTitle());
            entityManager.joinTransaction();   //Bart: no idea why, but this loc is required to make this function work. Anyway, the basicSearchBean should provide this functionality
        */
        }
    }
	
	public void selectAuction(Auction a)
	{
        if(a==null)
        {
            System.out.println("MESS: viewAuction.selectAuction called null auction ");
        }
        this.auction=a;
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