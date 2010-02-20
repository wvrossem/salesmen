package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;

import be.vub.salesmen.entity.Auction;

@Name("auctionHome")
public class AuctionHome extends EntityHome<Auction>
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2972682350816743680L;
	@RequestParameter Long auctionId;

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

    @Override @Begin
    public void create() {
        super.create();
    }

}
