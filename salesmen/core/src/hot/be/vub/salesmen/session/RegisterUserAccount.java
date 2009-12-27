package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface RegisterUserAccount
{
	
	public void newRegistration();
	
	public String register();


}
