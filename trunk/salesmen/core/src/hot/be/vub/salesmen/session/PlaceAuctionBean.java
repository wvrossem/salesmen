package be.vub.salesmen.session;

import javax.ejb.Stateless;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;
import org.jboss.seam.international.StatusMessages;

@Stateless
@Name("placeAuction")
public class PlaceAuctionBean implements PlaceAuction
{
	@Logger private Log log;

	@In StatusMessages statusMessages;

	public void placeAuction()
	{
		// implement your business logic here
		log.info("placeAuction.placeAuction() action called");
		statusMessages.add("placeAuction");
	}

	// add additional action methods

}