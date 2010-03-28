package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface ManageAuction
{
	public void createAuction();
	public void checkInput();
	public void confirm();
    public void save();
}