package be.vub.salesmen.session;

import java.util.Date;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.annotations.async.Expiration;
import org.jboss.seam.annotations.async.FinalExpiration;
import org.jboss.seam.annotations.async.IntervalDuration;
import org.jboss.seam.async.QuartzTriggerHandle;
import org.jboss.seam.log.Log;

import be.vub.salesmen.entity.Auction;

@Name("processor")
@AutoCreate
public class AuctionProcessor {
    
    @In EntityManager entityManager;
	
	@In(create=true)
	EmailService emailService;

    @Logger Log log;

    @Asynchronous
    @Transactional
    public QuartzTriggerHandle scheduleAuction(@Expiration Date when,
        @IntervalDuration Long interval, @FinalExpiration Date stoptime, Auction auction)
    {
        auction = entityManager.merge(auction);
		log.info("An auction's deadline has passed.");
		emailService.sendFinishedAuctionEmail(auction);
		
        if (auction.getStatus() == Auction.AuctionStatus.LISTED)
        {
            auction.setStatus(Auction.AuctionStatus.FINISHED);
        }
		
        return null;
    }
}
