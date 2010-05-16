package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

@Name("hotAuctionList")
public class HotAuctionList extends EntityQuery<Auction>
{
	private static final long serialVersionUID = -494416824954132695L;

	public HotAuctionList()
	{
		setEjbql("select a from Auction a where a.hotAuction=TRUE");
	}
}
