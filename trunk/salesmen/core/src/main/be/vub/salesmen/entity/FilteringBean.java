package be.vub.salesmen.session;

import org.jboss.seam.annotations.*;
import be.vub.salesmen.session.BasicSearchBean;
import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.AuctionImage;
import be.vub.salesmen.entity.Bid;
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



@Name("filteringBean")
public class FilteringBean 
{

	private String filterValue="";
	private List<Bid> bids;
	private List<AuctionImage> auctionImages;
	private Auction auction;
	private double highestBidAmount;
	@In EntityManager entityManager;
	private byte[] imageData;	
	
	public FilteringBean()
	{
	}
	
    public boolean filterAuctions(Object current)  //Not used at the moment...
	{
        Auction currentAuction = (Auction) current;
        if (filterValue.length()==0) {
            return true;
        }
        if (currentAuction.getDescription().toLowerCase().startsWith(filterValue.toLowerCase())) {
            return true;
        }else {
            return false; 
        }
    }
	public double getHighestBidAmount(Auction currentAuction) //Get the highest bid of an auction
	{
		if (currentAuction!=null){
			BasicSearchBean searchBids = new BasicSearchBean();
			bids=searchBids.findBids(currentAuction,1,this.entityManager);
			
			//update highest bid
			if(bids!=null && bids.size()>0)
			{
				this.highestBidAmount=bids.get(0).getAmount();
			}
			else
			{
				this.highestBidAmount=0.0;
			}	
			
			return this.highestBidAmount;
			}
		else {
			this.highestBidAmount=0.0;
			return this.highestBidAmount;
		}
	}
	
	public byte[] getFirstImage(Auction currentAuction) //Get the main image of an auction
	{
		if (currentAuction!=null){
			BasicSearchBean searchImages = new BasicSearchBean(); 
			auctionImages=searchImages.findImages(currentAuction,1,this.entityManager); 			
			//update first image
			if(auctionImages!=null && auctionImages.size()>0)
			{
				this.imageData=auctionImages.get(0).getData(); //imageData is a byte[] that contains the first image
			}
			else
			{
				this.imageData=null; //if there are no images (auctionImages=null && auctionImage.size()=0), then there is no image=> imageData=null
			}	
			
			return this.imageData;
			}
		else {
			this.imageData=null; //if there is no auction (currentAuction=null), then there is no image
			return this.imageData;
		}
	}
	

	
	public double getHighestBidAmount()
	{
		return highestBidAmount;
	}
	
	public byte[] getImageData()
	{
		return imageData;
	}
	
	public String getFilterValue()
	{
        return filterValue;
    }

    public void setFilterValue(String filterValue)
	{
        this.filterValue = filterValue;
    }
}
