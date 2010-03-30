package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface ActivateUserAccount {

  public void verifyActivationKey();

}
