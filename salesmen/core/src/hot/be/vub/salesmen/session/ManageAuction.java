package be.vub.salesmen.session;

import org.richfaces.event.NodeSelectedEvent;

import javax.ejb.Local;

import be.vub.salesmen.entity.User;

@Local
public interface ManageAuction
{
	public void createAuction();
	public void checkInput();
	public void confirm();
	public void save(User user);

	public void processTreeNodeImplSelection(final NodeSelectedEvent event);
}