package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.UserAccount;

import javax.ejb.Local;

@Local
public interface ViewAuction
{
    public void start();
  //  public void selectAuction(Auction a); //deprecated
    public void bid(UserAccount owner);
}