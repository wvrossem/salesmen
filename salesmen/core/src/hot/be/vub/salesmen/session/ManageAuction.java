package be.vub.salesmen.session;

import org.richfaces.event.NodeSelectedEvent;

import javax.ejb.Local;

import be.vub.salesmen.entity.UserAccount;

@Local
public interface ManageAuction
{
	public void createAuction();
	public void checkInput();
	public void confirm();
	public void save(UserAccount user);

	public void processTreeNodeImplSelection(final NodeSelectedEvent event);
}