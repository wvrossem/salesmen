package be.vub.salesmen.session;

import be.vub.salesmen.entity.UserAccount;
import org.richfaces.event.NodeSelectedEvent;

import javax.ejb.Local;

@Local
public interface ManageAuction
{
	public void createAuction();
	public void checkInput();
	public void confirm();
	public void save(UserAccount user);
    public void verifyPrice();

	public void processTreeNodeImplSelection(NodeSelectedEvent event);
}