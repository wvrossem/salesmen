package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface UserIdentity {

  public static enum loginState {
	  ok, banned
	}

  public void login();

  public boolean isLoggedIn();

  public boolean isBanned();
  
}
