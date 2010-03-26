package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;

import javax.ejb.Local;

@Local
public interface ViewAuction
{
    public void start();
    public void selectAuction(Auction a);
    public void bid();
}