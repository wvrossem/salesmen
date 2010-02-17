package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import be.vub.salesmen.entity.Bid;

@Name("bidList")
public class BidList extends EntityQuery<Bid>
{
    public BidList()
    {
        setEjbql("select bid from Bid bid");
    }
}
