package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.AuctionImage;
import be.vub.salesmen.entity.Bid;
import be.vub.salesmen.entity.UserAccount;
import be.vub.salesmen.entity.UserComment;
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
import java.util.ArrayList;
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
	private String commentText="";
    private Bid highestBid;
    private List<AuctionImage> images = new ArrayList<AuctionImage>();
	
	//Request Parameters
    @RequestParameter
    Long auctionId;

    @In
    FacesMessages facesMessages;

    @DataModel
	private List<Bid> bids;
	
	@DataModel
	private List<UserComment> comments;

	//@In annotations
	@In
    EntityManager entityManager;

	@In
	Search search;

	@Begin(join=true)
	public void start()
	{
		if(this.auction==null)
		{
			if(this.auctionId==null)//page.xml should prevent auctionId from being null, this is just a fail-safe
			{
				this.auctionId=1L;
			}
			this.auction = (Auction)search.findAuction(this.auctionId,this.entityManager);
            //reload bids
            this.updateBids();

            //reload images
            updateImages();
			updateComments();
		}
	}

    //loads the auction images
    private void updateImages()
    {
        images=search.findImages(this.auction,5,this.entityManager);
    }

    private void updateBids()
    {
        if(this.auction!=null && this.auction.getId()!=null)
        {
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
            facesMessages.addToControl("bidRegion", FacesMessage.SEVERITY_ERROR, "Bid amount can't be zero");
            return;
        }
		if(this.auction!=null && owner!=null && this.auction.getStatus()==Auction.AuctionStatus.LISTED)
		{
            //reload bids
            this.updateBids();
            //check if amount is high enough
            if(highestBid.getAmount()>=this.bidAmount || this.auction.getStartingPrice()>this.bidAmount)
            {
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
	
	private void updateComments()
    {
        if(this.auction!=null && this.auction.getId()!=null)
        {
            comments = search.findComments(this.auction,this.entityManager);
        }
    }
	/*
	Leave a comment
	*/
	public void addComment(UserAccount user)
	{
		updateComments();
		if(this.auction!=null && user!=null)
		{
			UserComment userComment = new UserComment(user, auction, commentText);
			this.entityManager.persist(userComment);
			updateComments();
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
	
	public String getCommentText()
	{
		return commentText;
	}
	
	public void setCommentText(String commentText)
	{
		this.commentText = commentText;
	}

    public List getBids() 
    {
        return bids;
    }

    public void setBids(List bids)
    {
        this.bids = bids;
    }
	
	public List<UserComment> getComments() 
    {
        return comments;
    }

    public void setComments(List<UserComment> comments)
    {
        this.comments = comments;
    }

    public Bid getHighestBid()
    {
        return highestBid;
    }

    public void setHighestBid(Bid highestBid)
    {
        this.highestBid = highestBid;
    }

    public List<AuctionImage> getImages()
    {
        return images;
    }

}