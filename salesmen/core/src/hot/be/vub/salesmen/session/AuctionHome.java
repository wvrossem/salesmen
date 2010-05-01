package be.vub.salesmen.session;

import java.util.List;

import javax.persistence.EntityManager;

//import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.async.QuartzTriggerHandle;

// Extra imports
/*
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import org.jboss.seam.annotations.In;
import be.vub.salesmen.entity.Category;
*/
import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.Category;
import be.vub.salesmen.session.AuctionProcessor;

//TODO: to use this functionality, a user cannot be guest (nor admin?)
//TODO: @In the account of the user

@Name("auctionHome")
public class AuctionHome extends EntityHome<Auction>
{
	private static final long serialVersionUID = 2972682350816743680L;
	
	@RequestParameter
	Long auctionId;
	
	@SuppressWarnings("unused")
	private List<Category> categories;
	
	@In
	EntityManager entityManager;
	
	@In
	AuctionProcessor processor;
	
    @Override
    public Object getId()
    {
        if (auctionId == null)
        {
            return super.getId();
        }
        else
        {
            return auctionId;
        }
    }

    @Override @Begin(join=true)
    public void create() {
        super.create();
    }
	
	public String saveAndSchedule()
    {
        String result = persist();
        
        Auction auction = getInstance();
        
        QuartzTriggerHandle handle = processor.scheduleAuction(auction.getEndDate(), 
                                                null, 
                                                auction.getEndDate(), 
                                                auction);
        
        auction.setQuartzTriggerHandle( handle );

        return result;
    }
}