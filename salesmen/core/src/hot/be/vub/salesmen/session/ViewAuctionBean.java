package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.SearchTerm;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;


import javax.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;

import static org.jboss.seam.ScopeType.CONVERSATION;

/**
 * Created by IntelliJ IDEA.
 * User: Bart
 * Date: 25-mrt-2010
 * Time: 11:58:23
 * To change this template use File | Settings | File Templates.
 */


@Scope(CONVERSATION)
@Name("viewAuction")
public class ViewAuctionBean implements ViewAuction   , Serializable 
{
    @In EntityManager entityManager;


    Auction auction;
    private static final long serialVersionUID = 5797405497183391745L;

    /*

     */
    @Begin(join=true)
    public void start()
    {
        int id=1;
        StringBuilder qry = new StringBuilder();
			qry.append("from Auction e");
			qry.append(" WHERE id="+id+"");
            qry.append(" AND e.status = " + Auction.AuctionStatus.LISTED.ordinal());

        this.auction = (Auction)entityManager.createQuery(qry.toString()).getSingleResult()        ;


        if(this.auction==null)  //REQUIRED, otherwise view-fields will be emptied on error message
        {
           this.auction = new Auction();
        }
    }
    
    public void selectAuction(Auction a)
    {
        this.auction=a;
    }

    /*
    Bid on the current auction
     */
    public void bid()
    {
        if(this.auction!=null)
        {
            //...
            this.auction=null;
        }
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
    
}
