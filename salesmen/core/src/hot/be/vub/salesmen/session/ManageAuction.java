package be.vub.salesmen.session;

import be.vub.salesmen.entity.UserAccount;
import be.vub.salesmen.entity.Category;

import javax.ejb.Local;

@Local
public interface ManageAuction
{
	public void createAuction();
	public boolean checkInput();
	public void confirm();
	public void saveAndSchedule(UserAccount user);
    public boolean verifyPrice();
    public boolean verifyEndDate();

	public void processTreeNodeImplSelection(Category cat);
}