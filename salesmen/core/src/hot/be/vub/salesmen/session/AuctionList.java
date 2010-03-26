package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import be.vub.salesmen.entity.Auction;

@Name("auctionList")
public class AuctionList extends EntityQuery<Auction>
{
	private static final long serialVersionUID = -7218320715871639765L;

	public AuctionList()
	{
		setEjbql("select auction from Auction auction");
	}
}