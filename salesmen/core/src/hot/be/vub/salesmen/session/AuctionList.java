package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import be.vub.salesmen.entity.Auction;

@Name("auctionList")
public class AuctionList extends EntityQuery<Auction>
{
    public AuctionList()
    {
        setEjbql("select auction from Auction auction");
    }
}
