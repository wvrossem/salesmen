package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;

import javax.ejb.Local;

/**
 * Created by IntelliJ IDEA.
 * User: Bart
 * Date: 25-mrt-2010
 * Time: 11:58:36
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface ViewAuction
{
    public void start();
    public void selectAuction(Auction a);
    public void bid();
}
