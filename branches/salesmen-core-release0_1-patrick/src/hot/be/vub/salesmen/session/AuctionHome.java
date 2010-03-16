package be.vub.salesmen.session;

import java.util.List;

import javax.persistence.EntityManager;

//import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;



/*
 * Extra imports
 * 
 */
/*
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import org.jboss.seam.annotations.In;
import be.vub.salesmen.entity.Category;
*/
import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.Category;


//TODO: to use this functionality, a user cannot be guest (nor admin?)
//TODO: @In the account of the user

@Name("auctionHome")
public class AuctionHome extends EntityHome<Auction>
{
	private static final long serialVersionUID = 2972682350816743680L;
	@RequestParameter Long auctionId;
	@SuppressWarnings("unused")
	private List<Category> categories;
	@In EntityManager entityManager;
	
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


}
