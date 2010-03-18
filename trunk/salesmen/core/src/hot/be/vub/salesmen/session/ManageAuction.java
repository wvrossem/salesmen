package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface ManageAuction
{
	public void create();
	public void checkInput();
	public void confirm();
}


