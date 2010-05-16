package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface ForgotPassword
{
	public void checkUsername();
}
