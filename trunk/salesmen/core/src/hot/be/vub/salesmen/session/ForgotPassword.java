package be.vub.salesmen.session;

import javax.ejb.Local;

//import be.vub.salesmen.entity.UserAccount;

@Local
public interface ForgotPassword
{
	public void checkUsername();
}
