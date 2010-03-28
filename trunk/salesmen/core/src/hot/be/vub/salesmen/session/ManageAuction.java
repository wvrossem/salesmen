package be.vub.salesmen.session;

import org.richfaces.event.NodeSelectedEvent;

import javax.ejb.Local;

@Local
public interface ManageAuction
{
	public void createAuction();
	public void checkInput();
	public void confirm();
    public void save();

    public void processTreeNodeImplSelection(final NodeSelectedEvent event);
}