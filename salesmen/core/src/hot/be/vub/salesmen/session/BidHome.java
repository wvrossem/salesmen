package be.vub.salesmen.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;

import be.vub.salesmen.entity.Bid;

@Name("bidHome")
public class BidHome extends EntityHome<Bid>
{
    @RequestParameter Long bidId;

    @Override
    public Object getId()
    {
        if (bidId == null)
        {
            return super.getId();
        }
        else
        {
            return bidId;
        }
    }

    @Override @Begin
    public void create() {
        super.create();
    }

}
