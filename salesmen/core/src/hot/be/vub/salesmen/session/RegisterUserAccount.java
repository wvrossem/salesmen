package be.vub.salesmen.session;

import javax.ejb.Local;

import be.vub.salesmen.entity.UserAccount;

@Local
public interface RegisterUserAccount
{
	public void createUser();
	public void userAccountCreated(UserAccount account);
	public void createUserAccount();
  public boolean next();

  public boolean verifyUsername();
  public void verifyPassword();
}